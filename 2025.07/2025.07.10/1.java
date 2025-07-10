// https://school.programmers.co.kr/learn/courses/30/lessons/161988
// 40 start


import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;

        // 연속 펄스 수열 = [1, -1, 1, ...] or [-1, 1, -1, ...]
        // 어떤 수열 sequence에 연속 펄수 수열을 곱한 값의 가장 최댓값은?

        // 문제 설계
        // two pointer 가 좋은 방법일듯 하다.
        // 연속 펄스 수열을 곱한다. -> 오른쪽으로는 계속 더한다. (진행)
        // but. 왼쪽을 어떻게 줄일 것인가? 가장 큰 값이 되도록 ---...
        // ex. 1 2 3 -1 5 -100 ...
        // 1 2 3 까지 진행 -> 최댓값 갱신됨 6
        // -1 진행 -> 값 5
        // 5 진행 -> 최댓값 갱신됨 10
        // -100 진행 -> 값 -90

        // 갱신된 값이 0보다 작게되면 뺀다? -> 이게 맞는듯?
        // -3 7 -6

        long plusStart = 0, minusStart = 0;
        for (int i = 0; i < sequence.length; i++) {
            if (plusStart < 0) {
                plusStart = 0;
            }

            if (minusStart < 0) {
                minusStart = 0;
            }

            if (i % 2 == 0) {
                plusStart += sequence[i];
                minusStart -= sequence[i];
            } else {
                plusStart -= sequence[i];
                minusStart += sequence[i];
            }

            answer = Math.max(plusStart, answer);
            answer = Math.max(minusStart, answer);
        }

        return answer;
    }
}
