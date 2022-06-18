package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;

/**
 * This a view model for modeling Skills(or Jobs).
 */
@Generated
@Builder
@Data
@AllArgsConstructor
public class Skill {

    final public String id;
    final public String name;
}
