// https://school.programmers.co.kr/learn/courses/30/lessons/12952
// 04 start


import java.util.*;

class Solution {
    public int[][] queenDirection = {
            {1, 0},{-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public int solution(int n) {
        int answer = 0;
        // n queen solution
        // return solution count

        // ! n <= 15
        // 설계 방안
        // 알고리즘이 따로 있는가? 모르겠다. 나중에 알아보자
        // 하나씩 다해보자 n <= 15 니까 -> dfs로 탐색
        // Q 두고, 상하좌우 대각선까지 visit 처리 반복
        // if 안된다면? 내가 visit 처리한 부분만 되돌리기
        // 내가 처리한 부분만을 어떻게 알것인가? dfs이니 저장공간 조금만 더 쓰는식으로 하자

        // 해결 방안
        // visit borad array booelan[][]
        // dfs -> end condition - n = 0 -> return 1 else 0
        // visit -> list.add & remove

        boolean[][] visit = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visit[i], false);
        }

        return findNQueenSolution(visit, n);


//        return answer;
    }

    private int findNQueenSolution(boolean[][] visit, int n) {
        if (n <= 0) {
            return 1;
        }

        int result = 0;
        for (int queen = 0; queen < visit.length; queen++) {
            List<List<Integer>> currentVisitList = new ArrayList<>();
            // 방문하지 않았다면
            if (! visit[n - 1][queen]) {
//                System.out.println("visit = " + List.of(n-1, queen));
                //방문 처리
                visitProcess(visit, new int[] {n-1, queen}, currentVisitList);
                result += findNQueenSolution(visit, n - 1);
                // 방문처리 풀기
                undoVisitProcess(visit, currentVisitList);
            }

        }

        return result;
    }



    private void visitProcess(boolean[][] visit, int[] queen, List<List<Integer>> currentVisitList) {
        currentVisitList.add(List.of(queen[0], queen[1]));

        for (int[] direct : queenDirection) {
            int coefficent = 1;

            int n = visit.length;
            while (true) {
                int newX = queen[0] + direct[0] * coefficent;
                int newY = queen[1] + direct[1] * coefficent;
                if (!(newX >= 0 && newX < n && newY >= 0 && newY < n)) break;

                // 다른 Q가 아닌 현재 Q가 갈수 있는 방향만 처리
                if (!visit[newX][newY]) {
                    visit[newX][newY] = true;
                    currentVisitList.add(List.of(newX, newY));
                }

                coefficent += 1;
            }

        }
    }

    private void undoVisitProcess(boolean[][] visit, List<List<Integer>> currentVisitList) {
        for (List<Integer> location : currentVisitList) {
            visit[location.get(0)][location.get(1)] = false;
        }
    }
}
