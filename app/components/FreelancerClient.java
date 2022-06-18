package components;

import javax.inject.Inject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.external.FreelancerProject;
import models.external.FreelancerUser;
import play.libs.ws.*;
import java.util.*;
import java.util.concurrent.CompletionStage;
import java.time.temporal.ChronoUnit;
import java.time.Duration;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonNode;
import com.ibm.asyncutil.iteration.AsyncIterator;

/**
 * This class is responsible for calling the Freelancer API using HTTP requests.
 * It is the only class that can communicate with Freelancer's APIs and understands the response data.
 * It uses Play Frameworks WSClient and WSRequest to do this.
 * The class also maps the json response to Java POJOs to be used by other components.
 */
public class FreelancerClient implements WSBodyReadables, WSBodyWritables {

    private static final String FREELANCE_URL = "https://www.freelancer.com/api/";
    private static final String PROJECT_SEARCH_PATH = "projects/0.1/projects/";
    private static final String ACTIVE_PROJECT_SEARCH_PATH = "projects/0.1/projects/active/";
    private static final String GET_USER_BY_ID_PATH = "users/0.1/users/%s/";
    private static final String FREELANCER_AUTH_TOKEN = "qhZR9ndUAnjhddxdy0t5QhjRXJ558z";
    private static final String ACTIVE_SEARCH_REQUEST_URL = FREELANCE_URL + ACTIVE_PROJECT_SEARCH_PATH;
    private static final String PROJECT_SEARCH_REQUEST_URL = FREELANCE_URL + PROJECT_SEARCH_PATH;
    private static final String GET_USER_BY_ID_REQUEST_URL = FREELANCE_URL + GET_USER_BY_ID_PATH;
    private static final Integer TIMEOUT = 15000;

    final ObjectMapper mapper = new ObjectMapper()
            .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    private final WSClient ws;


    /**
     * Constructor for this class.
     * The arguments received by this method are injected.
     *
     * @param ws - WSClient object.
     */
    @Inject
    public FreelancerClient(WSClient ws) {
        this.ws = ws;
    }

    /**
     * This method is used to search for projects based on the user provided search term.
     * We need to fetch at most 250 projects for a search term. Freelancer limits the number of projects returned to 100
     * irrespective of the number passed as limit parameter.
     * This method uses AsyncIterator to call the API 5 times, each time fetching 50 results.
     * The 50 result number was decided to ensure stability and ease of implementation.
     * In practice, we noticed that if we request for 100 results we did not receive exact 100 results.
     * The index of the iteration provided is used to calculate the offset query param value.
     * All the 5 API calls are async and do not block anything.
     * The results from the 5 calls are combined into one list.
     * This result is then mapped to a Set of FreelancerProject.
     * The actual projects we want are in the "projects" node of the "result" node in the json response.
     *
     * @param searchTerm - String of search term for which we want to fetch projects.
     * @return Returns a Set of FreelancerProject received from Freelancer for the search term, wrapped in a CompletionStage.
     */
    public CompletionStage<Set<FreelancerProject>> getSearchResults(final String searchTerm) {
        return AsyncIterator
                .range(0, 5)
                .thenComposeAsync(i -> executeProjectSearchRequestForSearchTerm(i, searchTerm))
                .collect(Collectors.toList())
                .thenApplyAsync((results) -> getProjectListFromResultsNode(results));
    }

