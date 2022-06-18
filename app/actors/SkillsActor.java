package actors;

import akka.actor.AbstractActorWithTimers;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.stream.ActorMaterializer;
import models.Project;
import models.SearchResults;
import components.*;
import models.Skill;
import models.SkillsSearchResult;
import models.external.FreelancerJob;
import models.external.FreelancerProject;
import play.libs.ws.WSClient;
import play.libs.ws.ahc.AhcWSClient;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.AsyncHttpClientConfig;
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClient;
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClientConfig;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SkillsActor extends AbstractActorWithTimers {

    private SearchComponent searchComponent;

    private final Supplier<UUID> uuidSupplier = UUID::randomUUID;
    private ActorRef out;
    private String skillId;
    private String skillName;
    private FreelancerClient freelancerClient;
    private final WordStatComponent wordStatComponent;
    private final ReadingScoreComponent readingScoreComponent;

    public static final class SkillTick {
        String skillId;
        String skillName;

        public SkillTick(final String skillId, final String skillName) {
            this.skillId = skillId;
            this.skillName = skillName;
        }
    }

    /**
     * Constructor for this class.
     * The arguments received by this method are injected.
     *
     */
    public SkillsActor(ActorRef out, final String skillId, final String skillName) {
        this.out = out;
        this.skillId = skillId;
        this.skillName = skillName;
        // Set up AsyncHttpClient directly from config
        AsyncHttpClientConfig asyncHttpClientConfig =
                new DefaultAsyncHttpClientConfig.Builder()
                        .setMaxRequestRetry(0)
                        .setShutdownQuietPeriod(0)
                        .setShutdownTimeout(0)
                        .build();
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient(asyncHttpClientConfig);

        WSClient client = new AhcWSClient(asyncHttpClient, ActorMaterializer.create(getContext()));
        this.freelancerClient = new FreelancerClient(client);
        this.wordStatComponent = new WordStatComponent();
        this.readingScoreComponent = new ReadingScoreComponent();
    }

    public static Props props(ActorRef out, final String skillId, final String skillName) {
        return Props.create(SkillsActor.class, () -> new SkillsActor(out, skillId, skillName));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(SkillTick.class, this::getSkillSearchResult)
                .build();
    }

    @Override
    public void preStart() {
        System.out.println("Skills actor started");
        getTimers().startPeriodicTimer("Timer", new SkillTick(skillId, skillName),
                Duration.create(5, TimeUnit.SECONDS));
    }

    @Override
    public void postStop() {
        System.out.println("Skills actor stopped");
    }

    /**
     * This method takes skill name and skill id and returns projects that have that skill as one of the requirement.
     * It calls SearchComponent to fetch the actual search result.
     *
     * @param skill - Id and name of the skill for which we want to fetch projects
     * @return Returns SkillsSearchResult wrapped in a CompletionStage
     */
    public void getSkillSearchResult(final SkillTick skill) {
        System.out.println(skill.skillId);
        searchProjectsForSkill(skill.skillId)
                .thenApplyAsync(searchResults -> buildSkillSearchResult(searchResults, skill.skillName))
                .thenAccept(skillsSearchResult -> out.tell(requestToJsonNode(skillsSearchResult), getSelf()));
    }
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static JsonNode requestToJsonNode(SkillsSearchResult skillsSearchResult) {
        return OBJECT_MAPPER.convertValue(skillsSearchResult, JsonNode.class);
    }

    public CompletionStage<SearchResults> searchProjectsForSkill(final String skillId) {
        return freelancerClient.getSearchResultForSkill(skillId)
                .thenApply(searchResult -> buildSearchResult(searchResult, skillId));
    }

    private SearchResults buildSearchResult(final Set<FreelancerProject> searchResult, final String searchTerm) {
        return SearchResults.builder()
                .requestId(getUUID())
                .searchTerm(searchTerm)
                .wordStat(wordStatComponent.getWordStat(searchResult))
                .projectsList(getProjectsList(searchResult))
                .readingEaseScores(readingScoreComponent.getAverageReadingEaseScores(searchResult))
                .build();
    }

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

    private List<Skill> buildSkillsList(final List<FreelancerJob> jobs) {
        return jobs.stream().map(job -> {
            return Skill.builder()
                    .id(job.getId())
                    .name(job.getName())
                    .build();
        }).collect(Collectors.toList());
    }

    /**
     * This method builds the SkillsSearchResult from the SearchResults in the input.
     *
     * @param searchResults - SearchResults returned from the SearchComponent.
     * @param skillName - Name of the skill.
     * @return SkillsSearchResult built from SearchResults.
     */
    private SkillsSearchResult buildSkillSearchResult(final SearchResults searchResults, final String skillName) {
        final String requestId = getUUID();
        return SkillsSearchResult.builder()
                .skill(Skill.builder().name(skillName).build())
                .projectsList(searchResults.getProjectsList())
                .wordStat(searchResults.getWordStat())
                .readingEaseScores(searchResults.getReadingEaseScores())
                .requestId(requestId)
                .renderedProjectHtml(views.html.projectsDisplay.render(searchResults.getProjectsList(), requestId).toString())
                .build();
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
