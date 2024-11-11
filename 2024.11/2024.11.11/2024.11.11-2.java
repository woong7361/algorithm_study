// https://leetcode.com/problems/3sum/

import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int p1 = 0; p1 < nums.length - 2; p1++) {
            int p2 = p1 + 1;
            int p3 = nums.length - 1;

            if (p1 > 0 && nums[p1 - 1] == nums[p1]) {
                continue;
            }

            while (p2 < p3) {
                if (p2 > p1+1 && nums[p2 - 1] == nums[p2]) {
                    p2++;
                    continue;
                }
                if (p3 < nums.length - 1 && nums[p3 + 1] == nums[p3]) {
                    p3--;
                    continue;
                }

                if (nums[p1] + nums[p2] + nums[p3] < 0) {
                    p2++;
                } else if (nums[p1] + nums[p2] + nums[p3] == 0) {
                    res.add(List.of(nums[p1], nums[p2], nums[p3])); // 중복은?
                    p2++;
                } else {
                    p3--;
                }
            }
        }

        return res;
    }
}
