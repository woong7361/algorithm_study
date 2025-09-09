// https://www.acmicpc.net/problem/1051
// 52 start


import java.io.*;
import java.util.*;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());


        //N, M 주어진다.
        // N * M 행렬
        // 각 꼭짓점의 수가 같은 가장 큰 정사각형의 크기는?

        // 문제 해결
        // row, col 에서 length를 늘려가며 row, col) row+l, col) row, col+l), row+l, col+l) 비교
        // 위에는 볼필요 없음 왼쪽도 없음, 우하단만 비교
        // 끝까지 ㄱ

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        int maxSquareSide = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                for (int lenght = 1; row + lenght < N && col + lenght < M; lenght++) {
                    int target = board[row][col];
                    if (
                            board[row][col] == target
                                    && board[row][col + lenght] == target
                                    && board[row + lenght][col] == target
                                    && board[row + lenght][col + lenght] == target
                    ) {
                        maxSquareSide = Math.max(maxSquareSide, (lenght + 1));
                    }
                }
            }
        }

        int x = maxSquareSide * maxSquareSide;
        System.out.println(x);

        br.close();
    }

}


