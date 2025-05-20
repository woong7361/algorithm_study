// https://school.programmers.co.kr/learn/courses/30/lessons/42883
// 39 start


import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";

        // 어떤 숫자에서 k개의 숫자를 제거했을 때 얻을 수 있는 가장 큰 수는?
        // ex. 1924 -> 1,2 제거 -> 94 가장 큰 수

        // 정렬...
        // 남은 수를 기준으로 세기

        int resultSize = number.length() - k;

        LinkedList<Integer> result = new LinkedList<>();
        result.add(Character.getNumericValue(number.charAt(0)));
        for (int i = 1; i < number.length(); i++) {
            int num = Character.getNumericValue(number.charAt(i));

            // 남은 수 = 목표 수 - 현재 수
            int remainCnt = number.length() - i + result.size() - resultSize;

            while (!result.isEmpty() && remainCnt > 0) {
                remainCnt--;
                Integer prevNum = result.getLast();

                if (prevNum >= num) {
                    break;
                } else {
                    result.removeLast();
                }
            }

            if (result.size() < resultSize) {
                result.add(num);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (Integer i : result) {
            builder.append(i);
        }

        // 1231234
        // 32 | 남은수 34
        // 남은수 2개, 현재수 2개, 목표수 4개 -> 0번
        // 남은수 3개, 현재수 2개, 목표수 4개 -> 1번 빼기 가능
        // 남은수 + 현재수 - 목표수
        return builder.toString();
    }
}
