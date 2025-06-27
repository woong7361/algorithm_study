// https://school.programmers.co.kr/learn/courses/30/lessons/12914
// 23 start


import java.util.*;

class Solution {
    public long solution(int n) {
        long answer = 0;

        // 효진이는 멀리뛰기 연습
        // 1칸 또는 2칸 뛰기 가능
        // 효진이가 끝에 도달할 수 있는 방법의 수는 몇가지인가?

        // 설계 방법
        // dynamic? -> n까지 도달하는 방법의 수 = Tn-1 + 1칸 뛰기 & Tn-2 + 2칸 뛰기

        // 4칸
        // ? ? ? 1

        // ? ? 1 1
        // ? ? 2


        // 해결 방법
        // t[1] = 1, t[2] = 2
        // t[3] 부터 시작
        // 정방향으로 t[n]까지 t[n] = t[n-1] + t[n-2]

        long back1 = 2;
        long back2 = 1;
        for (int i = 3; i < n + 1; i++) {
            answer = (back1 + back2) % 1234567;
            back2 = back1;
            back1 = answer;
        }

        if (n == 1) {
            answer = 1;
        } else if (n == 2) {
            answer = 2;
        }

        return answer;
    }
}
