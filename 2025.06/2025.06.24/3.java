// https://school.programmers.co.kr/learn/courses/30/lessons/12946
// 26 start


import java.util.*;

class Solution {
    List<int[]> result = new ArrayList<>();

    public int[][] solution(int n) {

        // 하노이탑
        // A -> C로 N판을 옮기고싶다.
        // A -> B로 N-1 ... 1 판이 옮겨져야함
        // A -> C로 N판을 옮긴다.
        // B -> C로 N-1 판을 옮긴다.

        hanoiCount(n, 1, 3, 2);

        int[][] array = result.stream()
                .toArray((p) -> new int[p][]);

        return array;
    }

    public void hanoiCount(int n, int start, int to, int via) {
        if (n == 1) {
            move(start, to);
            return;
        }

        hanoiCount(n - 1, start, via, to);

        move(start, to);

        hanoiCount(n - 1, via, to, start);
    }

    private void move(int start, int to) {
        result.add(new int[]{start, to});
    }
}
