package org.example;

// https://www.acmicpc.net/problem/1021
// 03 start


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // N과 L
        // 합이 N이면서, 길이가 적어도 L인 연속된 음이 아닌 정수 리스트를 구하여라

        // 문제 설계
        // 길이가 짝수일 때 -> (a1+an) * (n/2)    -- n은 원소의 개수
        // 길이가 홀수일 때 -> (a1+an) * (n/2) + a[n/2]
        // 길이가 적어도 L 이니
        //      짝수일 때 n/2 >= L
        //      홀수일 때 n/2 + 1 >= L

        // n >=  (L - n%2) * 2
        // N/n

        // 1 2 3 4 -> 최소 4개 유지
        // 1 2 3 4 5 6 7 -> 5개 N/n 가운데 3

        // 시작위치 찾기 짝 수
        // 10/2 = 5 -> a1 + an = 5 & n = 4
        // a1 + a1 + 3 = 5
        // a1 = 1
        // a1 = (N/(n/2) - (n-1)) / 2

        // 1 2 3 / 3 = 2
        // a1 = 2 - n/2
        // 1 2 3 4

        int start = -1;
        int length = -1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        // 홀 수 일때
        //
        for (int n = L; n <= 100; n++) {
            // n은 원소의 수

            // 짝수일 때
            if (n % 2 == 0) {
                // 수열로 표현 가능할 때
                if (N % (n / 2) == 0) {
                    int a1 = (N / (n / 2) - (n - 1)) / 2;
                    if (a1 >= 0) {
                        start = a1;
                        length = n;
                        break;
                    }
                }
            } else { // 홀 수일 때
                if (N % n == 0) {
                    int a1 = (N / n) - (n / 2);
                    if (a1 >= 0) {
                        start = a1;
                        length = n;
                        break;
                    }
                }

            }
        }


        System.out.println("start = " + start);
        System.out.println("length = " + length);


        br.close();
    }


}
