package jun17;

import java.util.HashSet;
import java.util.Set;

/**
 Given an integer array nums, return true if any value appears more than once in the array,
 otherwise return false.
*/

public class DupInteger {
    public boolean hasDuplicate(int[] nums) {
        int arrayNum = 0;
        int hashNum = 0;
        Set<Integer> numCount = new HashSet<>();

        for (int i : nums){
            arrayNum++;
            numCount.add(i);
        }

        for (int j : numCount){
            hashNum++;
        }

        if (arrayNum != hashNum){
            return true;
        }else {
            return false;
        }
        //utilize the non-duplicate feature of hashset to compare the numbers of array and set.
    }
}
