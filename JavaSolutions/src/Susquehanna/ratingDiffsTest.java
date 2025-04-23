package Susquehanna;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ratingDiffsTest {

    @Test
    public void testNormalSequence() {
        int[] diffs = {100, -200, 350, 100, -600};
        int[] expected = {1850, 1250};
        assertArrayEquals(expected, ratingDiffs.solution9(diffs));
    }

    @Test
    public void testNoChanges() {
        int[] diffs = {};
        int[] expected = {1500, 1500};
        assertArrayEquals(expected, ratingDiffs.solution9(diffs));
    }

    @Test
    public void testAllPositive() {
        int[] diffs = {100, 200, 50};
        int[] expected = {1850, 1850};
        assertArrayEquals(expected, ratingDiffs.solution9(diffs));
    }

    @Test
    public void testAllNegative() {
        int[] diffs = {-100, -200};
        int[] expected = {1500, 1200};
        assertArrayEquals(expected, ratingDiffs.solution9(diffs));
    }

    @Test
    public void testFluctuating() {
        int[] diffs = {300, -100, 200, -400, 500};
        int[] expected = {2000, 2000};
        assertArrayEquals(expected, ratingDiffs.solution9(diffs));
    }
}