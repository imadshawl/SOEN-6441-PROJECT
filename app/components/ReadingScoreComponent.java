package components;

import eu.crydee.syllablecounter.SyllableCounter;
import models.external.FreelancerProject;
import models.ReadingEaseScores;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * This component is responsible for calculating the reading ease scores.
 * The calculates 2 scores:
 * 1. Flesch Readability Index
 * 2. Flesch-Kincaid Grade Level (fklg)
 */
public class ReadingScoreComponent {

    private static final String SENTENCE_REGEX = "[^.!?:;\\s][^.!?:;]*(?:[.!?:;](?!['\"]?\\s|$)[^.!?:;]*)*[.!?:;]?['\"]?(?=\\s|$)";
    private static final String WORD_REGEX = "\\w+";
    private static final DecimalFormat decimalFormat = new DecimalFormat("####0.00");

    /**
     * This method calculates the average of ReadingEaseScores for all the projects in the input set.
     * This method internally calls ReadingScoreComponent::getReadingEaseScores to calculate the scores for individual projects.
     * It uses the project description and title as input for the calculation.
     *
     * @param projectList - Set of FreelancerProject for which we want to calculate average ReadingEaseScore
     * @return Average of ReadingEaseScore for all projects in the set.
     */
    public ReadingEaseScores getAverageReadingEaseScores(final Set<FreelancerProject> projectList) {
        final ReadingEaseScores initialScore = ReadingEaseScores.builder()
                .fleschReadingEaseScore(0.0)
                .fkgl(0.0)
                .build();
        ReadingEaseScores returnValue = projectList.stream()
                .map(project -> getReadingEaseScores(project.getTitle(), project.getDescription()))
                .reduce(initialScore, (first, second) -> readingEaseScoresAccumulator(first, second));
        return ReadingEaseScores.builder()
                .fleschReadingEaseScore(getFormattedDouble(returnValue.getFleschReadingEaseScore()/projectList.size()))
                .fkgl(getFormattedDouble(returnValue.getFkgl()/projectList.size()))
                .build();
    }

    /**
     * This method take two strings. One for title of the project and the other for the description and computes the following scores for it:
     * 1. Flesch Readability Index
     * 2. Flesch-Kincaid Grade Level (fklg)
     *
     * The method first decomposes the combined title and paragraph into a list of sentences.
     * Next the list of sentences are converted into a list of words.
     * For this list of words we get the count of total number of syllables.
     *
     * The method then uses the above computed values to calculate the 2 scores based on the following formulas:
     * Flesch Readability Index formula: (206.835) - 84.6 * (Syllables/Words) - 1.015 * (Words/Sentences)
     * FKGL formula: 0.39 * (Words/Sentences) + 11.8 * (Syllables/Words) - 15.59
     *
     * @param title - Title of the project
     * @param description - Description of the project
     * @return ReadingEaseScores for the input strings.
     */
    public ReadingEaseScores getReadingEaseScores(final String title, final String description) {
        final String content = title + ". " + description;
        final List<String> sentences = getSentences(content);
        final List<String> words = getWordsFromSentences(sentences);
        final Integer syllablesCount = countNumberOfSyllablesInWords(words);
        final Double wordsPerSentence = (double) words.size() / (double) sentences.size();
        final Double syllablesPerSentence = (double) syllablesCount / (double) words.size();
        double fkgl = (0.39 * wordsPerSentence) + (11.8 * syllablesPerSentence) - 15.59;
        double fleschReadingEaseScore = 206.835 - (1.015 * wordsPerSentence) - (84.6 * syllablesPerSentence);
        return ReadingEaseScores.builder()
                .fkgl(getFormattedDouble(fkgl))
                .fleschReadingEaseScore(getFormattedDouble(fleschReadingEaseScore))
                .build();
    }

    /**
     * Accumulator to add up the ReadingEaseScores.
     *
     * @param first - ReadingEaseScores
     * @param second - ReadingEaseScores
     * @return Returns the sum of the 2 ReadingEaseScores
     */
    private ReadingEaseScores readingEaseScoresAccumulator(final ReadingEaseScores first, final ReadingEaseScores second) {
        return ReadingEaseScores.builder()
                .fleschReadingEaseScore(first.getFleschReadingEaseScore() + second.getFleschReadingEaseScore())
                .fkgl(first.getFkgl() + second.getFkgl())
                .build();
    }

    /**
     * This method converts the input string into a list of sentences using the SENTENCE_REGEX regex.
     *
     * @param paragraph - Paragraph to be converted into sentences.
     * @return Returns List of sentences obtained from the input string.
     */
    private List<String> getSentences(final String paragraph) {
        Pattern re = Pattern.compile(SENTENCE_REGEX, Pattern.MULTILINE | Pattern.COMMENTS);
        return getStringListFromMatcher(re.matcher(paragraph));
    }

    /**
     * This method splits the List of strings to words based on whitespaces.
     * The input list of String are expected to we sentences.
     *
     * @param sentences - Input list of sentences.
     * @return List of words in all the sentences in the input.
     */
    private List<String> getWordsFromSentences(final List<String> sentences) {
        Pattern re = Pattern.compile(WORD_REGEX, Pattern.MULTILINE | Pattern.COMMENTS);
        return sentences.stream()
                .map(sentence -> getStringListFromMatcher(re.matcher(sentence)))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * The method count the number of syllables in the list of words provided in the input.
     *
     * @param words - List of word for which syllable count need to returned.
     * @return Returns the sum of number of syllables in all the words in the input list.
     */
    private Integer countNumberOfSyllablesInWords(final List<String> words) {
        SyllableCounter sc = new SyllableCounter();
        return words.stream()
                .map(word -> sc.count(word))
                .mapToInt(Integer::intValue)
                .sum();
    }

    /**
     * This method converts the matcher obtained from the regex matching to a list of strings.
     * It uses Spliterator to convert a simple matcher to a Stream and then uses the streams map method to get all the matched Strings.
     * The stream is then collected into a list and returned.
     *
     * @param matcher - Matcher obtained from a regex match.
     * @return Returns a list of Strings that were matched in the input Matcher.
     */
    private List<String> getStringListFromMatcher(final Matcher matcher) {
        Spliterator<MatchResult> spliterator =
                new Spliterators.AbstractSpliterator<>(Long.MAX_VALUE, Spliterator.ORDERED|Spliterator.NONNULL) {
                    @Override
                    public boolean tryAdvance(Consumer action) {
                        if(!matcher.find()) return false;
                        action.accept(matcher.toMatchResult());
                        return true;
                    }};
        return StreamSupport.stream(spliterator, false)
                .map(matchResult -> matchResult.group())
                .collect(Collectors.toList());
    }

    /**
     * This method converts a Double value with unspecified number of decimal places to one with only 2 decimal places.
     * For eg.: 3.1415926 to 3.14
     * @param number - Double to format
     * @return Returns formatted double
     */
    private Double getFormattedDouble(final Double number) {
        return Double.valueOf(decimalFormat.format(number));
    }
}
