package components;

import models.Project;
import models.ReadingEaseScores;
import models.SearchResults;
import models.external.FreelancerProject;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static components.TestDataProvider.getFreelancerProjects;
import static components.TestDataProvider.getProjectsListFromFreelancerProjects;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is the test class for SearchComponent class.
 */
public class SearchComponentTest extends WithApplication {

    @Mock
    private FreelancerClient client;

    @Mock
    private WordStatComponent wordStatComponent;

    @Mock
    private ReadingScoreComponent readingScoreComponent;

    @InjectMocks
    private SearchComponent searchComponent;

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    /**
     * Method executed before every test invocation.
     * It does the mockito initialization for the test.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * This method verifies the response of searchProjects method.
     */
    @Test
    public void test_searchProjects_Expect_SearchResults() throws IOException, NoSuchFieldException, IllegalAccessException {
        final String searchTerm = RandomStringUtils.randomAlphabetic(10);

        Supplier<UUID> uuidSupplier = mock(Supplier.class);
        Field uuidSupplierField = SearchComponent.class.getDeclaredField("uuidSupplier");
        uuidSupplierField.setAccessible(true);
        uuidSupplierField.set(searchComponent, uuidSupplier);
        when(uuidSupplier.get()).thenReturn(TestDataProvider.REQUEST_ID);
        final Set<FreelancerProject> mockFreelancerProjects = getFreelancerProjects();
        final ReadingEaseScores mockReadingEaseScore = ReadingEaseScores.builder()
                .fleschReadingEaseScore(0.0)
                .fkgl(0.0)
                .build();
        when(wordStatComponent.getWordStat(anyString())).thenReturn(Collections.emptyMap());
        when(wordStatComponent.getWordStat(any(Set.class))).thenReturn(Collections.emptyMap());
        when(readingScoreComponent.getAverageReadingEaseScores(any())).thenReturn(mockReadingEaseScore);
        when(readingScoreComponent.getReadingEaseScores(anyString(), anyString())).thenReturn(mockReadingEaseScore);
        when(client.getSearchResults(searchTerm)).thenReturn(CompletableFuture.completedStage(mockFreelancerProjects));

        final SearchResults expectedSearchResults = SearchResults.builder()
                .wordStat(Collections.emptyMap())
                .searchTerm(searchTerm)
                .requestId(TestDataProvider.REQUEST_ID.toString().replace("-", ""))
                .projectsList(getProjectsListFromFreelancerProjects(mockFreelancerProjects, Collections.emptyMap(), mockReadingEaseScore))
                .readingEaseScores(mockReadingEaseScore)
                .build();

        final SearchResults actualSearchResults = searchComponent.searchProjects(searchTerm).toCompletableFuture().join();
        assertEquals(expectedSearchResults, actualSearchResults);

    }

    /**
     * This method verifies the response of searchProjectsForSkill method.
     */
    @Test
    public void test_searchProjectsForSkill_Expect_SearchResults() throws IOException, NoSuchFieldException, IllegalAccessException {
        final String skillId = RandomStringUtils.randomNumeric(5);

        Supplier<UUID> uuidSupplier = mock(Supplier.class);
        Field uuidSupplierField = SearchComponent.class.getDeclaredField("uuidSupplier");
        uuidSupplierField.setAccessible(true);
        uuidSupplierField.set(searchComponent, uuidSupplier);
        when(uuidSupplier.get()).thenReturn(TestDataProvider.REQUEST_ID);
        final Set<FreelancerProject> mockFreelancerProjects = getFreelancerProjects();
        final ReadingEaseScores mockReadingEaseScore = ReadingEaseScores.builder()
                .fleschReadingEaseScore(0.0)
                .fkgl(0.0)
                .build();
        when(wordStatComponent.getWordStat(anyString())).thenReturn(Collections.emptyMap());
        when(wordStatComponent.getWordStat(any(Set.class))).thenReturn(Collections.emptyMap());
        when(readingScoreComponent.getAverageReadingEaseScores(any())).thenReturn(mockReadingEaseScore);
        when(readingScoreComponent.getReadingEaseScores(anyString(), anyString())).thenReturn(mockReadingEaseScore);
        when(client.getSearchResultForSkill(skillId)).thenReturn(CompletableFuture.completedStage(mockFreelancerProjects));

        final SearchResults expectedSearchResults = SearchResults.builder()
                .wordStat(Collections.emptyMap())
                .searchTerm(skillId)
                .requestId(TestDataProvider.REQUEST_ID.toString().replace("-", ""))
                .projectsList(getProjectsListFromFreelancerProjects(mockFreelancerProjects, Collections.emptyMap(), mockReadingEaseScore))
                .readingEaseScores(mockReadingEaseScore)
                .build();

        final SearchResults actualSearchResults = searchComponent.searchProjectsForSkill(skillId).toCompletableFuture().join();
        assertEquals(expectedSearchResults, actualSearchResults);
    }

    /**
     * This method verifies the response of searchProjectsForOwner method.
     */
    @Test
    public void test_searchProjectsForOwner_Expect_ProjectsList() throws IOException, NoSuchFieldException, IllegalAccessException {
        final String ownerId = RandomStringUtils.randomNumeric(5);

        Supplier<UUID> uuidSupplier = mock(Supplier.class);
        Field uuidSupplierField = SearchComponent.class.getDeclaredField("uuidSupplier");
        uuidSupplierField.setAccessible(true);
        uuidSupplierField.set(searchComponent, uuidSupplier);
        when(uuidSupplier.get()).thenReturn(TestDataProvider.REQUEST_ID);
        final Set<FreelancerProject> mockFreelancerProjects = getFreelancerProjects();
        final ReadingEaseScores mockReadingEaseScore = ReadingEaseScores.builder()
                .fleschReadingEaseScore(0.0)
                .fkgl(0.0)
                .build();
        when(wordStatComponent.getWordStat(anyString())).thenReturn(Collections.emptyMap());
        when(wordStatComponent.getWordStat(any(Set.class))).thenReturn(Collections.emptyMap());
        when(readingScoreComponent.getAverageReadingEaseScores(any())).thenReturn(mockReadingEaseScore);
        when(readingScoreComponent.getReadingEaseScores(anyString(), anyString())).thenReturn(mockReadingEaseScore);
        when(client.getSearchResultForOwnerId(ownerId)).thenReturn(CompletableFuture.completedStage(mockFreelancerProjects));

        final List<Project> expectedSearchResults =
                getProjectsListFromFreelancerProjects(mockFreelancerProjects, Collections.emptyMap(), mockReadingEaseScore);

        final List<Project> actualSearchResults = searchComponent.searchProjectsForOwner(ownerId).toCompletableFuture().join();
        assertEquals(expectedSearchResults, actualSearchResults);
    }

}
