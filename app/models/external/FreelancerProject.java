package models.external;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * This class models Project data type from Freelancer
 * The attributes have @JsonProperty annotation to map the required attributes from the Json response.
 */
@Generated
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FreelancerProject {

    @JsonProperty("id")
    private String id;

    @JsonProperty("owner_id")
    private String ownerId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("preview_description")
    private String previewDescription;

    @JsonProperty("title")
    private String title;

    @JsonProperty("time_submitted")
    private Long timeSubmitted;

    @JsonProperty("type")
    private String type;

    @JsonProperty("jobs")
    private List<FreelancerJob> jobs;
}
