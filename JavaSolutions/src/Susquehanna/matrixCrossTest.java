package Susquehanna;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class matrixCrossTest {

    @Test
    public void testMatrix1() {
        int[][] matrix1 = {
                {1, 1, 1, 1},
                {2, 3, 1, 1},
                {1, 1, 1, 0},
                {1, 4, 1, 1}
        };
        assertEquals(2, matrixCross.solution6(matrix1));
    }

    @Test
    public void testMatrix2() {
        int[][] matrix2 = {
                {1, 2},
                {2, 1}
        };
        assertEquals(4, matrixCross.solution6(matrix2));
    }

    @Test
    public void testMatrix3() {
        int[][] matrix3 = {
                {2, 3}
        };
        assertEquals(2, matrixCross.solution6(matrix3));
    }
}