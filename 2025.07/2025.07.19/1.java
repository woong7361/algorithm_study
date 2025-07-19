
// https://school.programmers.co.kr/learn/courses/30/lessons/136797
// 00 start


import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        // 지도에 부대가 위치한 지역을 포함한 각 지역은 유일한 번호로 구분된다.
        // 두 지역간 길을 통해 이동하는 시간은 1이다.
        // 적군의 방해로 인해, 임무의 시작때와 돌아오는 경로가 없어져 복귀가 불가능할 수 있다.
        // 최단시간 부대 복귀 시간은?

        // 총 지역의 수 n
        // 양방향 길 정보 roads [a, b] a=!b 이다.
        // 각 부대원이 위치한 서로 다른 지역들을 나타내는 sources
        // 도착지 destination

        // n <= 100,000
        // roads.length <= 500,000
        // sources <= 500

        // 문제 설계
        // 결국 destination에서 각 node까지 걸리는 모든 길이를 구하고
        // 그중 sources에 해당하는 것들중 MAX값을 구한다. 단 복귀가 불가능한 부대가 있을경우 -1이다.
        // 다익스트라 방법으로 해결한다면 될듯하다.

        // 생각할 사항
        // road
        //      a == b 가 주어질 경우, 중복된 [a, b] or [b, a] 가 주어질 경우, node에서 벗어난 a, b가 주어질 경우
        // sources & destination
        //      node에서 벗어난 sources가 주어질 경우
        // n과 sources가 같을 경우?

        // 문제 해결
        // 다익스트라로 해결한다.
        // int[n+1] 배열 생성 & -1로 초기화(일단 못감)
        // 최솟값 찾아가며 다익스트라 갱신
        // 완료되면 그중 souces 값들만 filtering하여 값들 반환

        int[] arriveTimes = new int[n + 1];
        Arrays.fill(arriveTimes, -1);
        arriveTimes[destination] = 0;

        HashMap<Integer, List<Integer>> roadGraph = new HashMap<>();
        for (int[] road : roads) {
            if (! roadGraph.containsKey(road[0])) {
                roadGraph.put(road[0], new ArrayList<>());
            }
            if (! roadGraph.containsKey(road[1])) {
                roadGraph.put(road[1], new ArrayList<>());
            }

            roadGraph.get(road[0]).add(road[1]);
            roadGraph.get(road[1]).add(road[0]);
        }

        Queue<Integer> queue = new LinkedList<>(List.of(destination));
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            List<Integer> nextNodes = roadGraph.getOrDefault(node, List.of());

            for (Integer nextNode : nextNodes) {
                // 갱신되지 않은 노드만 탐색한다.
                if (arriveTimes[nextNode] == -1) {
                    queue.add(nextNode);
                    // 값 갱신
                    arriveTimes[nextNode] = arriveTimes[node] + 1;
                }
            }
        }

        for (int i = 0; i < sources.length; i++) {
            answer[i] = arriveTimes[sources[i]];
        }

        return answer;
    }
}
