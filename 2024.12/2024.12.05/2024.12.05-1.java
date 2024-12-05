// https://school.programmers.co.kr/learn/courses/30/lessons/340211?language=java

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static class Robot {
        private List<int[]> routes;
        private int[] current;

        private int currentIndex = 0;

        Robot(int[] start, List<int[]> routes) {
            this.routes = routes;
            this.current = Arrays.copyOf(start, 2);
        }


        public void move() {
            // (r,c) r 먼저
            int[] destination = routes.get(currentIndex + 1);

            if (current[0] != destination[0]) {
                current[0] += (current[0] < destination[0]) ? 1 : -1;
            } else {
                current[1] += (current[1] < destination[1]) ? 1 : -1;
            }

            if (current[0] == destination[0] && current[1] == destination[1]) {
                currentIndex += 1;
            }
        }

        public Boolean checkEnd() {
            return currentIndex == routes.size()-1;
        }

        @Override
        public boolean equals(Object robot) {
            if (robot == null) {
                return false;
            }
            if (!(robot instanceof Robot)) {
                return false;
            }
            Robot robot1 = (Robot) robot;

            return robot1.current[0] == this.current[0] && robot1.current[1] == this.current[1];
        }

        @Override
        public int hashCode() {
            return Objects.hash(current[0], current[1]);
        }
    }

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        // start init
            // start, current, dest, move()
        List<Robot> robots = new ArrayList<>();
        for (int i = 0; i < routes.length; i++) {
            robots.add(new Robot(
                    points[routes[i][0] - 1],
                    Arrays.stream(routes[i])
                            .mapToObj((t) -> points[t - 1])
                            .collect(Collectors.toList())
            ));
        }

        // check collision
            // collision check how? -> SET 방식? key value >= 2
            // int[] not equals -> new Class And override HashCode and Equals
        answer += checkCollisionCount(robots);

        // while
            // live robot list when end -> remove robot
        while (! robots.isEmpty()){
            // move
                // move r,c first r, second c
            robots.stream()
                    .forEach(r -> r.move());

            // check collision
            System.out.println("answer = " + answer);
            answer += checkCollisionCount(robots);

            // check end
                // when robot equals dest finish it
            List<Robot> newRobots = new ArrayList<>(List.copyOf(robots));
            for (Robot robot : robots) {
                if (robot.checkEnd()) {
                    newRobots.remove(robot);
                }
            }

            robots = newRobots;
        }

        return answer;
    }

    public Integer checkCollisionCount(List<Robot> robots) {
        HashMap<Robot, Integer> collisionMap = new HashMap<>();
        for (Robot robot : robots) {
            collisionMap.put(robot, collisionMap.computeIfAbsent(robot, r -> 0) + 1);
        }

        return (int) collisionMap.values().stream()
                .filter(v -> v > 1)
                .count();
    }
}
