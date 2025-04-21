// https://school.programmers.co.kr/learn/courses/30/lessons/68936
// 17 start


import java.util.*;

class Solution {
    int[] answer = new int[2];

    public int[] solution(int[][] arr) {
        // start_row, start_col, end_row, end_col
        // 4분할
        // 모두 같은수라면? 하나로 통일 and result +1 return
        // 다른수라면? depth +1
        Arrays.fill(answer, 0);
        divde(arr, 0, 0, arr.length);

        return answer;
    }

    private void divde(int[][] arr, int startRow, int startCol, int length) {
        int value = arr[startRow][startCol];
        // 가장 작으면
        if (length == 1) {
            answer[value] += 1;
            return;
        }

        // 전부 같은지 확인
        if (validateSameValue(arr, startRow, startCol, length)) {
            answer[value] += 1;
        } else {
            divde(arr, startRow, startCol, length / 2);
            divde(arr, startRow + length / 2, startCol, length / 2);
            divde(arr, startRow, startCol + length / 2, length / 2);
            divde(arr, startRow + length / 2, startCol + length / 2, length / 2);
        }
    }

    private boolean validateSameValue(int[][]arr, int startRow, int startCol, int length) {
        for (int row = startRow; row < startRow + length; row++) {
            for (int col = startCol; col < startCol + length; col++) {
                if (arr[row][col] != arr[startRow][startCol]) {
                    return false;
                }
            }
        }

        return true;
    }

}
