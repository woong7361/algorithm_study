package org.example;

// https://www.acmicpc.net/problem/1647
// 03 start


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // N개의 집과 그 집들을 연결하는 M개의 길로 이루어져있다.
        // 길은 양방향이다.
        // 길을 유지하는 유지비가 존재한다, 임의의 두 집사이에는 경로가 항상 존재한다.
        // 마을을 2개로 분할하려고 한다. 각 분리된 마을 안에는 집들이 서로 연결되어있어야 한다.
        // 마을에는 집이 하나 이상 있어야한다.
        // 분리된 두 마을 사이에 있는 길들은 없엘 수 있다.
        // 그리고 각 분리된 마을 안에서도 임의의 두 집 사이에 경로가 존재하면서 길을 더 없앨 수 있다.
        // 길의 유지비를 최소로 하는 경우는 언제인가?
        // 2 <= N <= 100000, 1 <= M <= 1000000
        // A B C - C가 길의 유지비다.

        // 문제 설계
        // 집을 하나의 트리로 만든다. 최소 트리


        br.close();
    }


}
