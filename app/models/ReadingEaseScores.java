package models;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;

/**
 * This is view model used to model the 2 reading ease indexes.
 * 1. Flesch Readability Index
 * 2. Flesch-Kincaid Grade Level (fklg)
 */
@Generated
@Builder
@Data
public class ReadingEaseScores {
    final public Double fkgl;
    final public Double fleschReadingEaseScore;
}
