package Susquehanna;

/**
 * For a rectangular matrix of integers, a cross is a figure formed by joining one row and one column. A cross is considered to be regular if all the elements in it are equal.
 * A cross is called nearly regular if all of its elements are equal except for, at times, the element in the intersection of the row and
 * the column which form the cross.
 * You are given a rectangular matrix of integers matrix. Your task is to return the number of nearly regular crosses within matrix. Note that by definition the regular cross is also considered to be a nearly
 * regular cross.
 * Note: You are not expected to provide the most optimal solution, but a solution with time complexity not worse than
 * 0 (matrix. length • matrix[0] length •
 * (matrix. length + matrix[0].length))
 * will fit within the execution time limit.
 *
 * Example
 * • For
 * matrix = [
 * [1, 1, 1, 11],
 * [2, 3, 1, 11],
 * [1, 1, 1, 0],
 * [1, 4, 1, 1]
 * ]
 * the output should be:
 * solution (matrix) = 2.
 *
 * Explanation:
 * The only two nearly regular crosses are:
 * • The cross formed by the first row and the third column;
 * <img src="Resources/matrixCross1.png" />
 * • The cross formed by the third row and the last column.
 * <img src="Resources/matrixCross2.png" />
 */
public class matrixCross {
    public static int solution6(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (isNearlyRegular(matrix, row, col)) {
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean isNearlyRegular(int[][] matrix, int row, int col) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Check row: all elements (except intersection) must be equal
        Integer rowVal = null;
        for (int j = 0; j < n; j++) {
            if (j == col) continue;
            if (rowVal == null) {
                rowVal = matrix[row][j];
            } else if (matrix[row][j] != rowVal) {
                return false;
            }
        }

        // Check column: all elements (except intersection) must be equal
        Integer colVal = null;
        for (int i = 0; i < m; i++) {
            if (i == row) continue;
            if (colVal == null) {
                colVal = matrix[i][col];
            } else if (matrix[i][col] != colVal) {
                return false;
            }
        }

        // It passes both row and column uniformity (except center), so it's valid
        return true;
    }
}

//FIXME: there must be a slight problem for this solution. Cus I cannot pass one of the hidden tests(19/20 passed).
