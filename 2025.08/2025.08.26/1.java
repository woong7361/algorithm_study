// https://www.acmicpc.net/problem/1010
// 32 start


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

        // 왼쪽 다리 N개, 오른쪽 다리 M개 지을 수 있음
        // 연결 N개 가능
        // 다리 겹치기 불가능
        // 다리를 지을 수 있는 최대의 경우 수는?

        // 0 <= N <= M <= 30

        // 문제 설계
        // 위에부터 선택 가능

        int T = Integer.parseInt(br.readLine());
        for (int _t = 0; _t < T; _t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int result = findWay(N, M, 0, 0);

            System.out.println(result);
        }

        br.close();
    }

    private static int findWay(int N, int M, int current, int bridgeCount) {
        if (bridgeCount == N) {
            return 1;
        }

        int result = 0;

        for (int next = current+1; next <= M; next++) {
            result += findWay(N, M, next, bridgeCount + 1);
        }

        return result;
    }


}

