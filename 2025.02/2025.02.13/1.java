// https://school.programmers.co.kr/learn/courses/30/lessons/169198
class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        // 상하좌우 방향을 비교하여 최솟값 추가 -> 직각 좌표계 - 좌 하단 0
        // 가로 m 세로 n
        // 상하좌우
        // x = 0 x = m-1 대칭
        // y = 0 y = n-1 대칭

        // 단 좌 대칭일 때 y값이 같고 target이 start보다 앞에 있다면 target이 먼저 맞으므로 하면 안됨

        for (int i = 0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];

            int curlen, len = Integer.MAX_VALUE;

            // 좌
            if (!(startY == targetY && startX >= targetX)) {
                curlen = getDistance(startX, startY, targetX * (-1), targetY);
                len = Math.min(curlen, len);
            }

            // 우
            if (!(startY == targetY && startX <= targetX)) {
                curlen = getDistance(startX, startY, m + (m - targetX), targetY);
                len = Math.min(curlen, len);
            }

            // 상
            if (!(startX == targetX && startY <= targetY)) {
                curlen = getDistance(startX, startY, targetX, n + (n - targetY));
                len = Math.min(curlen, len);
            }

            // 하
            if (!(startX == targetX && startY >= targetY)) {
                curlen = getDistance(startX, startY, targetX, targetY * (-1));
                len = Math.min(curlen, len);
            }

            answer[i] = len;
        }

        return answer;
    }

    public int getDistance(int x1, int y1, int x2, int y2) {
        return (int) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
