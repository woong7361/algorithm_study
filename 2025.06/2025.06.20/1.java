// https://school.programmers.co.kr/learn/courses/30/lessons/12978
// 25 start

import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        // N개의 마을
        // 1 ~ N 까지의 번호
        // 양방향 도로 연결
        // 도로별 시간은 각각 다르다.
        // 1번 마을의 음식적으로 부터 K시간 이하로 배달이 가능한 마을만 주문을 받으려고 한다.

        // 1 <= N <= 50
        // 1 <= road <= 2000
        // road = [a, b, c] -> a b 마을, c 시간
        // a,b 두 마을을 연결하는 도로 중복 가능
        // 1 <= k <= 500,000


        // 경계값 주의사항
        // ! road 중복 가능
        // ! 나 자신은 K 이하다!

        // 해결 방법
        // 1번 마을에서 다른 마을로 가는 시간에 대한 모든 시간값 -> 다익스트라
        // dictionary로 가장 짧은 도로 (중복 제거) 양방향 추가
        // 1번 마을에서부터 시작하여 갈 수 있는 마을 최솟값 갱신 (bfs?)
        // 결과값 구하기

        HashMap<Integer, List<List<Integer>>> roadMap = new HashMap<>();
        for (int[] value : road) {
            if (roadMap.get(value[0]) == null) {
                roadMap.put(value[0], new ArrayList<>());
            }
            if (roadMap.get(value[1]) == null) {
                roadMap.put(value[1], new ArrayList<>());
            }

            Integer prevArriveTime = roadMap.get(value[0]).stream()
                    .filter(it -> it.get(0).equals(value[1]))
                    .findFirst()
                    .map(it -> it.get(1))
                    .orElseGet(() -> Integer.MAX_VALUE);

            if (value[2] < prevArriveTime) {
                roadMap.get(value[0]).add(List.of(value[1], value[2]));
                roadMap.get(value[1]).add(List.of(value[0], value[2]));
            }

        }

        int[] arriveTimeArray = new int[N + 1];
        Arrays.fill(arriveTimeArray, Integer.MAX_VALUE);
        arriveTimeArray[1] = 0;

        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((t1, t2) -> t1.get(1) - t2.get(1));
        queue.add(List.of(1, 0));

        while (!queue.isEmpty()) {
            List<Integer> node = queue.poll();
            // 갈 수 있는 곳 모두 찾기
            List<List<Integer>> nextNodes = roadMap.get(node.get(0));

            // 값 모두 최소값으로 갱신
            for (List<Integer> nextNode : nextNodes) {
                if (arriveTimeArray[nextNode.get(0)] > node.get(1) + nextNode.get(1)) {
                    arriveTimeArray[nextNode.get(0)] = node.get(1) + nextNode.get(1);

                    // 갱신된 곳 queue에 추가
                    queue.add(List.of(nextNode.get(0), node.get(1) + nextNode.get(1)));
                }


            }

        }

        return (int) Arrays.stream(arriveTimeArray)
                .filter(it -> it <= K)
                .count();
    }
}
