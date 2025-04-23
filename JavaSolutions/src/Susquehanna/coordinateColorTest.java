package Susquehanna;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class coordinateColorTest {
    @Test
    public void testExampleCase() {
        int length = 7;
        int[][] queries = {
                {1, 2}, {0, 2}, {3, 5}, {3, 2}, {2, 2}, {6, 1}, {1, 3}
        };
        int[] expected = {0, 1, 1, 1, 3, 3, 1};
        assertArrayEquals(expected, coordinateColor.solution8(length, queries));
    }

    @Test
    public void testNoMatchingPairs() {
        int length = 5;
        int[][] queries = {
                {0, 1}, {2, 2}, {4, 3}
        };
        int[] expected = {0, 0, 0};
        assertArrayEquals(expected, coordinateColor.solution8(length, queries));
    }

    @Test
    public void testAllSameColor() {
        int length = 5;
        int[][] queries = {
                {0, 1}, {1, 1}, {2, 1}, {3, 1}, {4, 1}
        };
        int[] expected = {0, 1, 2, 3, 4};
        assertArrayEquals(expected, coordinateColor.solution8(length, queries));
    }

    @Test
    public void testAlternatingColors() {
        int length = 6;
        int[][] queries = {
                {0, 1}, {1, 2}, {2, 1}, {3, 2}, {4, 1}, {5, 2}
        };
        int[] expected = {0, 0, 0, 0, 0, 0};
        assertArrayEquals(expected, coordinateColor.solution8(length, queries));
    }

    @Test
    public void testOverwriteColors() {
        int length = 4;
        int[][] queries = {
                {0, 1}, {1, 1}, {1, 2}, {2, 2}
        };
        int[] expected = {0, 1, 0, 1};
        assertArrayEquals(expected, coordinateColor.solution8(length, queries));
    }
}
