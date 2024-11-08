
// https://leetcode.com/problems/single-number/solutions/1771720/c-easy-solutions-sorting-xor-maps-or-frequency-array/

import java.util.*;

class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();

        int result = -1;
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }

        return set.stream().findFirst().get();
    }
}

