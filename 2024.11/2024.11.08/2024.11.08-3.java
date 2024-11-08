// https://leetcode.com/problems/remove-duplicates-from-sorted-array/solutions/3676877/best-method-100-c-java-python-beginner-friendly/

import java.util.*;

class Solution {
    public int removeDuplicates(int[] nums) {

        if (nums.length < 1) {
            return 0;
        }

        int start = 0;

        for (int current = 1; current < nums.length; current++) {
            // 1(nums)12(current)
            if(nums[start] != nums[current]) {
                start = start + 1;
                nums[start] = nums[current];
            }
        }

        int result = start + 1;
        while (start + 1 < nums.length) {
            nums[start + 1] = '_';
        }

        return result;
    }
}
