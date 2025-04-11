package Telstra26gp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NumOperations {
        static final int TARGET = 42;
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int[] nums = new int[5];
            for (int i = 0; i < 5; i++) {
                nums[i] = scanner.nextInt();
            }
            scanner.close();

            if (canReachTarget(nums)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        // 生成所有排列和操作组合
        public static boolean canReachTarget(int[] nums) {
            List<int[]> permutations = new ArrayList<>();
            permute(nums, 0, permutations);

            char[] ops = {'+', '-', '*'};
            for (int[] perm : permutations) {
                for (char op1 : ops) {
                    for (char op2 : ops) {
                        for (char op3 : ops) {
                            for (char op4 : ops) {
                                int result = calculate(perm, op1, op2, op3, op4);
                                if (result == TARGET) return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        public static int calculate(int[] nums, char op1, char op2, char op3, char op4) {
            int res = apply(apply(apply(apply(nums[0], nums[1], op1), nums[2], op2), nums[3], op3), nums[4], op4);
            return res;
        }

        public static int apply(int a, int b, char op) {
            switch (op) {
                case '+': return a + b;
                case '-': return a - b;
                case '*': return a * b;
            }
            return 0;
        }

        // 递归生成全排列
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

