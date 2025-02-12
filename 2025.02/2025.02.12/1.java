
// https://school.programmers.co.kr/learn/courses/30/lessons/131701

import java.util.*;

// 16:03 start
class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        // 연속하는 수열의 합으로 만들 수 있는 가짓수

        // 원형수열로 마지막과 처음이 이어져있다.
        // ex. 7 9 1 1 4

        // 원소의 길이가 1일때 2일때,... n일때

        // deque 에 담기 가장 마지막 담고 1로 시작 5 1 2 3 4 5 1

        // ex 부분수열 1일때 1 2 3 4 5
        // ex 부분수열 2일때 1 2 3 4 5 1
        // ex 부분수열 3일때 1 2 3 4 5 1 2
        // 결론 k개의 부분수열을 구할때는 n + k-1개의 수열을 배치하여 개수 구하기 ...

        Set<Integer> result = new HashSet<>();

        int n = elements.length;

        for (int k = 1; k <= n; k++) {
            Deque<Integer> deque = new LinkedList<>();

            for (int i = 0; i < n + k - 1; i++) {
                int roundIndex = i % n;
                deque.add(elements[roundIndex]);

                if (deque.size() == k) {
                    result.add(deque.stream().mapToInt(t -> t).sum());
                    deque.removeFirst();
                }
            }

        }
        System.out.println("result = " + result.toString());

        answer = result.size();
        return answer;
    }
}