    /**
     * This method is used to search for projects based on the user selected skill.
     * We need to fetch at most 250 projects for a Skill. Freelancer limits the number of projects returned to 100
     * irrespective of the number passed as limit parameter.
     * This method uses AsyncIterator to call the API 5 times, each time fetching 50 results.
     * The 50 result number was used to ensure stability and ease of implementation.
     * In practice, we noticed that if we request for 100 results we did not receive exact 100 results.
     * The index of the iteration provided is used to calculate the offset query param value.
     * All the 5 API calls are async and do not block anything.
     * The results from the 5 calls are combined into one list.
     * This result is then mapped to a Set of FreelancerProject.
     * The actual projects we want are in the "projects" node of the "result" node in the json response.
     *
     * @param skillId - Id of the skill for which we want to fetch projects.
     * @return Returns a Set of FreelancerProject received from Freelancer for the skill, wrapped in a CompletionStage.
     */
    public CompletionStage<Set<FreelancerProject>> getSearchResultForSkill(final String skillId) {
        return AsyncIterator
                .range(0, 5)
                .thenComposeAsync(i -> executeProjectSearchRequestForSkills(i, skillId))
                .collect(Collectors.toList())
                .thenApplyAsync((results) -> getProjectListFromResultsNode(results));
    }

    /**
     * This method is to search for projects posted by an Owner/Employer.
     * We need to fetch at most 10 projects posted by the Owner/Employer.
     * The method first builds the WSRequest and executes it.
     * It then extracts the "projects" JsonNode from the results.
     * This result is then mapped into a Set of FreelancerProject.
     *
     * @param ownerId - Id of the owner/employer that has posted the project.
     * @return Returns a Set of FreelancerProject received from Freelancer posted by the Owner/Employer, wrapped in a CompletionStage
     */
    public CompletionStage<Set<FreelancerProject>> getSearchResultForOwnerId(final String ownerId) {
        return executeRequest(buildSearchProjectWithOwnerIdRequest(ownerId))
                .thenApplyAsync((result) -> getProjectsNodeFromResult(result))
                .thenApplyAsync((results) -> getProjectListFromResult(results));
    }

    /**
     * This method fetches the User details from Freelancer using the provided user id.
     *
     * @param ownerId - Id of the Owner/Employer for whom we want to fetch user details.
     * @return Returns FreelancerUser with details corresponding to the user id, wrapped in a CompletionStage.
     */
    public CompletionStage<FreelancerUser> getUserDetails(final String ownerId) {
        return executeRequest(buildGetUserDetailsRequest(ownerId))
                .thenApplyAsync(result -> getUserDetailsFromResult(result));
    }

    /**
     * This method is used to map the result JsonNode to FreelancerUser.
     * This conversion is done using ObjectMapper::convertValue.
     *
     * @param result - result JsonNode to be mapped.
     * @return Returns FreelancerUser obtained from the input JsonNode.
     */
    private FreelancerUser getUserDetailsFromResult(final JsonNode result) {
        return mapper.convertValue(result, FreelancerUser.class);
    }

    /**
     * This method builds the WSRequest to fetch user details from Freelancer.
     *
     * @param ownerId - Id of the Owner/Employer for whom we want to fetch user details.
     * @return Returns the WSRequest with all the appropriate parameters set.
     */
    private WSRequest buildGetUserDetailsRequest(final String ownerId) {
        return ws.url(String.format(GET_USER_BY_ID_REQUEST_URL, ownerId))
                .addHeader("freelancer-oauth-v1", FREELANCER_AUTH_TOKEN)
                .setRequestTimeout(Duration.of(TIMEOUT, ChronoUnit.MILLIS));
    }

    /**
     * This method maps the "projects" JsonNode to a Set of FreelancerProject.
     * This conversion is done using ObjectMapper::convertValue.
     *
     * @param projectsNode - "projects" JsonNode from the response.
     * @return Set of FreelancerProject obtained from the input JsonNode.
     */
    private Set<FreelancerProject> getProjectListFromResult(final JsonNode projectsNode) {
        Set<FreelancerProject> projects = new HashSet<>();
        projectsNode.elements()
                .forEachRemaining(project -> projects.add(mapper.convertValue(project, FreelancerProject.class)));
        return projects;

    }

