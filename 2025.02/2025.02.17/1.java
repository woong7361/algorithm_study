// https://school.programmers.co.kr/learn/courses/30/lessons/142086
// 00 start


import java.util.HashMap;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();

        // char가 처음 나오면 -1  & 갱신
        // char가 두번째 나오면 현재 INDEX - 마지막 나온 INDEX & 갱신

        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);

            Integer lastIndex = characterIntegerHashMap.getOrDefault(character, -1);

            if (lastIndex == -1) {
                answer[i] = -1;
            } else {
                answer[i] = i - lastIndex;
            }

            characterIntegerHashMap.put(character, i);
        }

        return answer;
    }
}
