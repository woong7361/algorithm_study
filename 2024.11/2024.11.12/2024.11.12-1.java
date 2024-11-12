// https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/777/

import java.util.*;

class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        HashSet<Integer> rowSet = new HashSet<>();
        HashSet<Integer> colSet = new HashSet<>();

        // 순회1 0인 row & column 찾기
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 0) {
                    rowSet.add(row);
                    colSet.add(col);
                }
            }
        }

        // 순회2 0 쓰기
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (rowSet.contains(row) || colSet.contains(col)) {
                    matrix[row][col] = 0;
                }
            }
        }

    }
}
