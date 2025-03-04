// https://school.programmers.co.kr/learn/courses/30/lessons/118667
// 10 start


import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        // 길이가 다른 큐 2개
        // 큐에서 추출 다른 큐에 삽입 작업 반복 -> 두 큐의 합을 같게 만들기를 하길 원한다.
        // 최소 작업의 횟수는 얼마인가?


        // 절반으로 만들 수 있는가?
        // 1. 합이 홀수라면? -> 불가능

        // 그리디 -> 큰곳을 빼서 작은곳에 더해준다.

        // 불가능한 조건은 언제인가? 한바꾸 돌았을때 불가능 -> q1.l + q2.l 넘어갈때

        long q1Sum = Arrays.stream(queue1).sum();
        long q2Sum = Arrays.stream(queue2).sum();

        LinkedList<Long> que1 = new LinkedList<>();
        LinkedList<Long> que2 = new LinkedList<>();

        for (int i : queue1) {
            que1.add((long) i);
        }
        for (int i : queue2) {
            que2.add((long) i);
        }

        long maxCount = (queue1.length + queue2.length)*2;

        for (long i = 0; i < maxCount; i++) {
            if (q1Sum == q2Sum) {
                answer = (int) i;
                break;
            }

            if (q1Sum > q2Sum) {
                Long value = que1.poll();
                que2.add(value);
                q1Sum -= value;
                q2Sum += value;
            } else {
                Long value = que2.poll();
                que1.add(value);
                q2Sum -= value;
                q1Sum += value;
            }
        }

        return answer;
    }
}