    /**
     * This method maps a List of "result" JsonNode to a Set of FreelancerProject.
     * First the method extracts the "projects" JsonNode from each of the "result" JsonNodes.
     * Next each of the "projects" JsonNodes is converted to Set of FreelancerProject by calling FreelancerClient::getProjectListFromResult.
     * This produces a stream of Sets which is then flattened and collected into the final Set and returned.
     *
     * @param results - List of "result" JsonNodes.
     * @return Set of FreelancerProject obtained from the input JsonNodes List.
     */
    private Set<FreelancerProject> getProjectListFromResultsNode(final List<JsonNode> results) {
        return results.stream()
                .map(result -> getProjectsNodeFromResult(result))
                .map(result -> getProjectListFromResult(result))
                .flatMap(projects -> projects.stream())
                .collect(Collectors.toSet());
    }

    /**
     * This method adds the "jobs" query param to the base active projects search request and then executes it.
     * This method is used to search for projects based on the skill id.
     *
     * @param offset - Value for the offset query param.
     * @param skillId - skill id for the skill that the user requests. The projects returned have this skill as a requirement.
     * @return Returns "result" JsonNode from the response, wrapped in a CompletionStage.
     */
    private CompletionStage<JsonNode> executeProjectSearchRequestForSkills(final Long offset, final String skillId) {
        WSRequest request = getBaseActiveProjectSearchRequest(offset);
        request.addQueryParameter("jobs[]", skillId);
        return executeRequest(request);
    }

    /**
     * This method adds the "query" query param to the base active projects search request and then executes it.
     * This method is used to search for projects based on user provided search term.
     *
     * @param offset - Value for the offset query param.
     * @param searchTerm - search term to look for in the projects.
     * @return Returns "result" JsonNode from the response, wrapped in a CompletionStage.
     */
    private CompletionStage<JsonNode> executeProjectSearchRequestForSearchTerm(final Long offset, final String searchTerm) {
        WSRequest request = getBaseActiveProjectSearchRequest(offset);
        request.addQueryParameter("query", searchTerm);
        return executeRequest(request);
    }

    /**
     * Extracts "projects" node from the input JsonNode.
     *
     * @param result - JsonNode with projects
     * @return Returns "projects" JsonNode
     */
    private JsonNode getProjectsNodeFromResult(final JsonNode result) {
        return result.get("projects");
    }

    /**
     * This method builds base WSRequest to search active projects.
     *
     * @param offset - offset query param for this request.
     * @return WSRequest with all the appropriate base params set.
     */
    private WSRequest getBaseActiveProjectSearchRequest(final Long offset) {
        return ws.url(ACTIVE_SEARCH_REQUEST_URL)
                .addHeader("freelancer-oauth-v1", FREELANCER_AUTH_TOKEN)
                .setRequestTimeout(Duration.of(TIMEOUT, ChronoUnit.MILLIS))
                .addQueryParameter("limit", "50")
                .addQueryParameter("offset", String.valueOf(offset*50))
                .addQueryParameter("full_description", "true")
                .addQueryParameter("job_details", "true")
                .addQueryParameter("sort_field", "time_updated");
    }

    /**
     * This method builds WSRequest to fetch projects based on owner id.
     *
     * @param ownerId - id of the employer or owner.
     * @return WSRequest with all the appropriate params set.
     */
    private WSRequest buildSearchProjectWithOwnerIdRequest(final String ownerId) {
        return ws.url(PROJECT_SEARCH_REQUEST_URL)
                .addHeader("freelancer-oauth-v1", FREELANCER_AUTH_TOKEN)
                .setRequestTimeout(Duration.of(TIMEOUT, ChronoUnit.MILLIS))
                .addQueryParameter("limit", "10")
                .addQueryParameter("full_description", "true")
                .addQueryParameter("job_details", "true")
                .addQueryParameter("owners[]", ownerId);
    }

    /**
     * This method executes GET API call on the input WSRequest.
     * It also extracts the result JsonNode from the Json response.
     *
     * @param request - WSRequest to execute.
     * @return Returns the "result" node of GET API response.
     */
    private CompletionStage<JsonNode> executeRequest(final WSRequest request) {
        return request.get().thenApplyAsync(r -> r.asJson().get("result"));
    }
}