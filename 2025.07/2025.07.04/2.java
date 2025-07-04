// https://school.programmers.co.kr/learn/courses/30/lessons/250134
// 57 start


import java.util.Arrays;

class Solution {
    int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int solution(int[][] maze) {
        int answer = 0;
        // n*m 크기의 격자 모양의 퍼즐판
        // 빨간 수레 파란 수레
        // 각자 시작칸에서 자신의 도착칸으로 도착해야한다.
        // 각 턴마다 반드시 모든 수레를 상하좌우 인접한 칸 중 한칸으로 이동시켜야한다.
        // 단 방문한 칸은 방문 금지, 도착칸에 도착한 수레는 이동 금지, 동시에 같은 칸으로 이동 금지, 수레끼리 자리 스왑 금지, 벽으로 이동 금지
        // 퍼즐을 푸는데 가장 최소한의 값은 얼마인가?
        // maze의 가로 길이 & 세로 길이 <= 4

        // 문제 설계
        // 수레가 같히는 경우? -> 생각
        // 하나가 움직였을 때 길을 막아서 더 오래걸리는 경우 -> 생각
        // @ 가로 세로가 <=4 이니 -> 움직였을 때 -> max(b_reach, r_reach) 의 값을 계속 계산하며 가장 작은값으로 이동하는 식으로
        // 결국 전탐색 도전 -> dfs

        // 문제 해결
        // 파란 수레 이동, 빨간 수레 이동 but. swap만 잡기
        // 각 경우의 수를 모두 시도해보기 dfs

        boolean[][] rVisit = new boolean[maze.length][maze[0].length];
        boolean[][] bVisit = new boolean[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            Arrays.fill(rVisit[i], false);
            Arrays.fill(bVisit[i], false);
        }

        int[] rLocation = null;
        int[] bLocation = null;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 1) rLocation = new int[]{i, j};
                if (maze[i][j] == 2) bLocation = new int[]{i, j};
            }
        }
        rVisit[rLocation[0]][rLocation[1]] = true;
        bVisit[bLocation[0]][bLocation[1]] = true;

        answer = findPuzzle(maze, rVisit, bVisit, 0, rLocation, bLocation);

        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }

        return answer;
    }

    private int findPuzzle(int[][] maze, boolean[][] rVisit, boolean[][] bVisit, int depth, int[] rLocation, int[] bLocation) {
        // goal에 도착하면 return depth
        if (maze[rLocation[0]][rLocation[1]] == 3 && maze[bLocation[0]][bLocation[1]] == 4) {
//            System.out.println("fin!!! R: "  + Arrays.toString(rLocation) + " B: " + Arrays.toString(bLocation) + " depth: " + depth);
            return depth;
        }
//        System.out.println("R: "  + Arrays.toString(rLocation) + " B: " + Arrays.toString(bLocation) + " depth: " + depth);

        int result = Integer.MAX_VALUE;

        // 가능한 조합 찾기
        // 도착지점에 있다면 움직이지 않기
        if (maze[rLocation[0]][rLocation[1]] == 3) { // r이 도착지점이라면
            for (int[] direction : directions) {
                int[] newBLocation = new int[] {bLocation[0] + direction[0], bLocation[1] + direction[1]};
                int[] newRLocation = new int[] {rLocation[0], rLocation[1]};

                if(! checkFailLocation(maze,rVisit, bVisit, newRLocation, newBLocation, rLocation, bLocation)){
                    bVisit[newBLocation[0]][newBLocation[1]] = true;

                    result = Math.min(result, findPuzzle(maze, rVisit, bVisit, depth+1, newRLocation, newBLocation));

                    bVisit[newBLocation[0]][newBLocation[1]] = false;
                }
            }
        }else if (maze[bLocation[0]][bLocation[1]] == 4) { // b가 도착지점이라면
            for (int[] direction : directions) {
                int[] newBLocation = new int[] {bLocation[0], bLocation[1]};
                int[] newRLocation = new int[] {rLocation[0] + direction[0], rLocation[1] + direction[1]};

                if(! checkFailLocation(maze,rVisit, bVisit, newRLocation, newBLocation, rLocation, bLocation)){
                    rVisit[newRLocation[0]][newRLocation[1]] = true;

                    result = Math.min(result, findPuzzle(maze, rVisit, bVisit, depth+1, newRLocation, newBLocation));

                    rVisit[newRLocation[0]][newRLocation[1]] = false;
                }
            }
        } else { // 둘다 아니라면
            for (int[] rDirection : directions) {
                for (int[] bDirection : directions) {
                    int[] newBLocation = new int[] {bLocation[0] + bDirection[0], bLocation[1] + bDirection[1]};
                    int[] newRLocation = new int[] {rLocation[0] + rDirection[0], rLocation[1] + rDirection[1]};

                    if(! checkFailLocation(maze,rVisit, bVisit, newRLocation, newBLocation, rLocation, bLocation)){
                        rVisit[newRLocation[0]][newRLocation[1]] = true;
                        bVisit[newBLocation[0]][newBLocation[1]] = true;

                        result = Math.min(result, findPuzzle(maze, rVisit, bVisit, depth+1, newRLocation, newBLocation));

                        rVisit[newRLocation[0]][newRLocation[1]] = false;
                        bVisit[newBLocation[0]][newBLocation[1]] = false;
                    }
                }
            }
        }



        return result;
    }

    private static boolean checkFailLocation(int[][] maze, boolean[][] rVisit, boolean[][] bVisit,
                                             int[] rLocation, int[] bLocation, int[] prevRLocation, int[] prevBLocation) {
        // 벽 밖으로 못감
        return 0 > bLocation[0] || bLocation[0] >= maze.length || 0 > bLocation[1] || bLocation[1] >= maze[0].length ||
                0 > rLocation[0] || rLocation[0] >= maze.length || 0 > rLocation[1] || rLocation[1] >= maze[0].length ||
                //벽 못감
                maze[bLocation[0]][bLocation[1]] == 5 || maze[rLocation[0]][rLocation[1]] == 5 ||
                // visit 못감 (but. 이미 도착한 경우 생각해야함)
                (maze[bLocation[0]][bLocation[1]] != 4 && bVisit[bLocation[0]][bLocation[1]]) ||
                    (maze[rLocation[0]][rLocation[1]] != 3 && rVisit[rLocation[0]][rLocation[1]]) ||
                // 같은칸 못감
                (rLocation[0] == bLocation[0] && rLocation[1] == bLocation[1]) ||
                // swap 못함
                (prevBLocation[0] == rLocation[0] && prevBLocation[1] == rLocation[1] && prevRLocation[0] == bLocation[0] && prevRLocation[1] == bLocation[1])
                ;
    }

}
