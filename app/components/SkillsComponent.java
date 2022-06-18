package components;

import models.SearchResults;
import models.Skill;
import models.SkillsSearchResult;
import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

/**
 * This component deals with the requests to search projects based on skill.
 * It internally calls SearchComponent to fetch the search results.
 * It returns the view model used to render /skills page.
 */
public class SkillsComponent {

    final private SearchComponent searchComponent;
    private final Supplier<UUID> uuidSupplier = UUID::randomUUID;

    /**
     * Constructor for this class.
     * The arguments received by this method are injected.
     *
     * @param injectedSearchComponent
     */
    @Inject
    public SkillsComponent(final SearchComponent injectedSearchComponent) {
        this.searchComponent = injectedSearchComponent;
    }

    /**
     * This method takes skill name and skill id and returns projects that have that skill as one of the requirement.
     * It calls SearchComponent to fetch the actual search result.
     *
     * @param skillId - Id of the skill for which we want to fetch projects
     * @param skillName - name of the skill for which we want to fetch projects
     * @return Returns SkillsSearchResult wrapped in a CompletionStage
     */
    public CompletionStage<SkillsSearchResult> getSkillSearchResult(final String skillId, final String skillName) {
        return searchComponent.searchProjectsForSkill(skillId)
                .thenApplyAsync(searchResults -> buildSearchResult(searchResults, skillName));
    }

    /**
     * This method builds the SkillsSearchResult from the SearchResults in the input.
     *
     * @param searchResults - SearchResults returned from the SearchComponent.
     * @param skillName - Name of the skill.
     * @return SkillsSearchResult built from SearchResults.
     */
    private SkillsSearchResult buildSearchResult(final SearchResults searchResults, final String skillName) {
        return SkillsSearchResult.builder()
                .skill(Skill.builder().name(skillName).build())
                .projectsList(searchResults.getProjectsList())
                .wordStat(searchResults.getWordStat())
                .readingEaseScores(searchResults.getReadingEaseScores())
                .requestId(getUUID())
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
