package Susquehanna;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class evenOddCompareTest {

    @Test
    void testExample1() {
        int[] example1 = {1, 2, 3, 4, 5};
        assertEquals("even", evenOddCompare.solution7(example1));
    }

    @Test
    void testExample2() {
        int[] example2 = {-1, 4, 3, -2};
        assertEquals("equal", evenOddCompare.solution7(example2));
    }

    @Test
    void testExample3() {
        int[] example3 = {5, 21, 9};
        assertEquals("odd", evenOddCompare.solution7(example3));
    }
}