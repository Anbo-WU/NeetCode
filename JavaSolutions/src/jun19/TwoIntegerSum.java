package jun19;

public class TwoIntegerSum {
    /***
     * Given an array of integers nums and an integer target, return the indices i and j such that nums[i] + nums[j] == target and i != j.
     * You may assume that every input has exactly one pair of indices i and j that satisfy the condition.
     * Return the answer with the smaller index first.
     *
     * Example 1:
     * Input:
     * nums = [3,4,5,6], target = 7
     * Output: [0,1]
     * Explanation: nums[0] + nums[1] == 7, so we return [0, 1].
     *
     * Example 2:
     * Input: nums = [4,5,6], target = 10
     * Output: [0,2]
     *
     * Example 3:
     * Input: nums = [5,5], target = 10
     * Output: [0,1]
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{0};
    }
    // this question is the same as which in the Day1.
}
