package models;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;

import java.util.List;
import java.util.Map;

/**
 * This a view model for Skills page.
 */
@Generated
@Data
@Builder
public class SkillsSearchResult {

    final public String requestId;
    final public ReadingEaseScores readingEaseScores;
    final public Map<String, Integer> wordStat;
    final public Skill skill;
    final public List<Project> projectsList;
    final public String renderedProjectHtml;
}
