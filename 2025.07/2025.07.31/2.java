// https://www.acmicpc.net/problem/17070
// 43 start

import java.io.*;
import java.util.*;

public class Main {
    public int answer = 0;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";


        // n*n의 격자판
        // 파이프 방향은 우, 우하향, 하향 3가지 가능
        // 파이프는 45도 회전 가능
        // 격자판은 빈칸, 벽이 있고 벽이있는 곳으로는 이동 불가능
        // 처음 파이프는 (1,1) (1,2)를 차지하고 있고 방향은 가로이다. 파이프를 n*n으로 이동시키는 방법의 수는?
        // 3<=N<=16

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        for (int row = 0; row < N; row++) {
            String[] line = br.readLine().split(" ");

            for (int col = 0; col < N; col++) {
                board[row][col] = Integer.parseInt(line[col]);
            }
        }

        int answer = dfs(0, 1, "R", N, board);
        System.out.println(answer);
    }

    public static int dfs(int row, int col, String direc, int N, int[][] board) {
        // if 끝이면 answer ++
        if (row == N - 1 && col == N - 1) {
            return 1;
        }

        int result = 0;

        List<String> nextDirecs;
        if (direc.equals("R")) {
            nextDirecs = List.of("R", "RD");
        } else if (direc.equals("D")) {
            nextDirecs = List.of("D", "RD");
        } else if (direc.equals("RD")) {
            nextDirecs = List.of("R", "D", "RD");
        } else {
            nextDirecs = List.of();
        }

        for (String nextDirec : nextDirecs) {
            if (checkValidate(row, col, nextDirec, N, board)) {
                if (nextDirec.equals("R")) {
                    result += dfs(row, col+1, nextDirec, N, board);
                } else if (nextDirec.equals("D")) {
                    result += dfs(row+1, col, nextDirec, N, board);
                } else if (nextDirec.equals("RD")) {
                    result += dfs(row+1, col+1, nextDirec, N, board);
                }
            }
        }

        return result;
    }

    private static boolean checkValidate(int row, int col, String nextDirec, int N, int[][] board) {
        if (nextDirec.equals("R")) {
            if (col + 1 >= N) {
                return false;
            }
            if (board[row][col + 1] == 1) {
                return false;
            }
        } else if (nextDirec.equals("D")) {
            if (row + 1 >= N) {
                return false;
            }
            if (board[row + 1][col] == 1) {
                return false;
            }
        } else if (nextDirec.equals("RD")) {
            if (row + 1 >= N || col + 1 >= N) {
                return false;
            }
            if (board[row + 1][col] == 1 || board[row][col + 1] == 1 || board[row + 1][col + 1] == 1) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }
}
