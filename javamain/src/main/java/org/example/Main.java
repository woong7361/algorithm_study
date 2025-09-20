package org.example;

// https://www.acmicpc.net/problem/16236
// 30 start


import java.io.*;
import java.util.*;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // N*N 크기의 공간
        // 물고기 M마리, 아기상어
        // 아기상어의 크기 2, 1초에 1칸 이동 (상하좌우)
        // 자신의 크기보다 큰 물고기칸 못지나간다. 자기와 같은 크기는 지나갈 수 있지만 못먹는다. 자신보다 작은 크기는 먹을 수 있다.
        // 더이상 못먹으면 엄마에게 도움을 요청한다.

        // 먹을 수 있는 물고기가 있을 때 가장 가까운 고기를 먹으러간다.
        // 거리가 같다면 가장 위, 왼쪽에 있는 물고기를 우선한다.
        // 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.


        // 문제 설계
        // bfs 탐색
        // PriorityQueue에 같은 depth를 모두 넣고 정렬 후 확인 하는 방식으로 구현
        int N = Integer.parseInt(br.readLine());
        int[][] ocean = new int[N][N];
        int[] sharkPoint = new int[2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ocean[i][j] = Integer.parseInt(st.nextToken());
                if (ocean[i][j] == 9) {
                    sharkPoint[0] = i;
                    sharkPoint[i] = j;
                }
            }
        }

        PriorityQueue<int[]> currentGo = new PriorityQueue<>((t1, t2) -> {
            // 위쪽 우선 i 가 작은게 앞으로
            // 왼쪽 우선 j가 작은게 앞으로
            if (t1[0] != t2[0]) return t1[0] - t2[0];
            else return t1[1] - t2[1];
        });
        PriorityQueue<int[]> nextGo = new PriorityQueue<>((t1, t2) -> {
            // 위쪽 우선 i 가 작은게 앞으로
            // 왼쪽 우선 j가 작은게 앞으로
            if (t1[0] != t2[0]) return t1[0] - t2[0];
            else return t1[1] - t2[1];
        });

        int time = 0;

        // 못먹을때 까지
        boolean isEat = true;
        while (isEat) {
            isEat = false;

            // current 탐색
            currentGo.add(sharkPoint);
            while (!currentGo.isEmpty()) {
                int[] poll = currentGo.poll();
                //

                if (currentGo.isEmpty()) {
                    currentGo = nextGo;
                    nextGo =
                }
            }
        }


        br.close();
    }

    public PriorityQueue<int[]> newPriorityQueue() {
        return new PriorityQueue<>((t1, t2) -> {
            // 위쪽 우선 i 가 작은게 앞으로
            // 왼쪽 우선 j가 작은게 앞으로
            if (t1[0] != t2[0]) return t1[0] - t2[0];
            else return t1[1] - t2[1];
        });
    }

}


