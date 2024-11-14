// https://leetcode.com/problems/longest-substring-without-repeating-characters/

import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Integer, Integer> characters = new HashMap<>();

        int result = 0;

        int start = 0;
        int end = 0;
        for (int currentCharacter : s.toCharArray()) {
            // 중복 없어질때까지 돌려
            while (characters.getOrDefault(currentCharacter, 0) > 0) {
                characters.put((int) s.charAt(start), 0);
                start++;
            }

            // 추가해
            characters.put(currentCharacter, 1);

            // 갱신해
            result = Math.max(result, end - start + 1);

            // 다음값
            end++;
        }

        return result;
    }
}
