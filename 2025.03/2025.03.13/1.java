
// https://school.programmers.co.kr/learn/courses/30/lessons/87390
// 20 start


import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        // n*n 배열
        // arr[i][j] = max[i][j]
        // left 부터 right까지
        // arr[left/n][left%n]  ex 0 arr[0][0] n arr[1][0]
        int[] answer = new int[(int) (right - left + 1L)];
        int index = 0;
        for (long i = left; i <= right; i++) {
            long row = i / n, col = i % n;

            answer[index++] = (int) Math.max(row, col) + 1;
        }

        return answer;
    }
}
