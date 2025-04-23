package Susquehanna;

    /**
     * Given a square matrix n x n (n is guaranteed to be odd) that contains only 0s, 1s, and 2s.
     * In one operation, you can change the number in any cell of the matrix to a different number (0, 1, or 2).
     *
     * Task:
     * Compute the minimum number of cells that need to change for the letter Y to be written on the matrix.
     *
     * The letter Y is written on the matrix if and only if:
     * - All numbers on the diagonals (top-left to center and top-right to center) and the vertical line
     *   from the center to the bottom are the same.
     * - All other numbers (outside of the Y) are the same and different from the Y value.
     *
     * Note:
     * - There are 6 possible valid Y combinations (e.g. 0 and 1, 1 and 2, etc.).
     * - You are not expected to provide the most optimal solution, but a solution with time complexity
     *   no worse than O(n^2) will fit within the execution time limit.
     *
     * Example 1:
     * Input:
     * matrix = [
     *   [1, 0, 2],
     *   [1, 2, 0],
     *   [0, 2, 0]
     * ]
     * Output: 2
     * Explanation: Change [0][0] to 2, and [1][0] to 0. Final matrix will show a valid Y shape.
     *
     * Example 2:
     * Input:
     * matrix = [
     *   [2, 0, 0, 0, 2],
     *   [1, 2, 1, 2, 0],
     *   [0, 1, 2, 1, 0],
     *   [0, 0, 2, 1, 1],
     *   [1, 1, 2, 1, 1]
     * ]
     * Output: 8
     * Explanation: Change all 0s (8 total) to 1s. The 2s form the Y, 1s are the background.
     */

public class matrixCells {
        public static int solution10(int[][] matrix) {
            int n = matrix.length;
            int center = n / 2;

            int minChanges = Integer.MAX_VALUE;

            int[][] combinations = {{0, 1}, {0, 2}, {1, 0}, {1, 2}, {2, 0}, {2, 1}};

            for (int[] combo : combinations) {
                int yValue = combo[0];
                int bgValue = combo[1];

                int changes = 0;

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        boolean isPartOfY = false;

                        if (i == j && i <= center) {
                            isPartOfY = true;
                        } else if (i + j == n - 1 && i <= center) {
                            isPartOfY = true;
                        } else if (j == center && i >= center) {
                            isPartOfY = true;
                        }

                        if (isPartOfY && matrix[i][j] != yValue) {
                            changes++;
                        } else if (!isPartOfY && matrix[i][j] != bgValue) {
                            changes++;
                        }
                    }
                }

                minChanges = Math.min(minChanges, changes);
            }

            return minChanges;
        }
}
