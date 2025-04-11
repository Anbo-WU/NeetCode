package Telstra26gp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RawNumOperations {
    static final int TARGET = 42;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        scanner.close();

        System.out.println(canReachTarget(nums) ? "YES" : "NO");
    }

    public static boolean canReachTarget(int[] nums) {
        List<int[]> permutations = new ArrayList<>();
        permute(nums, 0, permutations);
        char[] ops = {'+', '-', '*'};
        for (int[] perm : permutations) {
            for (char op1 : ops)
                for (char op2 : ops)
                    for (char op3 : ops)
                        for (char op4 : ops)
                            if (calculate(perm, op1, op2, op3, op4) == TARGET)
                                return true;
        }
        return false;
    }

    public static int calculate(int[] nums, char op1, char op2, char op3, char op4) {
        return apply(apply(apply(apply(nums[0], nums[1], op1), nums[2], op2), nums[3], op3), nums[4], op4);
    }

    public static int apply(int a, int b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            default: return 0;
        }
    }

    public static void permute(int[] nums, int start, List<int[]> result) {
        if (start == nums.length) {
            result.add(Arrays.copyOf(nums, nums.length));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, i, start);
            permute(nums, start + 1, result);
            swap(nums, i, start);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
