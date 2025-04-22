package Susquehanna;

import java.util.*;

/**
 * This class is another solution to the same question as the one from <wordSuffix>. Two solutions are all right but only
 * this one can meet the requirement of time complexity.
 *
 * In order to lower the time complexity, I ameliorated the algorithm as below:
 * 1. sort the strings in advance
 * 2. use the suffix_map mapping
 * 3. exclusively process the high-frequency strings
 *
 * Now the whole time complexity is approximately O(n * k), while k is the maximal length of the string(10 for this question)
 */
public class wordSuffix2 {
    public static long solution4(String[] words) {
        Map<String, Integer> freqMap = new HashMap<>();
        long count = 0;

        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        Set<String> uniqueSet = new HashSet<>(Arrays.asList(words));
        List<String> uniqueWords = new ArrayList<>(uniqueSet);
        uniqueWords.sort(Comparator.comparingInt(String::length));

        Set<String> seen = new HashSet<>();
        for (String w : uniqueWords) {
            int freq = freqMap.get(w);

            count += (long) freq * (freq - 1) / 2;

            for (String seenStr : seen) {
                if (seenStr.endsWith(w)) {
                    count += (long) freq * freqMap.get(seenStr);
                }
                if (w.endsWith(seenStr)) {
                    count += (long) freq * freqMap.get(seenStr);
                }
            }

            seen.add(w);
        }

        return count;
    }
}
