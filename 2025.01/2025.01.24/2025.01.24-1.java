

// https://school.programmers.co.kr/learn/courses/30/lessons/172928?language=java


import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        // route: 방향 거리
        // 공원을 벗어나는지?, 장애물을 만나는지? -> 무시 or 수행
        // ----W, | H
        // 최종 결과가 어디인지 출력
        // 시작 위치 찾기
        int[] location = {0, 0};
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length(); j++) {
                if (park[i].charAt(j) == 'S') {
                    location[0] = i;
                    location[1] = j;
                }
            }
        }

        for (int i = 0; i < routes.length; i++) {
            String[] route = routes[i].split(" ");
            int[] direction;
            switch (route[0]) {
                case "N" -> {
                    direction = new int[]{-1, 0};
                    break;
                }
                case "S" -> {
                    direction = new int[]{1, 0};
                    break;
                }
                case "W" -> {
                    direction = new int[]{0, -1};
                    break;
                }
                case "E" -> {
                    direction = new int[]{0, 1};
                    break;
                }
                default -> {
                    throw new IllegalArgumentException("what?");
                }
            }

            moveIfSafe(location, direction, Integer.parseInt(route[1]), park);
        }

        return location;
    }

    private void moveIfSafe(int[] location, int[] direction, int distance, String[] park) {
        // 테이블을 넘어가지 않는지
        int i1 = location[0] + direction[0] * distance;
        int i2 = location[1] + direction[1] * distance;

        if (i1 < 0 || i1 >= park.length || i2 < 0 || i2 >= park[0].length()
        ) {
            return;
        }
        // 하나씩 살펴보면서 장애물이 있는지 없는지

        for (int i = 1; i < distance+1; i++) {
            int[] checkLocation = {location[0] + direction[0] * i, location[1] + direction[1] * i};
            if (park[checkLocation[0]].charAt(checkLocation[1]) == 'X') {

                return;
            }
        }

        location[0] = i1;
        location[1] = i2;
    }
}
