package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/86971
// 20 start


import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        // n개의 송전탑이 트리형태로 연결
        // 하나를 끊어 네트워크를 2개로 분할하려한다.
        // 이때 두개의 전력망이 갖게되는 송전탑의 개수를 최대한 비슷하게
        // 2<=n<=100

        // 1. 전선을 끊었을때 네트워크가 2개로 분리되는가?
        // 2. 개수 차이는?

        // 결론 edge 하나 끊고 edge node 2개 visit visit 해서 개수 보기 -> 사실 1개만 보면됨

        HashMap<Integer, List<Integer>> edgeMap = new HashMap<>();
        for (int[] wire : wires) {
            edgeMap.putIfAbsent(wire[0], new ArrayList<>());
            edgeMap.putIfAbsent(wire[1], new ArrayList<>());

            edgeMap.get(wire[0]).add(wire[1]);
            edgeMap.get(wire[1]).add(wire[0]);
        }

        for (int[] cutWire : wires) {
            Stack<Integer> stack = new Stack<>();
            stack.add(cutWire[0]);

            List<Integer> t1 = edgeMap.get(cutWire[0]);
            List<Integer> t2 = edgeMap.get(cutWire[1]);

            // remove wire
            t1.remove(Integer.valueOf(cutWire[1]));
            t2.remove(Integer.valueOf(cutWire[0]));

            // perform
            boolean[] visit = new boolean[n+1];
            Arrays.fill(visit, false);

            int visitCount = 0;
            while (!stack.isEmpty()) {
                Integer node = stack.pop();

                if (visit[node]) {
                    continue;
                }

                visit[node] = true;
                visitCount += 1;
                List<Integer> edges = edgeMap.getOrDefault(node, List.of());

                stack.addAll(edges);
            }
            answer = Math.min(answer, Math.abs(n - visitCount - visitCount));

            // add wire
            t1.add(cutWire[1]);
            t2.add(cutWire[0]);
        }

        return answer;
    }
}