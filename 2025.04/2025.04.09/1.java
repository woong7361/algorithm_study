// https://school.programmers.co.kr/learn/courses/30/lessons/77485
// 26 start


import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        // r*c 크기 행렬
        // 시계방향 회전
        // 테두리만 이동

        // r,c -> r,c+1 가로 이동
             // if 가로 이동시 c+1이 지정한 테두리를 벗어난다면? -> 세로 이동으로
        // r,c -> r+1,c 세로 이동

        // 1.  좌상단에서 시작 -> 오른쪽 이동, +1이 넘어간다면 -1 이동

        // 단순히 위에서 -1까지 오른쪽으로, 오른쪽에서 -1까지 아래로 , 아래서 -1까지 왼쪽으로, ....


        int[][] table = new int[rows][columns];
        for (int i = 0; i <rows; i++) {
            for (int j = 0; j < columns; j++) {
                table[i][j] = i * columns + j + 1;
            }
        }

        for (int[] query : queries) {
            rotate(table, query);
        }

        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(table, queries[i]);
        }

        for (int[] ints : table) {
            System.out.println("Arrays.toString(ints) = " + Arrays.toString(ints));
        }

        return answer;
    }

    private int rotate(int[][] table, int[] query) {
        // query ex. 2 2 5 4 -> table[1][1] ~ table[4][3]
        // query ex. x1 x2 y1 y2 -> table[x1-1][x2-1] ~ table[y1-1][y2-1]
        // table[x1][x2] ~ table[x1][y2-1]  오른쪽으로 값 옮기기
        // [x1][y2] ~ [y1-1][y2] 까지 아래로 값 옮기기
        // [y1][y2] ~ [y1][x2+1] 까지 왼쪽으로 값 옮기기
        // [y1][x2] ~ [x1-1][x2] 까지 위쪽으로 값 옮기기
        int min = Integer.MAX_VALUE;

        int x1 = query[0] - 1, x2 = query[1] - 1, y1 = query[2] - 1, y2 = query[3] - 1;

        int TREdge = table[x1][y2];
        for (int i = y2; i > x2; i--) {
            min = Math.min(min, table[x1][i]);
            table[x1][i] = table[x1][i-1];
        }

        int BREdge = table[y1][y2];
        for (int i = y1; i > x1; i--) {
            min = Math.min(min, table[i][y2]);
            table[i][y2] = table[i-1][y2];
        }
        table[x1 + 1][y2] = TREdge;

        int BLEdge = table[y1][x2];
        for (int i = x2; i < y2; i++) {
            min = Math.min(min, table[y1][i]);
            table[y1][i] = table[y1][i + 1];
        }
        table[y1][y2 - 1] = BREdge;

        for (int i = x1; i < y1; i++) {
            min = Math.min(min, table[i][x2]);
            table[i][x2] = table[i+1][x2];
        }
        table[y1 - 1][x2] = BLEdge;

        return min;
    }
}
