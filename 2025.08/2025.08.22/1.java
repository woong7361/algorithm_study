// https://www.acmicpc.net/problem/1034
// 30 start


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

        // 각 칸마다 램프가 들어있는 직사각형 모양의 탁자
        // 모든 램프는 꺼져있거나 켜져있다.
        // 각 열에 스위치가 달려있다 키면 그 열의 모든 램프가 toggle된다.
        // 만약 어떤 행에 있는 램프가 모두 켜져있을 때, 그 행이 켜져있다고 말한다
        // 가장 많은 행이 켜져있는 경우는 언제인가?
        // 단 지민이는 스위치 K번을 누른다. 이때 서로다른 스위치 K개를 누르지 않아도 된다.

        // 1 <= N, M 행 열 <= 50
        // 마지막줄 K <= 1000

        // 문제 설계
        // 브루트 포스로 한다면 가장 최악의 경우
        // 50^1000

        // 중복을 제거해보자
        // 1 -> 50까지
        // 단! 남은수가 짝수여야한다. -> 껏다 켰다 반복 가능해야 ...
        // -> 마지막까지 진행 후 남은 수(K)가 홀수라면 무시한다.

        // 그렇다면 경우의 수가 정말 많이 줄 수 있다. 최대 수가 50으로 줄기 때문에

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                board [i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        int K = Integer.parseInt(br.readLine());

        int result = toggleLamp(board, -1, K, new int[M]);

        System.out.println(result);

        br.close();
    }

    private static int toggleLamp(int[][] board, int current, int K, int[] selectedCols) {
        // k번을 다했거나 or 끝까지 갔을때 (K가 짝수)
        if (K == 0 || current == board[0].length) {
            // 홀 수 남았으면 무시
            if (K % 2 == 1) {
                return 0;
            } else {
                return checkRow(board, selectedCols);
            }
        }

        int result = 0;

        for (int next = current + 1; next <= board[0].length; next++) {
            if (next == board[0].length) {
                result = Math.max(result, toggleLamp(board, next, K, selectedCols));
                continue;
            }

            selectedCols[next] = 1;
            result = Math.max(result, toggleLamp(board, next, K - 1, selectedCols));
            selectedCols[next] = 0;
        }

        return result;
    }

    private static int checkRow(int[][] board, int[] selectedCols) {
        int result = 0;
        for (int row = 0; row < board.length; row++) {
            // 0 1 -> 1
            // 1 0 -> 1
            // 0 0 -> 0
            // 1 1 -> 0
            result++;
            for (int col = 0; col < board[0].length; col++) {
                if ((board[row][col] ^ selectedCols[col]) != 1) {
                    result--;
                    break;
                }
            }
        }

        return result;
    }


}


