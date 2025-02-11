// https://school.programmers.co.kr/learn/courses/30/lessons/160586

import java.util.HashMap;
import java.util.Map;

// 46분 스타트
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];

        // 중복 자판 존재 A -> B -> C
        // 자판 수 1 - 100
        // 같은 문자가 여러번 할당 존재 & 할당 x도 있음 -> 작성 할 수 없는 경우 존재
        // 키를 최소 몇번 눌러야하는가? - 작성할 수 없다면 -1을 반환

        // 문제 해결법
        // a -> 몇번, b 몇번 (각각 알파벳의 최소 눌림수 계산)
        // 각각 더하기 - 없다면 -1 반환

        Map<Character, Integer> keyCountMap = new HashMap<>();

        for (String keys : keymap) {
            for (int i = 0; i < keys.length(); i++) {
                char key = keys.charAt(i);

                keyCountMap.put(
                        key,
                        Math.min(keyCountMap.getOrDefault(key, Integer.MAX_VALUE), i+1)
                );
            }
        }

        for (int i = 0; i < targets.length; i++) {
            int temp = 0;

            for (int j = 0; j < targets[i].length(); j++) {
                Integer value = keyCountMap.getOrDefault(targets[i].charAt(j), -1);
                if (value == -1) {
                    temp = -1;
                    break;
                }

                temp += value;
            }

            answer[i] = temp;
        }


        return answer;
    }
}
