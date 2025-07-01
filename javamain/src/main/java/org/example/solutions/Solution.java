package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/12905
// 12 start


import java.util.*;

class Solution
{
    public int solution(int [][]board)
    {
        int answer = 1;

        // 1과 0으로 채워진 표
        // 표에서 1로 이루어진 가장 큰 정사각형을 찾아 넓이를 return 하는 함수를 만들어라

        // 문제 설계
        // i,j 에서 사각형의 max length를 조사한다.

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {

                // row,col == '1'일 때
                if (board[row][col] == 1) {
                    // while length를 늘려가며 정사각형이 되는 max_legnth를 확인
                    answer = Math.max(answer, findMaxSquareLength(board, row, col));
                }

            }
        }

        return answer*answer;
    }

    private int findMaxSquareLength(int[][] board, int row, int col) {
        int length = 1;

        while (row+length < board.length && col+length < board[0].length && checkSquare(board, row, col, length)) {
            length++;
        }

        return length;
    }

    private boolean checkSquare(int[][] board, int row, int col, int length) {
        for (int i = 0; i < length+1; i++) {
            if (board[row + length][col + i] == 0 || board[row + i][col + length] == 0) {
                return false;
            }
        }

        return true;
    }
}