package Susquehanna;

/**
 * Imagine you are calculating how you rate your favorite website over time using a random scale.
 * You started with a rating of 1500, and whenever your rating changed, you recorded this change in the array diffs.
 *
 * Task:
 * Return an array with two integers:
 * 	1. The highest rating ever achieved.
 * 	2. The current rating after all changes are applied.
 *
 * Notes:
 * 	• Your rating will never become negative.
 * 	• You are not expected to provide the most optimal solution.
 * 	• A solution with time complexity not worse than O(diffs.length²) is acceptable.
 *
 * 	Example1:
 * Input: diffs = [100, -200, 350, 100, -600]
 * Output: [1850, 1250]
 * Explanation:
 * Initial Rating: 1500
 * +100 → 1600
 * -200 → 1400
 * +350 → 1750
 * +100 → 1850 (maximum)
 * -600 → 1250 (final)
 *
 * Example 2:
 *
 * Input: diffs = []
 * Output: [1500, 1500]
 * Explanation: No changes occurred, so both highest and current ratings are the initial value.
 *
 * Input
 * 	• array.integer diffs — list of changes in your rating.
 * Constraints:
 * 	• 0 ≤ diffs.length ≤ 1000
 * 	• -1000 ≤ diffs[i] ≤ 1000
 * 	Output
 * 	• An array.integer of two values:
 * 	• [highest_rating_ever, current_rating]
 */
public class ratingDiffs {
    public static int[] solution9(int[] diffs) {
        int currentRating = 1500;
        int maxRating = 1500;

        for (int diff : diffs) {
            currentRating += diff;
            maxRating = Math.max(maxRating, currentRating);
        }

        return new int[]{maxRating, currentRating};
    }
}
