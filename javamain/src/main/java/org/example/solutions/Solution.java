package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/214289
// 12 start


import java.util.Arrays;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        // 에어컨 쾌적한 실내온도 t1 ~ t2 유지하려함
        // 0분에는 실내온도 = 실외온도
        // // 에어컨을 켜 희망온도 설정 -> 원하는 값으로 변경 가능
        // 실내온도 != 희망온도 -> 희망온도 방향으로 1 움직인다, 같다면 동일하다.
        //  에어컨을 끄면 실내온도가 실외온도 방향으로 1 움직인다. (위 아래)
        // 소비전력은 실내온도에 따라 달라진다. 희망온도가 실내온도와 다르면 a소비, 같다면 b 소비한다.
        // 소비전력을 최소화시킬 때의 소비전력은?
        // 단 승객이 탑승하지 않을 때는 희망온도를 유지하지 않아도 된다.

        // -10 ≤ temperature ≤ 40
        // -10 ≤ t1 < t2 ≤ 40


        // 문제 설계
        // a와 b 크기 관계 상관 X
        // 그냥 2가지 방법이 존재
        //  1. 온도를 올리거나 내리고 off 하는 방식
        //  2. 희망온도에 맞게 설정해 유지하는 방식

        // 공통 -> onboard == 0 일때 onboard == 1일 때를 맞추어 t1 or t2에 맞게 온도 맞추기
        // 온도 맞추었을 때 (1) or (2)에 맞게 온도 유지
        // 다시 onboard == 0 되면 다음 onboard == 1에 맞게 반복
        // onboaod가 0밖에 안남았거나 onboard가 끝났으면 끝

        // 해결방법
        // 현재 온도 , target 온도, onboard 시기, 켜야하는 시간, 해당 시간 온도
        // 28        26          2            2           26
        // 28        27          2            2           26

        // ! onboard가

        // 유의할점
        // 1. 그냥 두는거랑 희망온도 설정하는 거랑 같을 수도 있음
        // 2. 온도를 올릴지 내릴지 정해야함 -> abs 차이?

        int INF = Integer.MAX_VALUE;
        int answer = INF;
        int n = onboard.length;

        // 온도 범위 [-10,40] ~> [0,50]
        temperature += 10;
        t1 += 10;
        t2 += 10;

        // dp[시간][온도] : 시간(분)에 온도에 도달하는 최소 소모전력
        int[][] dp = new int[n+1][51];
        for (int i = 0; i < n+1; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][temperature] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 50; j++) {
                if (dp[i][j] == INF) continue;  // 도달할 수 없는 경우
                if (onboard[i] == 1 && (j < t1 || j > t2)) continue;    // 탑승자가 있고, 희망온도 범위가 아닌 경우

                // AC ON
                // 1. 희망온도를 낮춰서 실내온도 낮춤
                if (j > 0) dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j] + a);
                // 2. 희망온도를 높혀서 실내온도 높힘
                if (j < 50) dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j] + a);
                // 3. 희망온도 유지해서 실내온도 유지
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + b);

                // AC OFF
                int nextTemp = j;
                // 4. 실외온도와 같아지는 방향으로 실내온도가 변함
                if (j > temperature) nextTemp--;
                else if (j < temperature) nextTemp++;
                dp[i+1][nextTemp] = Math.min(dp[i+1][nextTemp], dp[i][j]);
            }
        }

        for (int j = 0; j <= 50; j++) {
            if (onboard[n-1] == 1 && (j < t1 || j > t2)) continue;    // 탑승자가 있고, 희망온도 범위가 아닌 경우
            answer = Math.min(answer, dp[n-1][j]);
        }

        return answer;
    }
}