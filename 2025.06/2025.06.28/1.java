package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/12914
// 23 start


import java.util.*;

class Solution {
    int solution(int[][] land) {
        int answer = 0;

        // 땅은 n행, 4열로 이루어져있고, 각 땅에는 점수가 쓰여져있다.
        // 1행부터 땅을 밟으면서 한 행씩 내려올때, 각 행의 4칸중 한칸만 밟으면서 내려와야한다. 단 같은 열을 연속해서 밟을 수 없다.
        // 마지막 행까지 내려왔을 때 얻을 수 있는 점수의 최대값은?

        // 행의 개수 <= 100000

        // 문제 설계
        // dfs로 풀려했으나 행의 개수가 너무 많아 연산시간이 너무 많을 것으로 예상
        // 각 열당 최대값을 만들어서 계산해 나가기

        // 해결 방법
        // T = int[4] 생성 -> 현재 상태의 각 열에 대응하는 최대값
        // 처음 값 land[0] 로 초기화
        // Tn[i] = land[n][i] + Max(Tn-1[i 제외 나머지])

        int[] prev = new int[4];
        int[] current = new int[4];
        for (int i = 0; i < 4; i++) {
            prev[i] = land[0][i];
        }

        for (int i = 1; i < land.length; i++) {

            for (int col = 0; col < 4; col++) {
                int max = 0;
                for (int j = 0; j < 4; j++) {
                    if(j == col) continue;
                    max = Math.max(max, prev[j]);
                }

                current[col] = land[i][col] + max;
            }

            for (int t = 0; t < 4; t++) {
                prev[t] = current[t];
            }
        }

        return Arrays.stream(current)
                .max()
                .getAsInt();
    }
}
