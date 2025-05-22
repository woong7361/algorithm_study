// https://school.programmers.co.kr/learn/courses/30/lessons/42842
// 29 start


import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};

        //  중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양
        // n*m 격자일때
        // 2n+2m-4 = brown
        // (m-2) * (n-2) = yellow
        // ... 연립 방적식

        // mn - 2n - 2m + 4 = yellow | 2n + 2m = brown+4
        // mn = yellow + brown

        // 가로의 길이가 더 길거나 같다.
        int total = brown + yellow;

        int row = 3;
        int column = -1;
        while (true) {
            if (total % row == 0) {
                column = total / row;

                if (2 * row + 2 * column - 4 == brown) {
                    break;
                }
            }

            row++;
        }



        return new int[] {column, row};
    }
}
