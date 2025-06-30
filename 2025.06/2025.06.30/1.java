// https://school.programmers.co.kr/learn/courses/30/lessons/12911
// 46 start


import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        // 자연수 n, n의 다음 큰 수는?
        // n보다 큰 자연수
        // n을 2진수로 바꾸었을 때 1을 숫자가 같다.
        // 1,2 를 만족하는 수 중 가장 작은 수

        // 해결 방법
        // n+1 ~ ... 시도
        // XOR? 0011010
        ///     1010100
        ///     1001110

        // 그냥 세야될듯
        int curOne = toBinary(n);

        while(true) {
            int nextOne = toBinary(++n);
            if(curOne == nextOne) break;
        }
        answer = n;

        return answer;
    }

    private static int toBinary(int n) {
        int one = 1;

        while(n > 1) {
            if(n % 2 == 1) {
                one++;
            }
            n /= 2;
        }

        return one;
    }
}
