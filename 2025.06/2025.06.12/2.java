// https://school.programmers.co.kr/learn/courses/30/lessons/17679
// 12 start


import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        // 같은 모양의 카카오 프렌즈 블록이 2*2 형태로 붙어있는 경우 사라지면서 점수를 얻는 방식
        // 지워지는 조건이 여러개 있다면 한번에 사라진다.
        // 블록이 지워진 후에 위에 있는 블록이 아래로 떨어져 빈 공간을 채우게 된다.
        // 반복된다.

        // 해결법
        // 배열로 board를 만들면 빈 공간을 채우고 옮겨야되는 공수가 존재 -> list로 만들면 편할듯
        // 중간에 접근하는 비용이 있지만 board의 높이 폭 2 <= m,n <= 30 이므로 괜찮을듯
        // 각 세로줄을 arrayList로 작성
        char[][] autoBoard = new char[m][n];

//        m개 짜리가 n개 존재
//        n이 가로
//        m이 세로

        for (int row = 0; row < m; row++) {
            String line = board[row];

            for (int col = 0; col < n; col++) {
                autoBoard[row][col] = line.charAt(col);
            }
        }

        // 같은 2*2 블록이 없을때까지 돌리기
        // 있다면 해당 블록 삭제 & 빈 공간 추가 '0'
        while (true) {
            int result = processBoard(autoBoard, m, n);
            answer += result;

            if (result <= 0) {
                break;
            }
        }


        return answer;
    }

    private int processBoard(char[][] board, int m, int n) {
        Set<String> deleteList = new HashSet<>();

        for (int row = 0; row < m-1; row++) {
            for (int col = 0; col < n-1; col++) {
                // row,col) row,col+1), row+1,col), row+1,col+1) 확인 -> row-1 ~ col-1까지 해야겠다
                char t = board[row][col];
                if (t != '0' && t == board[row + 1][col] && t == board[row][col + 1] && t == board[row + 1][col + 1]) {
                    deleteList.add(row + "," + col);
                    deleteList.add((row+1) + "," + col);
                    deleteList.add(row + "," + (col+1));
                    deleteList.add((row+1) + "," + (col+1));
                }
            }
        }

        // remove
        for (String position : deleteList) {
            String[] split = position.split(",");

            board[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = '0';
        }

        // down
        for (int col = 0; col < n; col++) {
            for (int row = m-1; row > 0; row--) {
                if (board[row][col] == '0') {

                    for (int t = row-1; t >= 0; t--) {
                        if (board[t][col] != '0') {
                            char temp = board[t][col];
                            board[t][col] = '0';
                            board[row][col] = temp;
                            break;
                        }
                    }
                }
            }
        }
        return deleteList.size();
    }
}
