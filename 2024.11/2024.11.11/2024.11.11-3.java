package org.example.solutions;

// https://leetcode.com/problems/sort-colors/

import java.util.*;

class Solution {
    public void sortColors(int[] nums) {

        if (nums == null || nums.length == 0) {
            return;
        }

        int left = 0;
        int p1 = 0;
        int right = nums.length - 1;

        while (p1 < right) {
            if (nums[p1] == 0) {
                int temp = nums[p1];
                nums[p1] = nums[left];
                nums[left] = temp;

                left++;
                p1++;
            } else if (nums[p1] == 2) {
                int temp = nums[p1];
                nums[p1] = nums[right];
                nums[right] = temp;

                right--;
            } else {
                p1++;
            }

        }

    }
}
