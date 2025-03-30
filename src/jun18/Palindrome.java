package jun18;

public class Palindrome {
    /***
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * 例如，121 是回文，而 123 不是。
     *
     * 示例 1：
     * 输入：x = 121
     * 输出：true
     *
     * 示例 2：
     * 输入：x = -121
     * 输出：false
     * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     *
     * 示例 3：
     * 输入：x = 10
     * 输出：false
     * 解释：从右向左读, 为 01 。因此它不是一个回文数。
     */
    public boolean isPalindrome(int x) {
        char[] xArray = integer2CharArray(x);
        for (int j = 0; j < (xArray.length / 2); j++) {
            if (xArray[j] != xArray[(xArray.length - 1 - j)]) {
                return false;
            }
        }
        return true;
    }

    public static char[] integer2CharArray(int x) {
        String x1 = String.valueOf(x);
        char[] x2 = new char[x1.length()];

        for (int i = 0; i < x1.length(); i++) {
            x2[i] = x1.charAt(i);
        }

        return x2;
    }
}
