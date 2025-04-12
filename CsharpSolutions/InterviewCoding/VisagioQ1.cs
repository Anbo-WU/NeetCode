using System;
using System.Collections.Generic;

/* 
Implement a method called `calculateRunningTotal(int n, List<int> list_of_numbers)` that returns a computed result based on the following rules:

1. For a given list of integers:
   - Compute the product of all numbers.
   - Compute the sum of all numbers.
   - If the product is even:
       - If the list contains 0, return `sum * 2`;
       - Otherwise, return `sum`.
   - If the product is odd:
       - If the list contains 3, return `product + 1`;
       - Otherwise, return `product`.
*/

namespace InterviewCoding
{
  public class VisagioQ1
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

  }
}