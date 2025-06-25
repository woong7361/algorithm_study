
// https://school.programmers.co.kr/learn/courses/30/lessons/12936
// 26 start


import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;

        // 자연수 n을 연속한 자연수들로 표현하는 방법의 가짓수는?
        //
        // ! 기본 생각 -> two pointer로 자연수 n보다 작으면 +, n보다 크다면 - 를 해주면 되지 않을까?
        //
        // 구현
        // linked list로 two pointer 대체
        // for문으로 n까지 추가 -> 그때 n이라면 good, n보다 작다면? go, n보다 크다면? remove

        // 경계조건 i = n일때 마지막은?


        int sum = 0;
        Deque<Integer> numbers = new LinkedList<>();

        for (int i = 1; i < n + 1; i++) {
            numbers.add(i);
            sum += i;

            while (sum > n) {
                sum -= numbers.removeFirst();
            }

            if (sum == n) {
                answer++;
            }

        }

        return answer;
    }
}
