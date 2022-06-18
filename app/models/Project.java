package models;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;

import java.util.List;
import java.util.Map;

/**
 * This is a view model for modeling Project data.
 */
@Generated
@Data
@Builder
public class Project {

    final public String ownerId;
    final public String projectId;
    final public String dateSubmitted;
    final public String timeSubmitted;
    final public String title;
    final public String description;
    final public String projectType;
    final public List<Skill> skillsList;
    final public Map<String, Integer> wordStat;
    final public ReadingEaseScores readingEaseScores;
}
