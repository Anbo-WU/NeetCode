package Susquehanna;

/**
 * Imagine that you are monitoring changes to user ratings for an online platform.
 * Each user on this platform has an overall rating (an integer between 1 and 2500 )and a corresponding level.
 * Rating levels are based the following rules.
 * - rating < 1000 = "beginner";
 * - 1000 <= rating < 1500 = "intermediate";
 * - 1500 <= rating < 2000 = "advanced";
 * 2000 <= rating = "pro".
 * You are given an 'initial' rating value and an array of integers 'changes' representing changes to the rating.
 * Your task is to calculate the final rating and return the level corresponding to that rating.
 * It is guaranteed that changes to the rating value will never result in it becoming less than 1 or greater than 2500
 *
 * Note: You are not expected to provide the most optimal solution, but a solution with time complexity not worse than o(changes.length^2) will fit within the execution time limit.
 *
 * Example:
 * For initial = 1500 and changes = [-100, -300, 450, 500, -500, -600] ,the output should be solution(initial,changes) = "beginner".
 * Explanation:
 * The rating changes as follows. 1500 -> 1400 -> 1100 -> 1550 -> 2050 -> 1550 -> 950 . The final rating value of 950 is in the range of the "beginner" level.
 * For initial = 1000 and changes = [100, 200, 300, 400, -500] ,the output should be solution(initial, changes) = "advanced"
 * Explanation:
 * The rating changes as follows: 1000 -> 1100 -> 1300 -> 1600 -> 2000 -> 1500 . The final rating value of 1500 is in the range of the "advanced" level.
 *
 */
public class userRating {
    public static String solution1(int initial, int[] changes) {
        int rating = initial;

        for (int i : changes) {
            rating += i;
        }

        if (rating < 1) rating = 1;
        if (rating > 2500) rating = 2500;

        if (rating < 1000) {
            return "beginner";
        } else if (rating < 1500) {
            return "intermediate";
        } else if (rating < 2000) {
            return "advanced";
        } else {
            return "pro";
        }

    }
}
