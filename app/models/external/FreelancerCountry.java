package models.external;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * This class models Country data type from Freelancer
 * The attributes have @JsonProperty annotation to map the required attributes from the Json response.
 */
@Generated
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FreelancerCountry {

    @JsonProperty("name")
    private String name;
}
