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
        Shark sharkPoint = null;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ocean[i][j] = Integer.parseInt(st.nextToken());
                if (ocean[i][j] == 9) {
                    sharkPoint = new Shark(i, j, 0);
                }
            }
        }

        int time = 0;
        int sharkSize = 2;
        int eatCount = 0;
        boolean isEat = true;
        boolean[][] visit = new boolean[N][N];
        // 못먹을때 까지
        while (isEat) {
            // 현재 탐색하면서, 다음거에 담기
/*            currentGo.clear();
            nextGo.clear();*/
            PriorityQueue<Shark> currentGo = new PriorityQueue<>();
            PriorityQueue<Shark> nextGo = new PriorityQueue<>();
            clearVisit(visit);
            currentGo.add(sharkPoint);
            visit[sharkPoint.x][sharkPoint.y] = true;
            while (!currentGo.isEmpty()) {
                Shark shark = currentGo.poll();

                // 먹을 수 있는 상어가 있는지 확인
                if (ocean[shark.x][shark.y] != 0 && ocean[shark.x][shark.y] != 9 && sharkSize > ocean[shark.x][shark.y]) {
                    ocean[sharkPoint.x][sharkPoint.y] = 0;
                    sharkPoint = new Shark(shark.x, shark.y, 0);
                    ocean[sharkPoint.x][sharkPoint.y] = 9;

                    eatCount += 1;
                    time += shark.distance;
                    if (eatCount == sharkSize) {
                        sharkSize++;
                        eatCount = 0;
                    }
                    break;
                }

                // 다음 depth 추가
                for (int[] direction : directions) {
                    int[] nextPoint = new int[]{shark.x + direction[0], shark.y + direction[1]};
                    // 밖이 아니고, visit이 아니고, 나보다 큰 물고기 칸이 아닐때
                    if (nextPoint[0] >= 0 && nextPoint[0] < N && nextPoint[1] >= 0 && nextPoint[1] < N
                            && !visit[nextPoint[0]][nextPoint[1]]
                            && ocean[nextPoint[0]][nextPoint[1]] <= sharkSize
                    ) {
                        visit[nextPoint[0]][nextPoint[1]] = true;
                        nextGo.add(new Shark(nextPoint[0], nextPoint[1], shark.distance + 1));
                    }
                }

                // 다음거가 있다면 다시 돌리기
                if (currentGo.isEmpty()) {
                    currentGo = nextGo;
                    nextGo = new PriorityQueue<>();
                }

                // 현재도 비어있고, 미래도 비어있다면 끝
                if (currentGo.isEmpty() && nextGo.isEmpty()) {
                    isEat = false;
                    break;
                }

            }
        }

        System.out.println(time);

        br.close();
    }

    public static void clearVisit(boolean[][] visit) {
        for (boolean[] booleans : visit) {
            Arrays.fill(booleans, false);
        }
    }

    public static class Shark implements Comparable<Shark> {
        int x, y, distance;

        public Shark(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Shark o) {
            if (this.distance != o.distance) return Integer.compare(this.distance, o.distance);
            else {
                if (this.x == o.x) return Integer.compare(this.y, o.y);
                else return Integer.compare(this.x, o.x);
            }
        }
    }


}


