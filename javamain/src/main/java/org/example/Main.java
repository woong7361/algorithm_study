package org.example;

// https://www.acmicpc.net/problem/1010
// 32 start


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 18/2 = 9 -> 9 10
        // 18/3 = 6 -> 5 6 7
        // 18/4 = 4.xx ->  3 4 5 6
        // 18/5 = 3.xx ->  2 3 4 5 6
        // 45/10 = 4.5


        // 길이가 짝수라면 x * n/2 = T
        // 길이가 홀수라면

        br.close();
    }


}


