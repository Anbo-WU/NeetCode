package Susquehanna;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class matrixCellsTest {

    @Test
    public void testExample1() {
        int[][] matrix = {
                {1, 0, 2},
                {1, 2, 0},
                {0, 2, 0}
        };
        assertEquals(2, matrixCells.solution10(matrix));
    }

    @Test
    public void testExample2() {
        int[][] matrix = {
                {2, 0, 0, 0, 2},
                {1, 2, 1, 2, 0},
                {0, 1, 2, 1, 0},
                {0, 0, 2, 1, 1},
                {1, 1, 2, 1, 1}
        };
        assertEquals(8, matrixCells.solution10(matrix));
    }

    @Test
    public void testNoChangeNeeded() {
        int[][] matrix = {
                {2, 0, 2},
                {0, 2, 0},
                {0, 2, 0}
        };
        assertEquals(0, matrixCells.solution10(matrix));
    }

    @Test
    public void testAllSameValues() {
        int[][] matrix = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        assertTrue(matrixCells.solution10(matrix) >= 0); // Should be a valid number
    }

    @Test
    public void testLargeOddMatrix() {
        int[][] matrix = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = (i + j) % 3;
            }
        }
        assertTrue(matrixCells.solution10(matrix) >= 0);
    }

}