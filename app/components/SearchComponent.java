package components;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import models.Project;
import models.SearchResults;
import models.Skill;
import models.external.FreelancerJob;
import models.external.FreelancerProject;
import org.springframework.stereotype.Component;

/**
 * This component has the business logic related to search requests.
 * The component is responsible for calling the Freelancer Client for search operations
 * and then transform the returned value to internal models to be rendered in html viewd.
 */
@Component
public class SearchComponent {

    private final FreelancerClient client;
    private final WordStatComponent wordStatComponent;
    private final ReadingScoreComponent readingScoreComponent;

    private final Supplier<UUID> uuidSupplier = UUID::randomUUID;

    /**
     * Constructor for this class.
     * The arguments received by this method are injected.
     *
     * @param injectedClient
     * @param wordStatComponent
     * @param readingScoreComponent
     */
    @Inject
    public SearchComponent(final FreelancerClient injectedClient, final WordStatComponent wordStatComponent, final ReadingScoreComponent readingScoreComponent) {
        this.client = injectedClient;
        this.wordStatComponent = wordStatComponent;
        this.readingScoreComponent = readingScoreComponent;
    }

    /**
     *  Method to search projects based on search term.
     *  The method internally calls Freelancer client to fetch the search results.
     *  It also calls WordStatComponent and ReadingScoreComponent to populate other attributes of SearchResults.
     *
     * @param searchTerm - search term to look for in the project
     * @return SearchResults wrapped in a CompletionStage.
     */
    public CompletionStage<SearchResults> searchProjects(final String searchTerm) {
        return client.getSearchResults(searchTerm)
                .thenApplyAsync(searchResult -> buildSearchResult(searchResult, searchTerm));
    }

    /**
     *  Method to search projects based on skill id.
     *  The method internally calls Freelancer client to fetch the search results.
     *  It also calls WordStatComponent and ReadingScoreComponent to populate other attributes of SearchResults.
     *
     * @param skillId - skill/job id to look for in the project
     * @return SearchResults wrapped in a CompletionStage.
     */
    public CompletionStage<SearchResults> searchProjectsForSkill(final String skillId) {
        return client.getSearchResultForSkill(skillId)
                .thenApply(searchResult -> buildSearchResult(searchResult, skillId));
    }

    /**
     *  Method to search projects based on owner id.
     *  The method internally calls Freelancer client to fetch the search results.
     *  It also calls WordStatComponent and ReadingScoreComponent to populate other attributes of SearchResults.
     *
     * @param ownerId - owner id that the projects belong to.
     * @return List of Projects wrapped in a CompletionStage.
     */
    public CompletionStage<List<Project>> searchProjectsForOwner(final String ownerId) {
        return client.getSearchResultForOwnerId(ownerId)
                .thenApply(searchResult -> getProjectsList(searchResult));
    }

    /**
     * This method is responsible for building the SearchResult.
     * It calls WordStatComponent and ReadingScoreComponent internally to populate total
     * wordStat and readingEaseScores attributes respectively for the set of projects.
     *
     * @param searchResult - Set of FreelancerProjects received from Freelancer API.
     * @param searchTerm - The string that was searched on Freelancer.
     * @return SearchResults built from input FreelancerProjects.
     */
    private SearchResults buildSearchResult(final Set<FreelancerProject> searchResult, final String searchTerm) {
        return SearchResults.builder()
                .requestId(getUUID())
                .searchTerm(searchTerm)
                .wordStat(wordStatComponent.getWordStat(searchResult))
                .projectsList(getProjectsList(searchResult))
                .readingEaseScores(readingScoreComponent.getAverageReadingEaseScores(searchResult))
                .build();
    }

    /**
     * This method transforms Set of FreelancerProject to a List of Project.
     * It calls WordStatComponent and ReadingScoreComponent internally to populate
     * wordStat and readingEaseScores attributes respectively for individual projects.
     *
     * @param freelancerProjects - Set of projects received from Freelancer based of the search criterion.
     * @return List of Project
     */
    private List<Project> getProjectsList(Set<FreelancerProject> freelancerProjects) {
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
                            .wordStat(wordStatComponent.getWordStat(freelancerProject.getPreviewDescription()))
                            .readingEaseScores(readingScoreComponent.getReadingEaseScores(title, description))
                            .build();
                })
                .collect(Collectors.toList());
    }

    /**
     * This method transforms Freelancer jobs model to internal Skills model.
     *
     * @param jobs - List of jobs assigned to a project
     * @return List of Skill corresponding to the input jobs
     */
    private List<Skill> buildSkillsList(final List<FreelancerJob> jobs) {
        return jobs.stream().map(job -> {
            return Skill.builder()
                    .id(job.getId())
                    .name(job.getName())
                    .build();
        }).collect(Collectors.toList());
    }

    /**
     * Returns a unique id for each request.
     *
     * @return UUID string
     */
    private String getUUID() {
        return uuidSupplier.get().toString().replace("-", "");
    }
}