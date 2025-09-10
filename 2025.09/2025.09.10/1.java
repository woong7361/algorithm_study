// https://www.acmicpc.net/problem/1769
// 10 start


import java.io.*;
import java.util.*;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());


        // X의 자리수의 합 -> Y
        // Y < 10 일때 까지 변환하려면 얼마나 변환해야 하는가?

        // 단 X는 자연수 <= 1000000

        // 문제 설계
        // 단순 구현 문제

        int result = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        String X = st.nextToken();

        while (X.length() > 1) {
            X = getPositionTotal(X);
            result++;
        }

        System.out.println(result);
        if (Integer.parseInt(X) % 3 == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        br.close();
    }

    private static String getPositionTotal(String x) {
        int result = 0;

        for (int i = 0; i < x.length(); i++) {
            result += Character.getNumericValue(x.charAt(i));
        }

        return String.valueOf(result);
    }

}


