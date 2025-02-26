// https://school.programmers.co.kr/learn/courses/30/lessons/134239
// 47 start


import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];

        // 짝수라면 2로 나눈다.
        // 홀수라면 3을 곱하고 1을 더한다.
        // 결과가 1보다 크다면 1번 더한다.

        // 정적분
        // x = 1 고정 사다리꼴의 넓이 구하는 문제

        // y점 list 만들고
        // ranges 돌려서 x1 ~ x2 까지
        // xi, xi+1의 y값 합쳐서 /2 더하기

        ArrayList<Integer> collatzList = new ArrayList<>();
        collatzList.add(k);

        while (k > 1) {
            if (k % 2 == 0) {
                k = k / 2;
            } else {
                k = k * 3 + 1;
            }

            collatzList.add(k);
        }

        int n = collatzList.size();

        // n=6  1,-2라면  [1,2,3] start = 1, end = 4(n-2) (미포함)
        // 경계조건 구하기

        //
        // start는 1 확정 -> collatz.get(start) 부터 가능
        // end를 어떻게 할것인가? n-x -> collatz.get(end) 미포함
        // start 가 3일때 end가 3 X
        // start 가 3일때 end 가 4 X
        // start 가 3일때 end 가 5 O
        for (int i = 0; i < ranges.length; i++) {
            int start = ranges[i][0];
            int end = n + ranges[i][1];

            if (start >= end-1) {
                answer[i] = -1;
            }

            for (int j = start; j < end-1; j++) {
                Integer i1 = collatzList.get(j);
                Integer i2 = collatzList.get(j + 1);

                answer[i] += (double) (i1 + i2) / 2;
            }
        }

        return answer;
    }
}
