package models;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import models.external.FreelancerUserStatus;
import java.util.List;

/**
 * This a view model for Owner Details page.
 */
@Generated
@Data
@Builder
public class OwnerDetailsResults {
    final public String id;
    final public String username;
    final public String suspended;
    final public String closed;
    final public String isActive;
    final public Long forceVerify;
    final public String avatar;
    final public String email;
    final public String reputation;
    final public String profileDescription;
    final public String displayName;
    final public String registrationDate;
    final public String location;
    final public String role;
    final public String firstName;
    final public String lastName;
    final public String primaryCurrencyName;
    final public FreelancerUserStatus status;
    final public List<Project> projectsList;
}
