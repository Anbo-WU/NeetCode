package VisagioGP;

/**
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

public class VisagioQ1 {
    public static int calculateRuningTotal(int n, int[] list_of_numbers){
        int product = 1;
        int sum = 0;
        boolean hasZero = false;
        boolean hasThree = false;

        for(int i : list_of_numbers){
            product *= i;
            sum += i;

            if(i == 0){
                hasZero = true;
            }
            if (i == 3){
                hasThree = true;
            }
        }

        if (product % 2 == 0){ // list number is even
            if (hasZero){
                return sum * 2;
            }else {
                return sum;
            }
        }else { // list number is odd
            if (hasThree){
                return product + 1;
            }else {
                return product;
            }
        }
    }
}
