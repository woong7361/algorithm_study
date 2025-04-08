package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/81302
// 54 start


import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        // 대기실 5개, 각 대시길 크기는 5*5 크기
        // 응시자들끼리 맨해튼 거리가 2이하로 앉지 않기
        // 단 응시자가 앉아있는 자리 사이가 파티션으로 막혀있을 경우 허용

        // 거리두기를 잘 지키고 있는지 확인

        //맨해튼 거리 = (r1, c1), (r2, c2) -> |r1 - r2| + |c1 - c2|
        // 해결방법
        // dfs로 visit한다. 파티션은 통과하지 못한다. 상하좌우만으로 이동이 가능하다.

        // !! 사람을 찾는다 -> 이동거리가 2 이내에 다른 사람이 존재한다? (상하좌우만으로 이동) -> 있다면 X, 없다면 계속
        for (int index = 0; index < places.length; index++) {
            int result = 1;
            String[] board = places[index];
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {

                    // 사람이라면
                    if (board[row].charAt(col) == 'P') {
                        boolean[][] visit = new boolean[5][5];
                        for (boolean[] booleans : visit) {
                            Arrays.fill(booleans, false);
                        }
                        if (!validateDistance(board, row, col, 0, visit)) {
                            result = 0;
                            break;
                        }
                    }
                }
            }

            answer[index] = result;
        }

        return answer;
    }

    private boolean validateDistance(String[] board, int row, int col, int distance, boolean[][] visit) {
        int[][] ways = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        if (visit[row][col]) {
            return true;
        }
        if (distance > 2) {
            return true;
        }

        if (distance != 0 && board[row].charAt(col) == 'P') {
            return false;
        } else if (board[row].charAt(col) == 'X') {
            return true;
        }

        visit[row][col] = true;

        boolean result = true;
        for (int[] way : ways) {
            if (row + way[0] < 0 || row + way[0] >= 5 || col + way[1] < 0 || col + way[1] >= 5) {
                continue;
            }
            result = result && validateDistance(board, row + way[0], col + way[1], distance + 1, visit);
        }

        return result;
    }
}
