// https://school.programmers.co.kr/learn/courses/30/lessons/42626#
// 43 start


import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        // 음식의 스코빌 지수를 k 이상으로 만들고 싶다.
        // Leo는 스코빌 지수가 가장 낮은 두 개의 음식을 아래와 같이 섞는다.
        // 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
        // 모든 음식의 스코빌 지수가 k이상이 될때까지 섞는다.
        // 최소 횟수는?

        // 우선순위큐
        // k 이상인 수는 추가하지 않는다.
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i : scoville) {
            if (i < K) {
                heap.add(i);
            }
        }
        if (heap.size() != scoville.length) {
            heap.add(K + 1);
        }
        // 1 2 7
        // 5 7

        // 1 5 7
        // 7

        // 1 2

        // 하나 뽑고
        // 또하나 뽑는다.
        // 섞는다.
        // k 이상일경우? -> 버린다.
        // k 미만일경우? 다시 섞는다.
        // 없을때까지 반복
        while (heap.size() >= 2 && heap.peek() < K) {
            int mixed = heap.poll() + heap.poll() * 2;

//            if (mixed < K) {
                heap.add(mixed);
//            }

            answer++;
        }

        if (heap.peek() < K) {
            return -1;
        } else {
            return answer;
        }

    }
}
