// https://school.programmers.co.kr/learn/courses/30/lessons/154540
// 19 start


import java.util.*;

class Solution {
    public int[] solution(String[] maps) {
        // X or 1-9 자연수
        // X 바다, 숫자 무인도
        // 무인도에 적힌 숫자의 합은 최대 며칠을 지낼 수 있는지

        // 각 섬에서 며칠을 지낼 수 있는지 오름차순으로 반환

        // dfs로 합시다.
        // 해결법
        // visit 생성
        // if X가 아니라면 -> 상하좌우 탐험 & 식량 갱신 & 끝나면 식량 총합 추가
        List<Integer> answer = new ArrayList<>();

        if (maps.length == 0 || maps[0].length() == 0) {
            return new int[]{-1};
        }

        boolean[][] visit = new boolean[maps.length][maps[0].length()];
        for (boolean[] booleans : visit) {
            Arrays.fill(booleans, false);
        }

        Stack<List<Integer>> dfs = new Stack<>();
        // map 전체 돌면서 visit하지 않았고, X가 아니라면 탐색 (stack 추가)
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) != 'X' && !visit[i][j]) {
                    dfs.push(List.of(i, j));
                    visit[i][j] = true;
                }

                int result = 0;
                // stack에 값이 있다면 상하좌우 탐색
                while (!dfs.isEmpty()) {
                    // 현재 무인도이니 값 갱신
                    List<Integer> pop = dfs.pop();

                    result += Integer.parseInt(String.valueOf(maps[pop.get(0)].charAt(pop.get(1))));

                    // 상하좌우 판단
                    for (int[] next : fourDirection(pop)) {
                        if (next[0] < 0 || next[0] >= maps.length || next[1] < 0 || next[1] >= maps[0].length()) {
                            continue;
                        }

                        if (maps[next[0]].charAt(next[1]) != 'X' && !visit[next[0]][next[1]]) {
                            dfs.push(List.of(next[0], next[1]));
                            visit[next[0]][next[1]] = true;
                        }
                    }
                    // visit 해봄? -> 무시
                    // 무인도 아님? -> 무시
                    // map size 안임?
                    // 무인도 맞음? -> 추가 & visit 처리

                }

                if (result != 0) {
                    answer.add(result);
                }
            }
        }
        if (answer.isEmpty()) {
            answer.add(-1);
        }

        Collections.sort(answer);

        int[] ints = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            ints[i] = answer.get(i);
        }

        return ints;
    }

    public int[][] fourDirection(List<Integer> current) {
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] direction : directions) {
            direction[0] += current.get(0);
            direction[1] += current.get(1);
        }

        return directions;
    }
}
