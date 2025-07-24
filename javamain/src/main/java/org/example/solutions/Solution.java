package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/118669
// 30 start


import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};

        // 등산 코스 정하기
        // n개의 지점 , 1~n의 번호
        // 출/입구, 쉼터, 산봉우리 3개중 하나
        // 양방향 통행가능 등산로, 등산로별 소요시간이 다르다.
        // 쉼터 혹은 산봉우리까지 휴식없이 이동시간 = intensity
        // 출입구에서 출발하여 산봉우리중 한곳만 방문 후 출입구로 되돌아올 때  intensity 가 최소가 되도록 하는 코스는?
        // intensity가 최소가 되는 등산코스가 여러 개라면 그중 산봉우리의 번호가 가장 낮은 등산코스를 선택합니다.

        // 2 <= n<= 50,000
//        n - 1 ≤ paths의 길이 ≤ 200,000  [i, j, w], i/j: node, w: time
        // 1 ≤ gates의 길이 ≤ n
        // 1 ≤ summits의 길이 ≤ n
//        gates와 summits에 등장하지 않은 지점은 모두 쉼터

        // 문제 설계
        // 출입구가 여러개
        // 출입구 -> 산봉우리 -> 출입구 (intensity가 최소가 되도록)
        // A 출입구에서 시작 -> 다른 출입구 만나면 X, 정상까지 ㄱㄱ (최소값으로) 우선순위 큐 다익스트라 (같은 값은 해야함!)
        // 가장 최소값으로 정상찍고 다른 출입구까지 가면 갱신

        // 문제 해결

        // 결과중 이동거리 최솟값으로 갱신 & 같다면 intensity 최솟값으로 갱신 & 같다면 산봉우리의 번호가 가장 작은 등산코스를 선택

        // road graph 생성 <HashMap>
        HashMap<Integer, List<Path>> roadGraph = new HashMap<>();
        for (int[] path : paths) {
            roadGraph.putIfAbsent(path[0], new ArrayList<>());
            roadGraph.putIfAbsent(path[1], new ArrayList<>());

            roadGraph.get(path[0]).add(new Path(path[1], path[2]));
            roadGraph.get(path[1]).add(new Path(path[0], path[2]));
        }

        // gates 에서 시작 PriorityQueue로 다익스트라 거리 최소값 갱신
        // 중복 출입구 & 중복 정상은 끝, intensity 갱신, !!node가 가장 작은값을 우선으로 탐색
        for (int gate : gates) {
            int[] visit = new int[n + 1];

            int intensity = Integer.MAX_VALUE;
            int peek = -1;

            // 최소값이 나오면 그 최솟값의 모든 경로중 intensity가 최소, peek가 최소값을 고르면 된다.
            PriorityQueue<Hiking> remainNode = new PriorityQueue<>((t1, t2) -> t1.cost - t2.cost);
            remainNode.add(gate);


        }


        return answer;
    }

    private static class Path{
        int node;
        int cost;

        public Path(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    private static class Hiking {
        int node;
        int cost;
        int maxIntensity;
        int currentIntensity;
        int peek;

        public Hiking(int node, int cost) {
            this.node = node;
            this.cost = cost;
            this.peek = -1;
            this.maxIntensity = Integer.MAX_VALUE;
            this.currentIntensity = 0;
        }
    }
}