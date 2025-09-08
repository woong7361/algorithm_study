// https://www.acmicpc.net/problem/1049
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


        // N개 줄 교체 필요
        // 6개 묶음 팔기 or 1개 낮개 팔기
        // 적어도  N개를 사기위한 최소한의 돈은?

        // 문제 설계

        // if 6개 패키지 가격 > 낮개 가격
        // -> 무조건 낮개 가격으로 다 사기
        // else
        // -> 6개 패키지로 사기 -> 낮개가 남았다.
        // -> 패키지가 더 싼가 or 낮개가 더 싼가 확인

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int result = 0;

        int minPackagePrice = Integer.MAX_VALUE;
        int minLinePrice = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            minPackagePrice = Math.min(minPackagePrice, Integer.parseInt(st.nextToken()));
            minLinePrice = Math.min(minLinePrice, Integer.parseInt(st.nextToken()));
        }

        // 패키지가 더 비쌀경우
        if (minPackagePrice >= minLinePrice * 6) {
            result = minLinePrice * N;
        } else {
            int packageCount = N / 6;
            int indivCount = N % 6;

            result = Math.min(minPackagePrice * (packageCount + 1), minPackagePrice * packageCount + minLinePrice * indivCount);
        }

        System.out.println(result);

        br.close();
    }

}

