// https://school.programmers.co.kr/learn/courses/30/lessons/70129
// 17 start


import java.util.*;

class Solution {
    private int removedZeroCount;
    private int transformCount;
    public int[] solution(String s) {
        int[] answer = new int[2];
        removedZeroCount = 0;
        transformCount = 0;
        // 0과 1로 이루어진 어떤 문자열 X에 대해 이진변환을 정의
        // x의 모든 0을 제거
        // x의 길이가 c라고 하면 c를 2진법으로 표현한 문자열로 바꾼다.
        // s가 1이 될때동안 변환을 가했을때 변환된 횟수와 제거된 0의 개수를 배열에 담아 반환

        while (! s.equals("1")){
            s = removeZero(s);
            s = transform(s);
        }

        answer[0] = transformCount;
        answer[1] = removedZeroCount;
        return answer;
    }

    private String transform(String s) {
        transformCount += 1;
        return Integer.toString(s.length(), 2);
    }

    private String removeZero(String s) {
        StringBuilder builder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '0') {
                removedZeroCount += 1;
                continue;
            }

            builder.append(c);
        }

        return builder.toString();
    }
}
