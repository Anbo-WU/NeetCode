package jun18;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
public class Anagram {
/*** Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.
    An anagram is a string that contains the exact same characters as another string, but the order of the characters
    can be different.

    Example 1:
    Input: s = "racecar", t = "carrace"
    Output: true

    Example 2:
    Input: s = "jar", t = "jam"
    Output: false

    Constraints:
    s and t consist of lowercase English letters.
 */
    public boolean isAnagram(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();

        Arrays.sort(s1);
        Arrays.sort(t1);

        if (Arrays.compare(s1,t1) != 0) {
            return false;
        }
        return true;
    }

}
