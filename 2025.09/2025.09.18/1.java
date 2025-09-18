// https://www.acmicpc.net/problem/16493
// 14 start


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 최대한 많은 페이지를 읽고 연체없이 반납하기
        // 한챕터를 읽으면 끝까지 봐야한다.
        // 남은기간 N일, 챕터당 챕터를 전부 읽는데 소요되는 일수와 페이지
        // N일간 최대 페이지수는?

        // 1<=N<=200, 1<=M<=20

        // 문제 설계
        // dp 가방문제
        // int [M][N]

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] chapter = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            chapter[i][0] = Integer.parseInt(st.nextToken());
            chapter[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] bagDp = new int[M][N];
        for (int page = 0; page < M; page++) {
            for (int day = 0; day < N; day++) {
                if (page == 0) {
                    bagDp[page][day] = day >= (chapter[page][0]-1) ? chapter[page][1] : 0;
                } else {
                    if (day < (chapter[page][0]-1)) {
                        bagDp[page][day] = bagDp[page-1][day];
                    } else if (day == (chapter[page][0]-1)) {
                        bagDp[page][day] = Math.max(bagDp[page - 1][day], chapter[page][1]);
                    } else {
                        bagDp[page][day] = Math.max(bagDp[page - 1][day - chapter[page][0]] + chapter[page][1], bagDp[page - 1][day]);
                    }
                }
            }
        }
        System.out.println(bagDp[M-1][N-1]);

        br.close();
    }
}


