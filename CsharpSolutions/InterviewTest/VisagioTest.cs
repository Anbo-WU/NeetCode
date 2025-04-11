using System;
using System.Collections.Generic;

class VisagioTest
{
    public static int calculateRunningTotal(int n, List<int> list_of_numbers)
    {
        int product = 1;
        int sum = 0;
        bool hasZero = false;
        bool hasThree = false;

        foreach (int num in list_of_numbers)
        {
            sum += num;
            if (num == 0) hasZero = true;
            if (num == 3) hasThree = true;
            product *= num;
        }

        if (product % 2 == 0) // even
        {
            if (hasZero)
                return sum * 2;
            else
                return sum;
        }
        else // odd
        {
            if (hasThree)
                return product + 1;
            else
                return product;
        }
    }

    static void Main()
    {
        int n = 4;
        List<int> list_of_numbers = new List<int> { 1, 2, 3, 4 };
        int result = calculateRunningTotal(n, list_of_numbers);
        Console.WriteLine(result); // 输出: 10
    }
}