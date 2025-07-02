// https://school.programmers.co.kr/learn/courses/30/lessons/12905
// 00 start


import java.util.*;

class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        // 1과 0으로 이루어진 보드
        // 1로 이루어진 가장 큰 정사각형의 넓이를 반환하라

        // 문제 설계
        // dp로 해결
        // dpBoard 좌 상 좌상단, 확인 후 가장 작은값 +1 -> min()+1
        // dpboard가 아닌 생보드 활용 가능할듯

        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    board[i][j] = Math.min(board[i - 1][j - 1], Math.min(board[i - 1][j], board[i][j - 1])) + 1;
                    answer = Math.max(board[i][j], answer);
                }
            }
        }

        if (board.length == 1 || board[0].length == 1) {
            answer = Arrays.stream(board)
                    .flatMapToInt(ints -> Arrays.stream(ints))
                    .max()
                    .getAsInt();

        }

        return answer*answer;
    }
}
