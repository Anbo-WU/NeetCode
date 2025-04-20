package Susquehanna;

/**
 * Given an array ot strings 'words' . find the number ot pairs where either the strings are equal or one sting ends wth another.
 * In other words, find the number of such pairs 'i, j' (0 <=> i < j < words.length) that words[i] is a suffix of words[j], or words[j] is a suffix of words[i].
 * Example:
 * For words = ["back", "backdoor", "gammon", "backgammon", "comeback", "come", "door"], the output should be: solution(words) = 3
 * The relevant pairs are:
 * 1. words[0] = "back" and words[4] = "comeback"
 * 2. words[1] = "backdoor" and words[6] = "door"
 * 3. words[2] = "gammon" and words[3] = "backgammon"
 *
 * For words = ["cba", "a", "a", "b", "ba", "ca"], the output should be: solution(words) = 8
 * The relevant pairs are:
 * 1. words[0] = "cba" and words[1] = "a"
 * 2. words[0] = "cba" and words[2] = "a"
 * 3. words[0] = "cba" and words[4] = "ba"
 * 4. words[1] = "a" and words[2] = "a"
 * 5. words[1] = "a" and words[4] = "ba"
 * 6. words[1] = "a" and words[5] = "ca"
 * 7. words[2] = "a" and words[4] = "ba"
 * 8. words[2] = "a" and words[5] = "ca"
 *
 * Note:
 * For the input, it is an array of strings containing lowercase English letters.
 * Guaranteed constraints:
 * 1 <= words.length <= 10^5
 * 1 <= words[i].length <= 10
 *
 */
public class wordSuffix {
    public static int solution4(String[] words) {
        int count = 0;
        int n = words.length;

        for (int i = 0; i < n; i++) {
            String wi = words[i];
            for (int j = i + 1; j < n; j++) {
                String wj = words[j];

                // Check if wi is a suffix of wj OR wj is a suffix of wi
                if (wi.equals(wj) || wi.endsWith(wj) || wj.endsWith(wi)) {
                    count++;
                }
            }
        }

        return count;
    }
}
