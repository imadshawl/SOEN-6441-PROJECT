package models.external;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * This class models User data type from Freelancer
 * The attributes have @JsonProperty annotation to map the required attributes from the Json response.
 */
@Generated
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FreelancerUser {

    @JsonProperty("id")
    private String id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("suspended")
    private String suspended;

    @JsonProperty("closed")
    private String closed;

    @JsonProperty("is_active")
    private String isActive;

    @JsonProperty("force_verify")
    private Long forceVerify;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("email")
    private String email;

    @JsonProperty("reputation")
    private String reputation;

    @JsonProperty("profile_description")
    private String profileDescription;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("registration_date")
    private Long registrationDate;

    @JsonProperty("location")
    private FreelancerLocation location;

    @JsonProperty("role")
    private String role;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("status")
    private FreelancerUserStatus status;

    @JsonProperty("primary_currency")
    private FreelancerCurrency primaryCurrency;
}
