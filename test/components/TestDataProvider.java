package components;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Project;
import models.ReadingEaseScores;
import models.SearchResults;
import models.Skill;
import models.external.FreelancerJob;
import models.external.FreelancerProject;
import models.external.FreelancerUser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class has the responsibility to provide other test classes with test data.
 * This class will mostly contain static util methods.
 */
public class TestDataProvider {

    public static final UUID REQUEST_ID = UUID.randomUUID();
    public static final String DUMMY_DESCRIPTION = "firstWord secondWord. thirdWord firstWord secondWord";
    public static final String DUMMY_DESCRIPTION_1 = "firstWord secondWord. thirdWord";

    private static final String USER_DETAILS_DATA_PATH =
            "test/testData/user_test_data.txt";
    private static final String PROJECT_DETAILS_DATA_PATH =
            "test/testData/project_test_data.txt";

    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    /**
     * This method provides FreelancerUser test data by fetching it for user_test_data.txt
     * @return FreelancerUser fetched from test data.
     * @throws IOException
     */
    public static FreelancerUser getFreelanceUser() throws IOException {
        final JsonNode jsonNode = mapper.readTree(new String(Files.readAllBytes(Paths.get(USER_DETAILS_DATA_PATH).toAbsolutePath())));
        return mapper.convertValue(jsonNode.get("result"), FreelancerUser.class);
    }

    /**
     * This method provides FreelancerUser JsonNode test data by fetching it for user_test_data.txt
     * @return FreelancerUser JsonNode fetched from test data.
     * @throws IOException
     */
    public static JsonNode getFreelanceUserJsonNode() throws IOException {
        return mapper.readTree(new String(Files.readAllBytes(Paths.get(USER_DETAILS_DATA_PATH))));
    }

    /**
     * This method provides FreelancerProject test data by fetching it for project_test_data.txt
     * @return FreelancerProject fetched from test data.
     * @throws IOException
     */
    public static Set<FreelancerProject> getFreelancerProjects() throws IOException {
        final JsonNode jsonNode = mapper.readTree(new String(Files.readAllBytes(Paths.get(PROJECT_DETAILS_DATA_PATH))));
        Set<FreelancerProject> expectedProjects = new HashSet<>();
        jsonNode.get("result").get("projects").elements()
                .forEachRemaining(project -> expectedProjects.add(mapper.convertValue(project, FreelancerProject.class)));
        return  expectedProjects;
    }

    /**
     * This method provides FreelancerProject JsonNode test data by fetching it for project_test_data.txt
     * @return FreelancerProject JsonNode fetched from test data.
     * @throws IOException
     */
    public static JsonNode getFreelancerProjectsJsonNode() throws IOException {
        return mapper.readTree(new String(Files.readAllBytes(Paths.get(PROJECT_DETAILS_DATA_PATH))));
    }

    /**
     * Util method to get List<Projects> from Set of FreelancerProject, wordStat map and readingEaseScores.
     *
     * @param freelancerProjects - Set of mock FreelancerProject.
     * @param wordStat - Common mock wordStat for all the projects.
     * @param readingEaseScores - Common mock ReadingEaseScore for all the projects.
     * @return Returns list of Project.
     */
    public static List<Project> getProjectsListFromFreelancerProjects(Set<FreelancerProject> freelancerProjects,
                                                                      final Map<String, Integer> wordStat,
                                                                      final ReadingEaseScores readingEaseScores) {
        return freelancerProjects.stream()
                .map((freelancerProject) -> {
                    final String title = freelancerProject.getTitle();
                    final String description = freelancerProject.getDescription();
                    return Project.builder()
                            .description(description)
                            .ownerId(freelancerProject.getOwnerId())
                            .projectId(freelancerProject.getId())
                            .title(title)
                            .dateSubmitted(DateTimeUtils.getDateStringFromMillis(freelancerProject.getTimeSubmitted()))
                            .timeSubmitted(freelancerProject.getTimeSubmitted().toString())
                            .projectType(freelancerProject.getType())
                            .skillsList(buildSkillsList(freelancerProject.getJobs()))
                            .wordStat(wordStat)
                            .readingEaseScores(readingEaseScores)
                            .build();
                })
                .collect(Collectors.toList());
    }

    /**
     * This method build List of Skills from provided input List of FreelancerJob
     *
     * @param jobs - FreelancerJob to be converted.
     * @return Returns List of Skill mapped from the input FreelancerJob.
     */
    private static List<Skill> buildSkillsList(final List<FreelancerJob> jobs) {
        return jobs.stream().map(job -> {
            return Skill.builder()
                    .id(job.getId())
                    .name(job.getName())
                    .build();
        }).collect(Collectors.toList());
    }
}
