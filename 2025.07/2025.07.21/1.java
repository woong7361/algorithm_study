// https://school.programmers.co.kr/learn/courses/30/lessons/133500
// 15 start


import java.util.*;

class Solution {
    int answer = 0;
    public int solution(int n, int[][] lighthouse) {
        // 인천 앞바다 1~n 까지 다른번호가 매겨진 등대 n개
        // 뱃길 n-1개
        // 모든 등대는 이어져있다. 이중 몇개만 켜두려한다.
        // 한 뱃길의 양쪽 끝 등대중 적어도 하나는 켜두려고 한다.

        // n <= 100,000

        // maybe dp 문제 ...


        // 문제 설계
        // 결론적으로 모든 등대가 해결되면 끝
        // 한 등대가 켜지면 그와 연결된 모든 등대는 해결된다.(자신 포함)
        // 해결되지 않은 등대가 가장 많이 연결된 등대부터 킨다. (자신 포함)

        // 양방향 graph 만들어서 연결된 각 개수 저장
        // max값 뽑아서 등대 켜기
        // 등대 킬때 map 에서 지우기 & 연결된값 갱신
        // 1자로 연결되어있으면 Timeout 100,000*100,000 가능성 높음

        // 중복 안나옴, a == b 안나옴
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] road : lighthouse) {
            graph.putIfAbsent(road[0], new ArrayList<>());
            graph.putIfAbsent(road[1], new ArrayList<>());

            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        dfs(graph, 1, 0);


        return answer;
    }

    // 1 -> 불을 키라는 시그널, 0 -> 불을 킬 필요가 없다는 시그널
    // 하나라도 불을 키라는 시그널이 들어온다면 켜야한다. 
    public int dfs(HashMap<Integer, List<Integer>> map, int cur, int before) {
        //리프 노드일 경우
        if (map.get(cur).size() == 1 && map.get(cur).get(0) == before)
            return 1;

        //리프 노드가 아닐 경우
        int tmpRes = 0;
        for (int i = 0; i < map.get(cur).size(); i++) {
            int next = map.get(cur).get(i);
            if (next == before) continue;
            tmpRes += dfs(map, next, cur);
        }

        //해당 노드가 불을 킬 필요 없음
        if (tmpRes == 0)
            return 1;

        //해당 노드가 불을 켜야함
        answer++;
        return 0;
    }
}
