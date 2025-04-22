package Susquehanna;

/**
 * Given an array of integers numbers, compare the sum of elements on even positions against the sum of elements on odd positions (0-based).
 * Return "even" if the sum of elements on even positions is greater, "odd" if the sum of elements on odd positions is greater, or "equal" if both sums are equal.
 *
 * Note: You are not expected to provide the most optimal solution, but a solution with time complexity not worse than O(numbers.length2) will fit within the execution time limit.
 *
 * Example:
 * For numbers = [1, 2, 3, 4, 5], the output should be solution(numbers) = "even".
 *
 * Explanation:
 * Sum of elements on even positions is 1 + 3 + 5 = 9.
 * Sum of elements on odd positions is 2 + 4 = 6.
 * 9 > 6, so the expected output is "even".
 * For numbers = [-1, 4, 3, -2], the output should be solution(numbers) = "equal".
 *
 * Explanation:
 * Sum of elements on even positions is (-1) + 3 = 2.
 * Sum of elements on odd positions is 4 + (-2) = 2.
 * 2 = 2, so the expected output is "equal".
 *
 * Input/Output
 * [execution time limit] 3 seconds (java)
 * [memory limit] 1 GB
 * [input] array.integer numbers
 * An array of integers.
 *
 * Guaranteed constraints:
 * 0 ≤ numbers.length ≤ 1000,
 * -1000 ≤ numbers[i] ≤ 1000.
 *
 * [output] string
 * Return one of these values: "even", "odd" or "equal", depending on the conditions described above.
 */
public class evenOddCompare {
    public static String solution7(int[] numbers) {
        int evenSum = 0;
        int oddSum = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (i % 2 == 0) {
                evenSum += numbers[i];
            } else {
                oddSum += numbers[i];
            }
        }

        if (evenSum > oddSum) {
            return "even";
        } else if (oddSum > evenSum) {
            return "odd";
        } else {
            return "equal";
        }
    }
}
