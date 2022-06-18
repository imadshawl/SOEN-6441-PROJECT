package models.external;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * This class models Location data type from Freelancer
 * The attributes have @JsonProperty annotation to map the required attributes from the Json response.
 */
@Generated
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FreelancerLocation {

    @JsonProperty("country")
    private FreelancerCountry country;

    @JsonProperty("city")
    private String city;

    @JsonProperty("full_address")
    private String fullAddress;
}
