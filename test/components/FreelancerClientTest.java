package components;

import com.fasterxml.jackson.databind.JsonNode;
import models.external.FreelancerProject;
import models.external.FreelancerUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;
import play.test.WithApplication;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import static components.TestDataProvider.*;
import static components.TestDataProvider.getFreelancerProjectsJsonNode;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This a test class for FreelancerClient class.
 */
public class FreelancerClientTest extends WithApplication {

    @Mock
    private WSClient wsClient;

    @InjectMocks
    private FreelancerClient freelancerClient;

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
     * This test verifies the results returned from getUserDetails method.
     * @throws IOException
     */
    @Test
    public void test_getUserDetails_Expect_FreelancerUser() throws IOException {
        final String ownerId = RandomStringUtils.randomNumeric(5);
        final WSResponse response = mock(WSResponse.class);
        final FreelancerUser expectedUser = getFreelanceUser();
        when(response.asJson()).thenReturn(getFreelanceUserJsonNode());
        final WSRequest request = mock(WSRequest.class);
        when(wsClient.url(anyString())).thenReturn(request);
        when(request.addQueryParameter(anyString(), anyString())).thenReturn(request);
        when(request.addHeader(anyString(), any())).thenReturn(request);
        when(request.setRequestTimeout(any(Duration.class))).thenReturn(request);
        when(request.get()).thenReturn(CompletableFuture.completedStage(response));
        FreelancerUser actualUser = freelancerClient.getUserDetails(ownerId).toCompletableFuture().join();
        assertEquals(expectedUser, actualUser);
    }

    /**
     * This test verifies the results returned from getSearchResultForOwnerId method.
     * @throws IOException
     */
    @Test
    public void test_getSearchResultForOwnerId_Expect_SetOfFreelancerProjects() throws IOException {
        final String ownerId = RandomStringUtils.randomNumeric(5);
        final JsonNode jsonNode = getFreelancerProjectsJsonNode();
        final WSResponse response = mock(WSResponse.class);
        Set<FreelancerProject> expectedProjects = getFreelancerProjects();
        when(response.asJson()).thenReturn(jsonNode);
        final WSRequest request = mock(WSRequest.class);
        when(wsClient.url(anyString())).thenReturn(request);
        when(request.addQueryParameter(anyString(), anyString())).thenReturn(request);
        when(request.addHeader(anyString(), any())).thenReturn(request);
        when(request.setRequestTimeout(any(Duration.class))).thenReturn(request);
        when(request.get()).thenReturn(CompletableFuture.completedStage(response));
        Set<FreelancerProject> actualProjects =
                freelancerClient.getSearchResultForOwnerId(ownerId).toCompletableFuture().join();
        assertEquals(expectedProjects, actualProjects);
    }

    /**
     * This test verifies the results returned from getSearchResultForSkill method.
     * @throws IOException
     */
    @Test
    public void test_getSearchResultForSkill_Expect_SetOfFreelancerProjects() throws IOException {
        final String skillId = RandomStringUtils.randomNumeric(5);
        final WSResponse response = mock(WSResponse.class);
        Set<FreelancerProject> expectedProjects = getFreelancerProjects();
        when(response.asJson()).thenReturn(getFreelancerProjectsJsonNode());
        final WSRequest request = mock(WSRequest.class);
        when(wsClient.url(anyString())).thenReturn(request);
        when(request.addQueryParameter(anyString(), anyString())).thenReturn(request);
        when(request.addHeader(anyString(), any())).thenReturn(request);
        when(request.setRequestTimeout(any(Duration.class))).thenReturn(request);
        when(request.get()).thenReturn(CompletableFuture.completedStage(response));
        Set<FreelancerProject> actualProjects =
                freelancerClient.getSearchResultForSkill(skillId).toCompletableFuture().join();
        assertEquals(expectedProjects, actualProjects);
    }

    /**
     * This test verifies the results returned from getSearchResults method.
     * @throws IOException
     */
    @Test
    public void test_getSearchResults_Expect_SetOfFreelancerProjects() throws IOException {
        final String searchTerm = RandomStringUtils.randomAlphabetic(5);
        final WSResponse response = mock(WSResponse.class);
        Set<FreelancerProject> expectedProjects = getFreelancerProjects();
        when(response.asJson()).thenReturn(getFreelancerProjectsJsonNode());
        final WSRequest request = mock(WSRequest.class);
        when(wsClient.url(anyString())).thenReturn(request);
        when(request.addQueryParameter(anyString(), anyString())).thenReturn(request);
        when(request.addHeader(anyString(), any())).thenReturn(request);
        when(request.setRequestTimeout(any(Duration.class))).thenReturn(request);
        when(request.get()).thenReturn(CompletableFuture.completedStage(response));
        Set<FreelancerProject> actualProjects =
                freelancerClient.getSearchResults(searchTerm).toCompletableFuture().join();
        assertEquals(expectedProjects, actualProjects);
    }

}
