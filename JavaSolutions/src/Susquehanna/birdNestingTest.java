package Susquehanna;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class birdNestingTest {

    @Test
    public void test1() {
        int[] forest = {25, 0, 50, 0, 0, 0, 0, 15, 0, 0, 45};
        int bird = 4;
        int[] result = birdNesting.solution2(forest, bird);
        assertArrayEquals(new int[]{7, 2, 10}, result);
    }

    @Test
    public void test2() {
        int[] forest = {50, 0, 50};
        int bird = 1;
        int[] result = birdNesting.solution2(forest, bird);
        assertArrayEquals(new int[]{2, 0}, result);
    }

    @Test
    public void test3() {
        int[] forest = {25, 25, 0, 25, 25};
        int bird = 2;
        int[] result = birdNesting.solution2(forest, bird);
        assertArrayEquals(new int[]{3, 1, 4, 0}, result);
    }

    @Test
    public void test4() {
        int[] forest = {50, 50, 50, 0, 31, 0, 25, 0, 24, 0, 0, 0, 0, 0, 0, 33};
        int bird = 3;
        int[] result = birdNesting.solution2(forest, bird);
        assertArrayEquals(new int[]{4, 2, 6}, result);
    }

    @Test
    public void test5() {
        int[] forest = {31, 0, 0, 0, 26, 0, 25, 0, 28, 0, 50, 50, 50};
        int bird = 5;
        int[] result = birdNesting.solution2(forest, bird);
        assertArrayEquals(new int[]{6, 4, 8, 0}, result);
    }

}