package components;

import models.ReadingEaseScores;
import models.external.FreelancerProject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;

/**
 * This is the test class for ReadingScoreComponent class.
 */
public class ReadingScoreComponentTest extends WithApplication {

    @InjectMocks
    private ReadingScoreComponent readingScoreComponent;

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
     * This method verifies the response of getReadingEaseScores method.
     */
    @Test
    public void test_getReadingEaseScores_Expect_ReadingEaseScore() {
        final String title = "This is a dummy Title";
        final String description = "This is a dummy description";
        final ReadingEaseScores expectedReadingEaseScore = ReadingEaseScores.builder()
                .fkgl(2.88).fleschReadingEaseScore(83.32).build();
        final ReadingEaseScores actualReadingEaseScores =
                readingScoreComponent.getReadingEaseScores(title, description);
        assertEquals(expectedReadingEaseScore, actualReadingEaseScores);
    }

    /**
     * This method verifies the response of getAverageReadingEaseScores method.
     */
    @Test
    public void test_getAverageReadingEaseScores_Expect_ReadingEaseScore() {
        final String title = "This is a dummy Title";
        final String description = "This is a dummy description";
        final ReadingEaseScores expectedReadingEaseScore = ReadingEaseScores.builder()
                .fkgl(2.88).fleschReadingEaseScore(83.32).build();
        final FreelancerProject freelancerProject = FreelancerProject.builder()
                .description(description)
                .title(title)
                .build();
        final Set<FreelancerProject> projects = new HashSet<>();
        projects.add(freelancerProject);
        final ReadingEaseScores actualReadingEaseScores =
                readingScoreComponent.getAverageReadingEaseScores(projects);
        assertEquals(expectedReadingEaseScore, actualReadingEaseScores);
    }
}
