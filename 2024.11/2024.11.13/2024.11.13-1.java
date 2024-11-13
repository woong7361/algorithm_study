// https://leetcode.com/problems/group-anagrams/

import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 1. sol: set

        // 2. sol: sort


        HashMap<String, List<String>> result = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            //기준
            String current = String.valueOf(charArray);

            if (result.containsKey(current)) {
                result.get(current).add(str);
            } else {
                ArrayList<String> newList = new ArrayList<>();
                newList.add(str);
                result.put(current, newList);
            }
        }

        return new ArrayList<>(result.values());
    }
}
