package models;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;

import java.util.List;
import java.util.Map;

/**
 * This a view model for modeling search results.
 */
@Generated
@Data
@Builder
public class SearchResults {
    final public String requestId;
    final public String searchTerm;
    final public ReadingEaseScores readingEaseScores;
    final public List<Project> projectsList;
    final public Map<String, Integer> wordStat;
}
