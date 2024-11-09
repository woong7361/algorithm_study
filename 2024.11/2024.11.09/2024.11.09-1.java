// https://leetcode.com/problems/intersection-of-two-arrays-ii/

import java.util.*;

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int pointer1 = 0;
        int pointer2 = 0;

        List<Integer> results = new ArrayList<>();
        while (pointer1 < nums1.length && pointer2 < nums2.length) {
            if (nums1[pointer1] == nums2[pointer2]) {
                results.add(nums1[pointer1]);
                pointer1 += 1;
                pointer2 += 1;
            } else if (nums1[pointer1] > nums2[pointer2]) {
                pointer2 += 1;
            } else {
                pointer1 += 1;
            }
        }

        int[] result = new int[results.size()];
        for (int i = 0; i < results.size(); i++) {
            result[i] = results.get(i);
        }


        return result;
    }
}
