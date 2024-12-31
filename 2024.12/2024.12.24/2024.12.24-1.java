package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/181188


class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;

        long r1Pow = (long) r1 * r1;
        long r2Pow = (long) r2 * r2;

        // x 0부터 r2 까지 구하기
        for (long x = 0; x < r1; x++) {
            long xPow = x * x;
            // 위는 r2, 아래는 r1으로 시작해서 현재 x위치에서 가장 윗점, 아랫점 찾기
            int maxC = (int) Math.floor(Math.sqrt(r2Pow - xPow));
            int minC = (int) Math.ceil(Math.sqrt(r1Pow - xPow));

            if (maxC - minC < 0) {
                continue;
            }

            answer = answer + (maxC - minC + 1) * 4;
        }
        answer = answer - (r2 - r1 + 1) * 2;

        for (long x = r1; x < r2+1; x++) {
            long xPow = x * x;

            int maxC = (int) Math.floor(Math.sqrt(r2Pow - xPow));


            answer = answer + (maxC * 2 + 1) * 2;
        }

        return answer;
    }

}
