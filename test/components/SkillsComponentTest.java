package components;

import models.*;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This is a test class for SkillsComponent class.
 */
public class SkillsComponentTest extends WithApplication {

    @Mock
    private SearchComponent searchComponent;

    @InjectMocks
    private SkillsComponent skillsComponent;

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
     * This method tests the response of getSkillSearchResult method.
     */
    @Test
    public void test_getSkillSearchResult_Expect_SkillsSearchResult() throws IOException, NoSuchFieldException, IllegalAccessException {
        final String skillId = RandomStringUtils.randomNumeric(5);
        final String skillName = RandomStringUtils.randomAlphabetic(5);

        Supplier<UUID> uuidSupplier = mock(Supplier.class);
        Field uuidSupplierField = SkillsComponent.class.getDeclaredField("uuidSupplier");
        uuidSupplierField.setAccessible(true);
        uuidSupplierField.set(skillsComponent, uuidSupplier);
        when(uuidSupplier.get()).thenReturn(TestDataProvider.REQUEST_ID);

        final ReadingEaseScores mockReadingEaseScore = ReadingEaseScores.builder()
                .fleschReadingEaseScore(0.0)
                .fkgl(0.0)
                .build();
        final Set<FreelancerProject> mockFreelancerProjects = getFreelancerProjects();
        final List<Project> mockProjectsList = getProjectsListFromFreelancerProjects(mockFreelancerProjects, Collections.emptyMap(), mockReadingEaseScore);

        final SearchResults mockSearchResult = SearchResults.builder()
                .wordStat(Collections.emptyMap())
                .searchTerm(skillId)
                .requestId(TestDataProvider.REQUEST_ID.toString().replace("-", ""))
                .projectsList(mockProjectsList)
                .readingEaseScores(mockReadingEaseScore)
                .build();
        when(searchComponent.searchProjectsForSkill(skillId)).thenReturn(CompletableFuture.completedStage(mockSearchResult));

        final SkillsSearchResult expectedResult = SkillsSearchResult.builder()
                .skill(Skill.builder().name(skillName).build())
                .projectsList(mockProjectsList)
                .wordStat(Collections.emptyMap())
                .readingEaseScores(mockReadingEaseScore)
                .requestId(TestDataProvider.REQUEST_ID.toString().replace("-", ""))
                .build();

        final SkillsSearchResult actualResult = skillsComponent.getSkillSearchResult(skillId, skillName).toCompletableFuture().join();
        assertEquals(expectedResult, actualResult);
    }

}
