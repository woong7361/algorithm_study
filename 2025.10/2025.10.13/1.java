// https://www.acmicpc.net/problem/7562
// 39 start


import java.io.*;
import java.util.*;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static int[][] horizental = {{-1, 0}, {1, 0}};
    public static int[][] vertical = {{0, 1}, {0, -1}};


    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 나이트의 이동 -> 8방향 가능 상하좌우 한번 -> 그에 수직 대응하는 방향 2가지중 하나로 1번
        // 특정 칸으로 최소 이동 거리를 구하여라
        // 한변의 길이 l
        // 나이트가 있는 칸
        // 나이트가 이동 하려는 칸

        // 문제 설계
        // bfs 문제
        // & visit 필수 -> visit에 최소 거리를 두어 현재+1로 활용
        // 시작위치 0

        int C = Integer.parseInt(br.readLine());
        for (int iterNum = 0; iterNum < C; iterNum++) {
            int l = Integer.parseInt(br.readLine());

            int[] start = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] destination = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[][] visit = new int[l][l];
            for (int i = 0; i < l; i++) {
                Arrays.fill(visit[i], -1);
            }

            int t = roundVisit(start, destination, visit);
            System.out.println(t);
        }

        br.close();
    }

    private static int roundVisit(int[] start, int[] destination, int[][] visit) {
        LinkedList<int[]> deque = new LinkedList<>();
        deque.add(start);
        visit[start[0]][start[1]] = 0;

        while (!(start[0] == destination[0] && start[1] == destination[1])) {
            start = deque.poll();

            // visit 안한 곳이라면 추가 & 자신 +1
            for (int[] ver : vertical) {
                for (int[] hor : horizental) {
                    int[] newS1 = new int[]{start[0] + ver[0]*2 + hor[0], start[1] + ver[1]*2 + hor[1]};
                    int[] newS2 = new int[]{start[0] + ver[0] + hor[0]*2, start[1] + ver[1] + hor[1]*2};

                    if (isInBoard(visit, newS1) && isNotVisit(visit, newS1)) {
                        deque.add(newS1);
                        visit[newS1[0]][newS1[1]] = visit[start[0]][start[1]] + 1;
                    }
                    if (isInBoard(visit, newS2) && isNotVisit(visit, newS2)) {
                        deque.add(newS2);
                        visit[newS2[0]][newS2[1]] = visit[start[0]][start[1]] + 1;
                    }
                }
            }

        }

        return visit[destination[0]][destination[1]];
    }

    private static boolean isNotVisit(int[][] visit, int[] newS) {
        return visit[newS[0]][newS[1]] == -1;
    }

    private static boolean isInBoard(int[][] visit, int[] newS) {
        return newS[0] >= 0 && newS[0] < visit.length && newS[1] >= 0 && newS[1] < visit.length;
    }


}


