import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // N행 M열의 표 A가 있다.
        // 연두는 서로 다른 1개 이상의 칸을 선택 선택한 순서대로 등차수열을 이루어야한다.
        // 연두가 만들 수 있는 정수 중 가장 큰 수는?
        // 1 <= N, M <= 9
        // 0 <= 숫자 <= 9

        // dfs가 편하다 length 구별하지 않아도됨
        // dfs 선택 & 완전제곱수 확인 -> visit 인지 확인 & 등차수열인지 확인 -> 더이상 선택 못하면 끝

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[][] board = new String[N][M];

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < row.length(); j++) {
                board[i][j] = String.valueOf(row.charAt(j));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                findMaxSquare(board, board[i][j], i, j, 0, 0);
            }
        }

        System.out.println(String.format("%.0f", maxResult));

        br.close();
    }


    private static void findMaxSquare(String[][] board, String result, int currentRow, int currentCol, int dRow, int dCol) {
        // 제곱수인지 확인 후 max 갱신
        double sqrt = Math.sqrt(Double.parseDouble(result));
        if (sqrt == Math.floor(sqrt)) {
            maxResult = Math.max(maxResult, Double.parseDouble(result));
        }

        double maxSqrt = -1;

        // 다음 갈 곳을 확인
        // 처음이라면 dRow, dCol 정의하기 & 자신의 위치 제외하고 아무데나 이동
        if (result.length() == 1) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (i == currentRow && j == currentCol) continue;
                    findMaxSquare(board, result + board[i][j], i, j, i - currentRow, j - currentCol);
                }
            }
        } else {
            int nextRow = currentRow + dRow;
            int nextCol = currentCol + dCol;

            // 보드 내부에 있다면
            if (0 <= nextRow && nextRow < board.length && 0 <= nextCol && nextCol < board[0].length) {
                findMaxSquare(board, result + board[nextRow][nextCol], nextRow, nextCol, dRow, dCol);
            }
        }
    }
