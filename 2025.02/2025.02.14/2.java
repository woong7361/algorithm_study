// https://school.programmers.co.kr/learn/courses/30/lessons/159993
// 54 start

import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int answer = 0;

        // 미로 탈출
        // 통로 또는 벽 - 벽 못지나감 - 통로칸 이동 가능
        // 통로중 탈출구 레버를 먼저 당겨야함 그래야 열림
        // 이때 아직 레버를 안당겼더라도 출구칸을 지나갈 수는 있음
        // 최대한 빠르게 이동하는 시간은 얼마인가?
        // 탈출할 수 없다면 0을 반환하라

        // 시작 S, 출구 E, 레버 L, 통로 O, 벽 X

        // bfs로 빠른 탐색이 좋다. -> 시간 단축

        LinkedList<Location> deque = new LinkedList<>();
        if (maps.length == 0) {
            return -1;
        }

        Location endLocation = null, leverLocation = null;

        // start 찾기
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) == 'S') {
                    deque.add(new Location(i, j, 0));
                }
                if (maps[i].charAt(j) == 'E') {
                    endLocation = new Location(i, j, 0);
                }
                if (maps[i].charAt(j) == 'L') {
                    leverLocation = new Location(i, j, 0);
                }
            }
        }

        // start 에서 레버로 이동
        leverLocation = findLocation(maps, deque, new HashSet<>(), leverLocation);
        if (leverLocation == null) return -1;

        deque.clear();
        deque.add(leverLocation);
        // 레버에서 출구로 이동
        endLocation = findLocation(maps, deque, new HashSet<>(), endLocation);
        if (endLocation == null) return -1;

        return endLocation.moveCount;
    }

    private Location findLocation(String[] maps, LinkedList<Location> deque, Set<Location> visitMap, Location targetLocation) {
        Location result = null;
        while (! deque.isEmpty()) {
            Location location = deque.removeFirst();
            visitMap.add(location);

            if (location.row == targetLocation.row && location.col == targetLocation.col) {
                result = location;
                break;
            }

            for (Location newLocation : location.move()) {
                if (newLocation.row < 0 || newLocation.row >= maps.length || newLocation.col < 0 || newLocation.col >= maps[0].length()) {
                    continue;
                }
                if (maps[newLocation.row].charAt(newLocation.col) == 'X') {
                    continue;
                }
                if (visitMap.contains(newLocation)) {
                    continue;
                }
                deque.addLast(newLocation);
            }

        }

        return result;
    }

    private static class Location {
        private int row;
        private int col;
        private int moveCount;

        public Location(int row, int col, int moveCount) {
            this.row = row;
            this.col = col;
            this.moveCount = moveCount;
        }

        public List<Location> move() {
            return List.of(
                    new Location(row + 1, col, moveCount + 1),
                    new Location(row - 1, col, moveCount + 1),
                    new Location(row, col + 1, moveCount + 1),
                    new Location(row, col - 1, moveCount + 1)
            );
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Location location = (Location) object;
            return row == location.row && col == location.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
