package controllers;

import components.OwnerDetailsComponent;
import components.SearchComponent;
import components.SkillsComponent;
import components.WordStatComponent;
import models.*;
import models.external.FreelancerUserStatus;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import play.Application;
import play.data.DynamicForm;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.mvc.Http.Status.OK;

/**
 * This is a test class for HomeController class.
 */
public class HomeControllerTest extends WithApplication {

    @Mock
    private SearchComponent searchComponent;

    @Mock
    private OwnerDetailsComponent ownerDetailsComponent;

    @Mock
    private SkillsComponent skillsComponent;

    @Mock
    private play.data.FormFactory formFactory;

    @Mock
    private WordStatComponent wordStatComponent;

    @InjectMocks
    private HomeController homeController;

    private static ReadingEaseScores DUMMY_READING_EASE_SCORE = ReadingEaseScores.builder()
            .fleschReadingEaseScore(10.0)
            .fkgl(0.0)
            .build();
    private static String DUMMY_REQUEST_ID = UUID.randomUUID().toString();

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
     * This method tests the response of index method.
     * We verify the status and content type of the response
     */
    @Test
    public void test_index_path() {
        Result result = homeController.index();
        assertEquals(OK, result.status());
        assertEquals("text/html", result.contentType().get());
    }

    /**
     * This method tests the response of search method.
     * We verify the status and content type of the response
     */
    @Test
    public void test_search_path() {
        final String searchTerm = "testTerm";
        final SearchResults searchResult = SearchResults.builder()
                .readingEaseScores(DUMMY_READING_EASE_SCORE)
                .searchTerm(searchTerm)
                .requestId(DUMMY_REQUEST_ID)
                .projectsList(Collections.emptyList())
                .wordStat(Collections.emptyMap())
                .build();
        when(searchComponent.searchProjects(searchTerm)).thenReturn(CompletableFuture.completedStage(searchResult));
        CompletionStage<Result> result = homeController.search(searchTerm);
        final Result actualResult = result.toCompletableFuture().join();
        assertEquals("text/html", actualResult.contentType().get());
        assertEquals(OK, actualResult.status());
    }

    /**
     * This method tests the response of skill method.
     * We verify the status and content type of the response
     */
    @Test
    public void test_skill_path() {
        final String skillId = "skillId";
        final String skillName = "skillName";
        final SkillsSearchResult expectedSkillsSearchResult = SkillsSearchResult.builder()
                .readingEaseScores(DUMMY_READING_EASE_SCORE)
                .skill(Skill.builder().name(skillName).id(skillId).build())
                .wordStat(Collections.emptyMap())
                .projectsList(Collections.emptyList())
                .requestId(DUMMY_REQUEST_ID)
                .build();
        when(skillsComponent.getSkillSearchResult(skillId, skillName))
                .thenReturn(CompletableFuture.completedStage(expectedSkillsSearchResult));
        CompletionStage<Result> result = homeController.skills(skillId, skillName);
        final Result actualResult = result.toCompletableFuture().join();
        assertEquals("text/html", actualResult.contentType().get());
        assertEquals(OK, actualResult.status());
    }

    /**
     * This method tests the response of wordStat method.
     * We verify the status and content type of the response
     */
    @Test
    public void test_wordStat_path() {
        final Http.Request request = mock(Http.Request.class);
        final DynamicForm mockForm = mock(DynamicForm.class);
        when(mockForm.get("wordCountMap")).thenReturn("");
        when(formFactory.form()).thenReturn(mockForm);
        when(mockForm.bindFromRequest(request)).thenReturn(mockForm);
        when(wordStatComponent.getMapFromString(anyString())).thenReturn(Collections.emptyMap());
        final Result actualResult = homeController.wordStat(request);
        assertEquals("text/html", actualResult.contentType().get());
        assertEquals(OK, actualResult.status());
    }

    /**
     * This method tests the response of ownerDetails method.
     * We verify the status and content type of the response
     */
    @Test
    public void test_ownerDetails_path() {
        final String mockOwnerId = "ownerId";
        final OwnerDetailsResults results = OwnerDetailsResults.builder()
                .id(mockOwnerId)
                .username(RandomStringUtils.randomAlphabetic(10))
                .suspended(RandomStringUtils.randomAlphabetic(10))
                .closed(RandomStringUtils.randomAlphabetic(10))
                .isActive(RandomStringUtils.randomAlphabetic(10))
                .forceVerify(Long.getLong(RandomStringUtils.randomNumeric(5)))
                .avatar(RandomStringUtils.randomAlphabetic(10))
                .email(RandomStringUtils.randomAlphabetic(10))
                .profileDescription(RandomStringUtils.randomAlphabetic(10))
                .projectsList(Collections.emptyList())
                .status(FreelancerUserStatus.builder().build())
                .build();
        when(ownerDetailsComponent.getOwnerDetailsResult(mockOwnerId))
                .thenReturn(CompletableFuture.completedStage(results));
        CompletionStage<Result> result = homeController.ownerDetails(mockOwnerId);
        final Result actualResult = result.toCompletableFuture().join();
        assertEquals("text/html", actualResult.contentType().get());
        assertEquals(OK, actualResult.status());
    }

    /**
     * This method tests the response of javascriptRoutes method.
     * We verify the status and content type of the response
     */
    @Test
    public void test_javascriptRoutes_path() {
        final Http.Request request = mock(Http.Request.class);
        when(request.host()).thenReturn("localhost");
        Result result = homeController.javascriptRoutes(request);
        assertEquals(OK, result.status());
        assertEquals("application/javascript", result.contentType().get());
    }
}
