using System.Collections.Generic;
using Xunit;
using InterviewCoding;

namespace InterviewCodingTest;

public class VisagioQ1Test
{
    [Fact]
    public void Test_Even_NoZero_ReturnSum()
    {
        var result = VisagioQ1.calculateRunningTotal(4, new List<int> { 1, 2, 3, 4 });
        Assert.Equal(10, result);
    }

    [Fact]
    public void Test_Even_WithZero_ReturnSumTimes2()
    {
        var result = VisagioQ1.calculateRunningTotal(4, new List<int> { 1, 0, 2, 4 });
        Assert.Equal(14, result);
    }

    [Fact]
    public void Test_Odd_NoThree_ReturnProduct()
    {
        var result = VisagioQ1.calculateRunningTotal(3, new List<int> { 1, 5, 7});
        Assert.Equal(35, result);
    }

    [Fact]
    public void Test_Odd_WithThree_ReturnProductPlus1()
    {
        var result = VisagioQ1.calculateRunningTotal(3, new List<int> { 3, 5, 7});
        Assert.Equal(106, result);
    }
    
}