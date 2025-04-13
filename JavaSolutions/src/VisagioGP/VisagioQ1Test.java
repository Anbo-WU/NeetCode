package VisagioGP;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VisagioQ1Test {
    @Test
    public void Test_Even_NoZero_ReturnSum(){
        int result = VisagioQ1.calculateRuningTotal(4, new int[]{1, 2, 3, 4});
        assertEquals(10, result);
    }

    @Test
    public void Test_Even_WithZero_ReturnSumTimes2(){
        int result = VisagioQ1.calculateRuningTotal(4, new int[]{1, 0, 2, 4});
        assertEquals(14, result);
    }

    @Test
    public void Test_Odd_NoThree_ReturnProduct(){
        int result = VisagioQ1.calculateRuningTotal(3, new int[]{1, 5, 7});
        assertEquals(35, result);
    }

    @Test
    public void Test_Odd_WithThree_ReturnProductPlus1(){
        int result = VisagioQ1.calculateRuningTotal(3, new int[]{3, 5, 7});
        assertEquals(106, result);
    }
}