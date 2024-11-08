
// https://leetcode.com/problems/contains-duplicate

import java.util.*;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.getOrDefault(num, -1) == -1) {
                map.put(num, 1);
            } else {
                return false;
            }
        }

        return true;
    }
}
