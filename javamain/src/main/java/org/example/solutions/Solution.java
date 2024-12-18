package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/258711?language=java


import java.util.*;
//도넛모양, 막대모양, 팔자모양
// 새로운 정점 생성 & 새로운 정점에서 모든 그래프의 임의의 한점에 간선 추가
// -> 새로운 정점은 들어오는 간선 X, 나가는 간선만 존재
// but. 막대모양의 첫점도 나가는 간선 1개만 존재
// 도넛 모양, 막대모양, 8자 모양 그래프의 수의 합은 2 이상 이므로 새로운 정점 찾기는 나가는 선만 존재하고, 나가는 선이 1개 초과인 것!
// 그러면 이제 그래프의 모양을 분류 해야함
// 막대 모양 -> 순환 X 이면 됨 (시작점 찾기? -> 나가는 선이 1개인 것 찾기)
// 도넛 모양  -> 중복이 발생했지만 더이상 갈곳이 존재 X (시작점 찾기? -> 그냥 아무데서나 시작 가능)
// 8자 모양  -> 중복이 발생했지만 갈곳이 존재 함 (시작점 찾기? -> 아무데서나 가능)

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        // map 에 간선 정리
        HashMap<Integer, List<Integer>> edgeMap = new HashMap<>();
        HashMap<Integer, int[]> edgeInOutMap = new HashMap<>(); // edge: in | out
        for (int[] edge : edges) {
            List<Integer> edgeList = edgeMap.getOrDefault(edge[0], new ArrayList<>());
            edgeList.add(edge[1]);
            edgeMap.put(edge[0], edgeList);

            // edge[0] -> edge[1]
                // 1 in
                // 0 out
            // 없다면 만들고 더하고 넣어주기, 있다면 수정만
            int[] edge0Array = edgeInOutMap.getOrDefault(edge[0], new int[2]);
            edge0Array[1]++;
            edgeInOutMap.put(edge[0], edge0Array);

            int[] edge1Array = edgeInOutMap.getOrDefault(edge[1], new int[2]);
            edge1Array[0]++;
            edgeInOutMap.put(edge[1], edge1Array);
        }

        // new Node 찾고 edge 끊기
        Integer newNode = -1;
        for (Integer keyNode : edgeInOutMap.keySet()) {
            int[] inOut = edgeInOutMap.get(keyNode);
            int in = inOut[0];
            int out = inOut[1];

            if (in == 0 && out > 1) {
                newNode = keyNode;
                break;
            }
        }
        // 새로운 노드를 찾았다면 새로운 노드에서 시작하는 간선 끊기
        edgeInOutMap.remove(newNode);
        List<Integer> remove = edgeMap.remove(newNode);
        for (Integer inNode : remove) {
            edgeInOutMap.get(inNode)[0]--;
        }
        answer[0] = newNode;

        ArrayList<Integer> rodStartNode = new ArrayList<>();
        ArrayList<Integer> eightStartNode = new ArrayList<>();
        for (Integer keyNode : edgeInOutMap.keySet()) {
            int[] inOut = edgeInOutMap.get(keyNode);
            int in = inOut[0];
            int out = inOut[1];

            if (in == 0 && out <= 1) {
                rodStartNode.add(keyNode);
                answer[2]++;
            }

            if (out == 2) {
                eightStartNode.add(keyNode);
                answer[3]++;
            }
        }

        // node visit 추가
        HashMap<Integer, Integer> visitMap = new HashMap<Integer, Integer>();
        for (Integer keyNode : edgeMap.keySet()) {
            visitMap.put(keyNode, 0);
        }

        // 막대모양의 시작점부터 visit 시작 result ++ & visit 처리
        for (Integer node : rodStartNode) {
            visitMap.put(node, 1);

            while (! edgeMap.getOrDefault(node, List.of()).isEmpty()) {
                node = edgeMap.get(node).get(0);
                visitMap.put(node, 1);
            }
        }

        // 8자 모양 visit 처리
        for (Integer node : eightStartNode) {
            visitMap.put(node, 1);
            Integer leftNode = edgeMap.get(node).get(0);
            Integer rightNode = edgeMap.get(node).get(1);

            while (visitMap.get(leftNode) == 0) {
                visitMap.put(leftNode, 1);
                leftNode = edgeMap.get(leftNode).get(0);
            }

            while (visitMap.get(rightNode) == 0) {
                visitMap.put(rightNode, 1);
                rightNode = edgeMap.get(rightNode).get(0);
            }
        }

        // 도넛 모양 찾으며 result ++ & visit 처리
        for (Integer node : visitMap.keySet()) {
            if (visitMap.get(node) > 0) {
                continue;
            }

            // 현재 노드가 방문 X라면 방문 & 다음 노드로 이동
            while (visitMap.get(node) == 0) {
                visitMap.put(node, 1);
                node = edgeMap.get(node).get(0);
            }

            answer[1]++;
        }

        return answer;
    }
}