// https://school.programmers.co.kr/learn/courses/30/lessons/160585


class Solution {
    public int solution(String[] board) {
        //틱택토
        // O 선공 X 후공

        // 경우의 수
        // 1. 경기가 끝났는데도 계속 진행한 경우
            // XXX or OOO 가 두개 이상 존재하는 경우
        // 2. 실수로 O 혹은 X를 두번 둔 경우
            // 원래 O = X+1 or O = X 이 경우가 아닌 경우는 모두 실수
        int[] threeLineResult = getThreeLine(board);
        int[] stoneCountResult = getStoneCount(board);

        int xStone = stoneCountResult[0];
        int xThreeLine = threeLineResult[0];
        int oStone = stoneCountResult[1];
        int oThreeLine = threeLineResult[1];

        if (oStone < xStone || oStone > xStone + 1) {
            return 0;
        }
//        if (xThreeLine + oThreeLine > 1) {
//            return 0;
//        }
        // O X -> O가 오면 안됨
        if (xThreeLine > 0 && oStone != xStone) {
            return 0;
        }
        // O X O -> X가 오면 안됨
        if (oThreeLine > 0 && oStone != xStone+1) {
            return 0;
        }

        return 1;
    }

    public int[] getThreeLine(String[] board) {
        int xResult = 0;
        int oResult = 0;
        // 가로
            // i...이 같을때
        // 세로
            // ...j가 같을때
        // 대각선
            // i j가 모두 같을때 and i 2-j가 같을때

        for (int i = 0; i < 3; i++) {
            char rowChar = board[i].charAt(0);
            char columnChar = board[0].charAt(i);

            for (int j = 0; j < 3; j++) {
                if (rowChar == '.') break;
                if (board[i].charAt(j) != rowChar) break;

                if (j == 2 && rowChar == 'X') xResult += 1;
                if (j == 2 && rowChar == 'O') oResult += 1;
            }

            for (int j = 0; j < 3; j++) {
                if (columnChar == '.') break;
                if (board[j].charAt(i) != columnChar) break;

                if (j == 2 && columnChar == 'X') xResult += 1;
                if (j == 2 && columnChar == 'O') oResult += 1;
            }
        }

        char diagonalChar = board[0].charAt(0);
        for (int i = 0; i < 3; i++) {
            if (diagonalChar == '.') break;
            if (diagonalChar != board[i].charAt(i)) break;

            if (i == 2 && diagonalChar == 'X') xResult += 1;
            if (i == 2 && diagonalChar == 'O') oResult += 1;
        }

        char reverseDiagonalChar = board[0].charAt(2);
        for (int i = 0; i < 3; i++) {
            if (reverseDiagonalChar == '.') break;
            if (reverseDiagonalChar != board[i].charAt(2-i)) break;

            if (i == 2 && reverseDiagonalChar == 'X') xResult += 1;
            if (i == 2 && reverseDiagonalChar == 'O') oResult += 1;
        }

        return new int[]{xResult, oResult};
    }

    public int[] getStoneCount(String[] board) {
        int oCount = 0;
        int xCount = 0;

        for (int i = 0; i <3; i++) {
            for (int j = 0; j < 3; j++) {
                char stone = board[i].charAt(j);

                if (stone == 'X') xCount += 1;
                if (stone == 'O') oCount += 1;
            }
        }

        return new int[]{xCount, oCount};
    }
}
