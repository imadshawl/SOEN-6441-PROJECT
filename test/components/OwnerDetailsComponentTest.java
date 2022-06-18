package components;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.OwnerDetailsResults;
import models.Project;
import models.external.FreelancerLocation;
import models.external.FreelancerUser;
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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import static components.TestDataProvider.getFreelanceUser;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * This is the test class for OwnerDetailsComponent class.
 */
public class OwnerDetailsComponentTest extends WithApplication {

    @Mock
    private SearchComponent searchComponent;

    @Mock
    private FreelancerClient freelancerClient;

    @InjectMocks
    private OwnerDetailsComponent ownerDetailsComponent;

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
     * This method verifies the response of getOwnerDetailsResult method.
     * @throws IOException
     */
    @Test
    public void test_getOwnerDetailsResult_Expect_OwnerDetailsResult() throws IOException {
        final String ownerId = RandomStringUtils.randomNumeric(5);
        final FreelancerUser freelancerUser = getFreelanceUser();
        final List<Project> projectList = Arrays.asList(Project.builder().build());
        final OwnerDetailsResults expectedResult = OwnerDetailsResults.builder()
                .id(freelancerUser.getId())
                .username(freelancerUser.getUsername())
                .suspended(freelancerUser.getSuspended())
                .closed(freelancerUser.getClosed())
                .isActive(freelancerUser.getIsActive())
                .forceVerify(freelancerUser.getForceVerify())
                .avatar(freelancerUser.getAvatar())
                .email(freelancerUser.getEmail())
                .reputation(freelancerUser.getReputation())
                .profileDescription(freelancerUser.getProfileDescription())
                .displayName(freelancerUser.getDisplayName())
                .registrationDate(DateTimeUtils.getDateStringFromMillis(freelancerUser.getRegistrationDate()))
                .location(getSimpleLocationString(freelancerUser.getLocation()))
                .role(freelancerUser.getRole())
                .status(freelancerUser.getStatus())
                .firstName(freelancerUser.getFirstName())
                .lastName(freelancerUser.getLastName())
                .primaryCurrencyName(freelancerUser.getPrimaryCurrency().getName())
                .projectsList(projectList)
                .build();
        when(freelancerClient.getUserDetails(ownerId)).thenReturn(CompletableFuture.completedStage(freelancerUser));
        when(searchComponent.searchProjectsForOwner(ownerId)).thenReturn(CompletableFuture.completedStage(projectList));
        final OwnerDetailsResults actualResult =
                ownerDetailsComponent.getOwnerDetailsResult(ownerId).toCompletableFuture().join();
        assertEquals(expectedResult, actualResult);

    }

    /**
     * This is test data provider method used to build the expected location string.
     * @param location - FreelancerLocation
     * @return Expected location string.
     */
    private String getSimpleLocationString(final FreelancerLocation location) {
        return location.getFullAddress() + "\n" + location.getCity() + "\n" + location.getCountry().getName();
    }
}
