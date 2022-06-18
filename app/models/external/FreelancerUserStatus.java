package models.external;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * This class models UserStatus data type from Freelancer
 * The attributes have @JsonProperty annotation to map the required attributes from the Json response.
 */
@Generated
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FreelancerUserStatus {

    @JsonProperty("payment_verified")
    public Boolean paymentVerified;

    @JsonProperty("email_verified")
    public Boolean emailVerified;

    @JsonProperty("deposit_made")
    public Boolean depositMade;

    @JsonProperty("profile_complete")
    public Boolean profileComplete;

    @JsonProperty("phone_verified")
    public Boolean phoneVerified;

    @JsonProperty("identity_verified")
    public Boolean identityVerified;

    @JsonProperty("facebook_connected")
    public Boolean facebookConnected;

    @JsonProperty("freelancer_verified_user")
    public Boolean freelancerVerifiedUser;

    @JsonProperty("linkedin_connected")
    public Boolean linkedinConnected;
}
