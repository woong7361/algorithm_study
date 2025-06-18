// https://school.programmers.co.kr/learn/courses/30/lessons/12985
// 50 start


import java.util.*;

class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        // N명 참가 차례대로 번호 부여 1 2, 3 4, ... 끼리 경기 진행 (홀수는?)
        // 다음 라운드는 다시 차례대로 번호 부여
        // 최종 한명이 남을때까지 계속 진행
        // 처음 라운드에서 A번을 가진 참가자는 B번 참가자와 몇번째 라운드에서 만나는지? 찾으시오 (반드시 이긴다면)
        // 2<= N <= 2^20 인 2의 지수승 -> 부전승 X

        // 해결방법
        // 2^1 -> | |
        // 2^2 -> | |  | |
        // 2^3 -> | |  | |  | |  | |
        // 전탐색은 너무 낭비일듯 다른 해결방안이 있을듯함
        // 이긴다면 다음라운드의 번호를 예측?
        // 12 34 56 78
        // 1  2  3  4
        // 01 23 45 67

        // 0부터 시작하면 2로 나눈 몫으로 다음 라운드의 번호가 수렴
        // -> a-b == 1이고 a/2 == b/2 인 경우까지 진행


        while (!isFightRound(a, b)) {
            int[] nextNumber = nextRound(a, b);
            a = nextNumber[0];
            b = nextNumber[1];

            answer++;
        }


        return answer+1;
    }

    private static boolean isFightRound(int a, int b) {
        return Math.abs(a - b) == 1 && (a-1) / 2 == (b-1) / 2;
    }

    private int[] nextRound(int a, int b) {
        int nextA = (a-1) / 2 + 1;
        int nextB = (b-1) / 2 + 1;

        return new int[] {nextA, nextB};
    }
}
