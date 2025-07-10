package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/152995
// 35 start


import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        // 근태 점수, 동료 점수
        // 어떤 사원이 다른 임의의 사원보다 두 점수가 모두 낮은 경우가 한 번이라도 있다면 인센을 받지 못한다.
        // 그렇지 않은 사원은 두 점수의 합이 높은 순으로 석차를 내어 차등 지급한다.
        // 점수가 같아 동석차라면 동석차의 수만큼 다음 석차는 건너 뛴다. ex. 1 2 2 4 ...
        // 완호의 석차를 반환하라

        // scores[i] = [a, b]  a - 근태 점수, b - 동료 점수
        // i == 0 -> 완호의 점수이다.
        // 완호가 인센을 받지 못할경우 -1 return

        // 해결방법
        // 먼저 인센을 받지 못하는 사람을 걸러낸다.
        // a b의 합으로 sort 하여 석차를 구한다.

        // 어떻게 인센을 받지 못하는 사람을 걸러낼 것인가?
        // sorting 먼저?
        // ex. [[2,2],[1,4],[3,2],[3,2],[2,1]]
        // [[1,4] [2,1] [2,2] [3,2] [3,2]]

        // [1,4] [1,5]

        // 정렬을 내림차순 오름차순으로 정렬 ...
        // max값을 설정하여 작으면 끝

        int[] wanhoScore = scores[0];
        int rank = 1;
        int maxScore = 0;
        Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

        for (int[] score : scores) {
            if (maxScore <= score[1]) {
                maxScore = score[1];
                if (score[0] + score[1] > wanhoScore[0] + wanhoScore[1]) {
                    rank++;
                }
            } else {
                if (Arrays.equals(score, wanhoScore)) {
                    return -1;
                }
            }

        }

        return rank;
    }
}