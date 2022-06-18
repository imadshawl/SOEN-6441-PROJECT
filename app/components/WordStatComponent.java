package components;

import com.google.common.base.Splitter;
import lombok.NoArgsConstructor;
import models.external.FreelancerProject;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This component is responsible for calculating word stats for the project.
 * The word stats is essentially a count of the frequency of each word.
 */
@NoArgsConstructor
public class WordStatComponent {

    /**
     * This method converts the input string to a map with the words as key and their frequency as value.
     * All characters in the word are converted to lower case before counting them.
     * First it removes all non alphabet characters and then splits the string into words using white spaces.
     * It then converts the array of words to a stream and then uses the Map::merge method to count the frequency of each word.
     * The returned map is sorted in descending order of frequency of the word.
     *
     * @param description - The string to process.
     * @return Sorted Map of word count of the input string.
     */
    public Map<String, Integer> getWordStat(final String description) {
        String[] words = description.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        Map<String, Integer> wordCountMap = new HashMap<>();
        Arrays.stream(words).forEach(word -> wordCountMap.merge(word, 1, (oldValue, newValue) -> oldValue+1));
        return sortWordCountMap(wordCountMap);
    }

    /**
     * This method take a set of FreelancerProjects and returns a combined wordStat map for the previewDescription
     * from all the projects in the set.
     * It internally calls getWordStat(String description) to get the word count of each project description.
     * The returned map is sorted in descending order of frequency of the word.
     *
     * @param projectList - Set of FreelancerProject to process.
     * @return Sorted Map of word count of the input string.
     */
    public Map<String, Integer> getWordStat(final Set<FreelancerProject> projectList) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        projectList.stream()
                .map(project -> getWordStat(project.getPreviewDescription()))
                .forEach(projectWordCountMap ->
                        projectWordCountMap.entrySet().stream()
                                .forEach(entrySet ->
                                        wordCountMap.merge(entrySet.getKey(), entrySet.getValue(), Integer::sum)));
        return sortWordCountMap(wordCountMap);
    }

    /**
     * This method converts a string in the form of "{a=1,b=2}" to a map.
     * This method is invoked by wordStat method in HomeController to convert wordStat passed in the http request to a Map.
     * The key and value are split based on "=" sign and each element is split based on ",".
     *
     * @param mapString - String to convert to a map.
     * @return Map obtained from the input string.
     */
    public Map<String, String> getMapFromString(final String mapString) {
        return Splitter.on(",")
                .withKeyValueSeparator("=")
                .split(mapString.replaceAll("^.|.$", ""));
    }

    /**
     * This method sorts the provided Map of word count based on the value(frequency) in descending order.
     * It converts the map into a LinkedHashMap to preserve the order.
     *
     * @param wordCountMap - Map to sort
     * @return Sorted LinkedHashMap
     */
    private Map<String, Integer> sortWordCountMap(final Map<String, Integer> wordCountMap){
        return wordCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }
}
