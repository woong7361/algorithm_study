// https://www.acmicpc.net/problem/1026
// 15 start


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가장 큰수에 가장 작은 수를 곱해야한다.

        // N <= 50 자연수
        // Ai, Bi <= 100 음이 아닌 정수

        // priority Queue 정방향 역방향 곱
        // or sorting 곱 -> 편하다. 쓰자

        int N = Integer.parseInt(br.readLine());

        Integer[] A = new Integer[N];
        Integer[] B = new Integer[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A, (integer, t1) -> integer - t1);
        Arrays.sort(B, (integer, t1) -> t1 - integer);

        int result = 0;
        for (int i = 0; i < N; i++) {
            result += A[i] * B[i];
        }

        System.out.println(result);

        br.close();
    }

}


