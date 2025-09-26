// https://www.acmicpc.net/problem/10158
// 24 start


import java.io.*;
import java.util.*;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가로 길이가 w, 세로 길이가 h 2차원 격자
        // 왼쪽 아래가 0,0  오른쪽 위가 w,h 이다.
        // p,q 에 개미 한마리가 있다. 개미는 오른쪽 위 45도 방향으로 옮겨간다. -> p+1, q+1
        // 이 속력으로 경계면에 부딛치면 같은 속력으로 반사되어 움직인다.
        // t 시간 후의 개미의 위치를 구하여라

        // 문제 설계
        // 처음 시작 p+1, q+1 로 옮겨가기
        // X축에 부딛쳤을 때 x의 방향이 바뀌어야한다.
        // Y축에 부딛쳤을 때 y의 방향이 바뀌어야한다.
        // 2*w, 2*h 만큼 돌면 제자리로 돌아온다.

        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int time = Integer.parseInt(br.readLine());

        int remainX = time % (2 * w);
        int remainY = time % (2 * h);

        int xDirection = 1, yDirection = 1;
        while (remainX > 0) {
            if (p + xDirection < 0 || p + xDirection > w) {
                xDirection = -xDirection;
            }
            p = p + xDirection;
            remainX--;
        }

        while (remainY > 0) {
            if (q + yDirection < 0 || q + yDirection > h) {
                yDirection = -yDirection;
            }
            q = q + yDirection;
            remainY--;
        }

        System.out.println(p + " " + q);
        br.close();
    }

}


