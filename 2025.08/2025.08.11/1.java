// https://www.acmicpc.net/problem/17144
// 10 start


import java.io.*;
import java.util.*;

public class Main {
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 집의 크기 R*C인 격자판
        // 공기청소기는 항상 1열에 존재한다.
        // 공기청정기가 설치되어있지 않은 칸에는 미세먼지가 있고, 미세먼지의 양은 Arc이다.

        // 1. 미세먼지 확산 (모든 미세먼지가 있는 칸에서 동시에 발생)
        //  미세먼지는 인접한 4칸으로 확산된다.
        //  홧단되는 양은 Arc/5이고, 소수점은 버린다.
        //  rc에 남은 미세먼지 양은 Arc - (확산된 미세먼지의 양) 이다.
        // 2. 공기청정기 작동
        //  공기청정기에서는 바람이 나온다.
        //  위쪽 공기청정기에서는 반시계방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
        //  바람이 불면 미세먼지가 바람의 방향대로 모두 한칸씩 이동한다.
        //  공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.

        // T초가 지난 후 방에 남아있는 미세먼지의 양은 얼마인가?

        // 첫째줄에 R, C, T가 주어진다.
        // 둘째줄부터 R개의 줄에 Arc가 주어진다. 공기청정기가 설치된곳은 -1이고, 나머지는 미세먼지의 양이다.
        // -1은 위아래로 2개가 붙어있다. & 가장 윗행과 아랫행과 두칸이상 떨어져있다.

        // 문제 설계
        // 구현 문제
        // 1. board를 만들고, 공기청정기의 위치를 구한다.
        // 2. 확산을 하는 함수를 작성한다.
        //  확산은 board 에서 미세먼지가 있는 곳만을 진행하며 new board로 작성한다. (동시에 발생하기에)
        // 3. 공기청정기의 작동 함수를 작성한다.
        //  공기청정기는 첫번째 -1은 반시계, 2번째 -1은 시계 방향으로 값을 옮겨준다.
        //  -1의 위치에 도착했을 때 값을 지운다.

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] board = new int[R][C];
        int airConditionLoc = -1;
        for (int i = 0; i < R; i++) {
            String[] rowStrings = br.readLine().split(" ");
            for (int j = 0; j < rowStrings.length; j++) {
                board[i][j] = Integer.parseInt(rowStrings[j]);

                if (board[i][j] == -1) {
                    airConditionLoc = i;
                }
            }

        }

        for (int i = 0; i < T; i++) {
            // 확산
            board = diffusionBoard(board);
            // 공기청정기
            board = airconditional(board, airConditionLoc - 1, airConditionLoc);
        }
        int answer = sumDust(board);

        System.out.println(answer);


        br.close();
    }

    private static int sumDust(int[][] board) {
        int sum = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                // 공기청정기 넘어가기
                if (board[row][col] == -1) {
                    continue;
                }

                sum += board[row][col];
            }
        }

        return sum;
    }

    private static int[][] airconditional(int[][] board, int highAirConditionalLoc, int lowAirConditionalLoc) {
        int rowLength = board.length;
        int colLength = board[0].length;

        // 위에 공기청정기 반시계방향
        int nextVal = 0;
        for (int i = 1; i < colLength; i++) {
            int temp = board[highAirConditionalLoc][i];
            board[highAirConditionalLoc][i] = nextVal;
            nextVal = temp;
        }
        for (int i = highAirConditionalLoc-1; i >= 0; i--) {
            int temp = board[i][colLength-1];
            board[i][colLength-1] = nextVal;
            nextVal = temp;
        }
        for (int i = colLength-2; i >= 0; i--) {
            int temp = board[0][i];
            board[0][i] = nextVal;
            nextVal = temp;
        }
        for (int i = 1; i < highAirConditionalLoc; i++) {
            int temp = board[i][0];
            board[i][0] = nextVal;
            nextVal = temp;
        }

        // 아래 공기청정기 시계방향
        nextVal = 0;
        for (int i = 1; i < colLength; i++) {
            int temp = board[lowAirConditionalLoc][i];
            board[lowAirConditionalLoc][i] = nextVal;
            nextVal = temp;
        }
        for (int i = lowAirConditionalLoc + 1; i < rowLength; i++) {
            int temp = board[i][colLength-1];
            board[i][colLength-1] = nextVal;
            nextVal = temp;
        }
        for (int i = colLength-2; i >= 0; i--) {
            int temp = board[rowLength-1][i];
            board[rowLength-1][i] = nextVal;
            nextVal = temp;
        }
        for (int i = rowLength-2; i > lowAirConditionalLoc; i--) {
            int temp = board[i][0];
            board[i][0] = nextVal;
            nextVal = temp;
        }

        return board;
    }

    private static int[][] diffusionBoard(int[][] board) {
        int rowLength = board.length;
        int colLength = board[0].length;

        int[][] nextBoard = new int[rowLength][colLength];
        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < colLength; col++) {
                // 공기청정기라면 확산 X
                if (board[row][col] == -1) {
                    nextBoard[row][col] = -1;
                    continue;
                }

                // 미세먼지라면 확산
                // board를 벗어나면 확산 X, 공기청정기라면 확산 X
                if (board[row][col] != 0) {
                    nextBoard[row][col] += board[row][col];

                    for (int[] direction : directions) {
                        int nextRow = row + direction[0];
                        int nextCol = col + direction[1];

                        // 보드 바깥이라면 확산 X
                        if(nextRow < 0 || nextRow >= rowLength || nextCol < 0 || nextCol >= colLength) continue;
                        // 확산된 곳이 공기청정기면 끝
                        if (board[nextRow][nextCol] == -1) continue;

                        // 확산
                        nextBoard[nextRow][nextCol] += board[row][col] / 5;
                        nextBoard[row][col] -= board[row][col] / 5;
                    }

                }
            }
        }

        return nextBoard;
    }


}
