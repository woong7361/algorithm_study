// https://www.acmicpc.net/problem/1052
// 38 start


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // N개의 물병
        // 모든 물병에는 물이 1L씩 들어있다.
        // 한번에 K개의 물병을 옮길 수 있다.
        // 물병의 물을 재분배해, k개를 넘지 않는 비어있지 않은 물병을 만드려한다.

        // 물의 재분배 법칙
        // 같은 양의 물병 2개를 고른다. 그다음 한개의 물병에 다른 한쪽에 있는 물을 모두 붇는다.
        // 이때문에 K개를 넘지 않는 비어있지 않은 물병을 만드는 것이 불가능할 수 도 있다.
        // 따라서 상점에서 물병을 살 수 있다. (1L)

        // 상점에서 사야하는 물병의 최솟값을 구하여라
        // 물병에 물을 무한대로 부을 수 있다.

        // 1 <= N < 10^7 , 1 <= K <= 1000

        // 1 1 1 1 1 1 1 1 1 1 1 1 1
        // 2 2 2 2 2 2 1
        // 4 4 4 1
        // 8 4 1
        // 8 4 2 +1
        // 8 4 4 +2
        // 8 8

        // 문제 설계
        // N/2 -> 2의 개수  N%2 -> 1의 개수
        // 반복
        // int[] 배열에 담아둔다.
        // sum을 반복하면서 상점에서 살 물건을 구한다.

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] waters = new int[100];

        int liter = 1;
        while (N > 0) {
            waters[liter] = N / 2;
            waters[liter - 1] = N % 2;
            N = N / 2;
            liter++;
        }

        int buyCount = 0;
        for (int i = 0; i < 99; i++) {
            System.out.println(Arrays.toString(waters));
            if (waters[i] > 1) {
                waters[i + 1] += waters[i] / 2;
                waters[i] = waters[i] % 2;
            }

            if (Arrays.stream(waters).sum() <= K) {
                break;
            }

            if (waters[i] == 1) {
                buyCount += (1 << i);
                waters[i] = 0;
                waters[i + 1] += 1;
            }

        }

        if (Arrays.stream(waters).sum() <= K) {
            System.out.println(buyCount);
        } else {
            System.out.println(-1);
        }

        br.close();
    }
}


