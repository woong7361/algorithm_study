// https://leetcode.com/problems/top-k-frequent-elements/

import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // (key, count)
        PriorityQueue<List<Integer>> heap  = new PriorityQueue<>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> origin, List<Integer> target) {
                return origin.get(1) - target.get(1);
            }
        });

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Integer key : map.keySet()) {
            heap.add(List.of(key, map.get(key)));
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll().get(0);
        }

        return result;

    }
}
