// https://school.programmers.co.kr/learn/courses/30/lessons/250136

import java.util.*;

class Solution {
    public int solution(int[][] land) {
        int answer = 0;

        // 석유 시추
        // 세로길이 n, 가로길이 m
        // 시추관은 열 하나를 관통
        // 가장 많은 석유량 return
        // position list & 크기 저장

        // 석유 땅 나누기 할것 시작 땅 이름 2++ & visit 처리
        // 성능 생각하여 bfs 반복문으로 진행, 상하좌우 살피기
        // Map으로 <땅이름, 석유 시추량> 저장
//        List<Integer> dxy = List.of(-1, 1);
        int[][] dxy = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        HashMap<Integer, Integer> landMap = new HashMap<>();

        int landName = 2;
        int rowLength = land.length;
        int columnLength = land[0].length;
        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < columnLength; col++) {
                // 석유땅 & 방문X 라면
                if (land[row][col] == 1) {
                    LinkedList<int[]> queue = new LinkedList<>();

                    int count = 0;
                    queue.add(new int[] {row, col});
                    while (!queue.isEmpty()) {
                        int[] point = queue.poll();

                        if (land[point[0]][point[1]] != 1) {
                            continue;
                        }

                        count++;
                        land[point[0]][point[1]] = landName;

                        // | -1 0 | 1 0 | 0 -1 | 0 1 |
                        for (int[] d : dxy) {
                            int nextRow = point[0] + d[0];
                            int nextCol = point[1] + d[1];

                            if (nextRow >= 0 && nextRow < rowLength
                                    && nextCol >= 0 && nextCol < columnLength
                            ) {
                                queue.add(new int[]{nextRow, nextCol});
                            }
                        }

                    }

                    landMap.put(landName++, count);
                }
            }
        }

        // 각 세로축으로 반복문 진행
        // set에 땅이름 없으면 추가 & 시추량 추가
        for (int col = 0; col < columnLength; col++) {

            int columnResult = 0;
            HashSet<Integer> landSet = new HashSet<>();
            for (int row = 0; row < rowLength; row++) {
                int currentLandName = land[row][col];

                if (currentLandName == 0) {
                    continue;
                }

                if (! landSet.contains(currentLandName)) {
                    landSet.add(currentLandName);
                    columnResult += landMap.get(currentLandName);
                }
            }

            answer = Math.max(answer, columnResult);
        }

        return answer;
    }
}
