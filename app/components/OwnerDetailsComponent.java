package components;

import models.OwnerDetailsResults;
import models.Project;
import models.external.FreelancerLocation;
import models.external.FreelancerUser;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

/**
 * This component is responsible for generating response for Employer Details page.
 * It fetches the employers projects from SearchComponent and then combines it with Employer Details from FreelancerClient.
 * IT builds the OwnerDetailsResults view model.
 */
public class OwnerDetailsComponent {

    final SearchComponent searchComponent;
    final FreelancerClient freelancerClient;

    /**
     * Constructor for this class.
     * The arguments received by this method are injected.
     *
     * @param injectedSearchComponent
     * @param injectedClient
     */
    @Inject
    public OwnerDetailsComponent(final SearchComponent injectedSearchComponent, final FreelancerClient injectedClient) {
        this.searchComponent = injectedSearchComponent;
        this.freelancerClient = injectedClient;
    }

    /**
     * This method takes the owner id and generates the details to be shown on Employer Details page.
     * The method gets the projects posted by the employer from SearchComponent and user details from FreelancerClient.
     * These two responses are combined into OwnerDetailsResults.
     * OwnerDetailsResults is then rendered on the employer details page.
     * This method is called from the ownerDetails method of HomeController.
     *
     * @param ownerId - Id of the employer.
     * @return Returns OwnerDetailsResults wrapped in a CompletionStage.
     */
    public CompletionStage<OwnerDetailsResults> getOwnerDetailsResult(final String ownerId) {
        return freelancerClient.getUserDetails(ownerId)
                .thenComposeAsync(userDetails -> searchComponent.searchProjectsForOwner(ownerId)
                            .thenApplyAsync(searchResults -> buildOwnerDetailsResult(userDetails, searchResults)));
    }

    /**
     * This method combines FreelancerUser and List of Project to build OwnerDetailsResults.
     *
     * @param userDetails - FreelancerUser fetched from FreelancerClient.
     * @param searchResults - list of projects posted by the employer/owner fetched from SearchComponent.
     * @return Returns OwnerDetailsResults obtained from FreelancerUser and List of Project.
     */
    private OwnerDetailsResults buildOwnerDetailsResult(final FreelancerUser userDetails, final List<Project> searchResults) {
        return OwnerDetailsResults.builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .suspended(userDetails.getSuspended())
                .closed(userDetails.getClosed())
                .isActive(userDetails.getIsActive())
                .forceVerify(userDetails.getForceVerify())
                .avatar(userDetails.getAvatar())
                .email(userDetails.getEmail())
                .reputation(userDetails.getReputation())
                .profileDescription(userDetails.getProfileDescription())
                .displayName(userDetails.getDisplayName())
                .registrationDate(DateTimeUtils.getDateStringFromMillis(userDetails.getRegistrationDate()))
                .location(getSimpleLocationString(userDetails.getLocation()))
                .role(userDetails.getRole())
                .status(userDetails.getStatus())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .primaryCurrencyName(userDetails.getPrimaryCurrency().getName())
                .projectsList(searchResults)
                .build();
    }

    /**
     * This method build a simple location string from FreelancerLocation from FreelancerUser.
     *
     * @param location - FreelancerLocation fetched from FreelancerUser.
     * @return Returns simple location string.
     */
    private String getSimpleLocationString(final FreelancerLocation location) {
        return location.getFullAddress() + "\n" + location.getCity() + "\n" + location.getCountry().getName();
    }
}
