// https://www.acmicpc.net/problem/1038
// 06 start


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());


        // 음이아닌 정수 X의 자릿수가 가장 큰 자릿수부터 감소한다면 그 수를 감소하는 수
        // 321 950 감소수
        // 322 958 X
        // N번쨰 감소하는 수를 출력하라

        // 0은 0번쨰 감소수
        // 1은 1번째 감소수

        // 1 2 3 4 5 6 7 8 9
        // 10
        // 20 21
        // 30 31 32
        // 40 41 42 43

        // 문제 설계
        // 성공 -> +1 -> 반복
        // 실패 -> 실패 자릿수 +1
        // dfs? 굳이? 반복문으로 하는게?

        // 가장 큰 수
        // 9876543210

        int N = Integer.parseInt(br.readLine());
        if (N == 0) {
            System.out.println(0);
            return;
        }


        long number = 1;
        while (number <= 9876543210L) {
            int n = checkDecreaseNumber(number);

            // if true -> N-1 & number+1
            if (n == -1) {
                if (--N <= 0) break;

                number++;
            }
            // if false -> fail number + 1
            else {
                for (int i = 0; i < n; i++) number = number / 10;
                number++;
                for (int i = 0; i < n; i++) number = number * 10;
            }
        }

        if (number > 9876543210L) {
            System.out.println(-1);
        } else {
            System.out.println(number);
        }

        br.close();
    }

    private static int checkDecreaseNumber(long number) {
        String numberString = String.valueOf(number);

        //531
        for (int i = 0; i < numberString.length()-1; i++) {
            long divNum = 1;
            for (int j = 0; j < i; j++) divNum = divNum * 10;

            if ((number / (divNum*10)) % 10 <= (number / divNum) % 10) {
                return i + 1;
            }
        }

        return -1;
    }
}


