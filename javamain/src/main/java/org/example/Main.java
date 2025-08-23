package org.example;

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

        // line을 받는다.
        // 0의 개수 - count를 센다.
        //      K보다 크다면 넘긴다.
        //      K - count % 2 == 1 넘긴다.
        //      위의 조건이 충족된다면 Map에 추가하여 count를 증가시킨다.
        // Map의 value중 max값을 반환한다.

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] board = new String[N];
        HashMap<String, Integer> rowMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine();
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            rowMap.putIfAbsent(board[i], 0);

            int zeroCount = 0;
            for (int j = 0; j < M; j++) {
                if (board[i].charAt(j) == '0') zeroCount++;
            }

            if (zeroCount <= K && ((K-zeroCount) % 2) == 0) {
                rowMap.put(board[i], rowMap.get(board[i]) + 1);
            }
        }

        Integer result = rowMap
                .values()
                .stream()
                .max(Comparator.comparingInt(integer -> integer))
                .orElse(0);

        System.out.println(result);

        br.close();
    }


}


