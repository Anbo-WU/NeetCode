package jun19;

import java.util.Arrays;

public class CommonPrefixM2 {
    /***
     * Write a function to find the longest common prefix string amongst an array of strings.
     * If there is no common prefix, return an empty string "".
     *
     * Example 1:
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     *
     * Example 2:
     * Input: strs = ["dog","racecar","car"]
     * Output: ""
     * Explanation: There is no common prefix among the input strings.
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = CommonPrefix(prefix, strs[i]);

            if (prefix.length() == 0) {
                break;
            } // this 'if' decision can be ignored. But it may cause time waste for some unnecessarily comparison.
        }
        return prefix;
    }

    public String CommonPrefix(String str1, String str2) {
        String[] str = new String[]{str1, str2};
        Arrays.sort(str);

        for (int i = 0; i < str[0].length(); i++) {
            if (str[0].charAt(i) != str[1].charAt(i)) {
                return str[0].substring(0,i);
            }
        }
        return str[0];
    }
}

// Learning Outcome:
// 1. overload the method? Or just change to a different name?
// 2. Some recursion ideology.

