// https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/

import java.util.*;

class Solution {
    private List<String> result = new ArrayList<>();
    private static Map<Character, List<String>> phone = new HashMap<>();

    static{
        phone.put('2', List.of("a", "b", "c"));
        phone.put('3', List.of("d", "e", "f"));
        phone.put('4', List.of("g", "h", "i"));
        phone.put('5', List.of("j", "k", "l"));
        phone.put('6', List.of("m", "n", "o"));
        phone.put('7', List.of("p", "q", "r", "s"));
        phone.put('8', List.of("t", "u", "v"));
        phone.put('9', List.of("w", "x", "y", "z"));
    }


    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return List.of();
        }

        // dfs 방식으로 string 추가
        dfs(digits, new StringBuilder(), 0);

        // length 가 끝이면 끝

        return result;
    }

    private void dfs(String digits, StringBuilder builder, int currentIndex) {
        if (currentIndex == digits.length()) {
            result.add(builder.toString());
            return;
        }

        for (String s : phone.get(digits.charAt(currentIndex))) {
            dfs(digits, builder.append(s), currentIndex+1);
            builder.delete(currentIndex, currentIndex+1);
        }
    }

}
