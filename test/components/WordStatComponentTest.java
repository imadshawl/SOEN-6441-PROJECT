package components;

import models.external.FreelancerProject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static components.TestDataProvider.DUMMY_DESCRIPTION;
import static components.TestDataProvider.DUMMY_DESCRIPTION_1;
import static org.junit.Assert.assertEquals;

/**
 * This is a test class for WordStatComponent class.
 */
public class WordStatComponentTest extends WithApplication {

    @InjectMocks
    private WordStatComponent wordStatComponent;

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
     * This method tests the response of getWordStatForString method.
     */
    @Test
    public void test_getWordStatForString_Expect_MapOfWordFrequency() {
        Map<String, Integer> expectedWordCount = new HashMap<>();
        expectedWordCount.put("firstword", 2);
        expectedWordCount.put("secondword", 2);
        expectedWordCount.put("thirdword", 1);
        final Map<String, Integer> actualWordCount = wordStatComponent.getWordStat(DUMMY_DESCRIPTION);
        assertEquals(expectedWordCount.size(), actualWordCount.size());
        assertEquals(expectedWordCount.get("firstword"), actualWordCount.get("firstword"));
        assertEquals(expectedWordCount.get("secondword"), actualWordCount.get("secondword"));
        assertEquals(expectedWordCount.get("thirdword"), actualWordCount.get("thirdword"));
    }

    /**
     * This method tests the response of getWordStatForProjectsList method.
     */
    @Test
    public void test_getWordStatForProjectsList_Expect_MapOfWordFrequency() {
        final Set<FreelancerProject> mockProjects = new HashSet<>();
        mockProjects.add(FreelancerProject.builder().previewDescription(DUMMY_DESCRIPTION).build());
        mockProjects.add(FreelancerProject.builder().previewDescription(DUMMY_DESCRIPTION_1).build());
        Map<String, Integer> expectedWordCount = new HashMap<>();
        expectedWordCount.put("firstword", 3);
        expectedWordCount.put("secondword", 3);
        expectedWordCount.put("thirdword", 2);

        final Map<String, Integer> actualWordCount = wordStatComponent.getWordStat(mockProjects);
        assertEquals(expectedWordCount.size(), actualWordCount.size());
        assertEquals(expectedWordCount.get("firstword"), actualWordCount.get("firstword"));
        assertEquals(expectedWordCount.get("secondword"), actualWordCount.get("secondword"));
        assertEquals(expectedWordCount.get("thirdword"), actualWordCount.get("thirdword"));
    }

    /**
     * This method tests the response of getMapFromString method.
     */
    @Test
    public void test_getMapFromString_Expect_MapOfWordFrequency() {
        final String mapString = "{a=1,b=2,c=3}";
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("a", "1");
        expectedMap.put("b", "2");
        expectedMap.put("c", "3");

        final Map<String, String> actualMap = wordStatComponent.getMapFromString(mapString);
        assertEquals(expectedMap.size(), actualMap.size());
        assertEquals(expectedMap.get("a"), actualMap.get("a"));
        assertEquals(expectedMap.get("b"), actualMap.get("b"));
        assertEquals(expectedMap.get("c"), actualMap.get("c"));
    }
}
