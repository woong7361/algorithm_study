// https://school.programmers.co.kr/learn/courses/30/lessons/150365
// 12 start


class Solution {
    String[] directions = new String[]{"d", "l", "r", "u"};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        // n*m 격자 미로 x,y에서 출발 r,c로 이동

        // 격자 바깥으로 못나감
        // 이동거리 총 k 같은 격자 중복 방문 가능
        // 미로에서 탈출한 경로를 문자열로 나타났을때 사전순으로 가장 빠른 경로로 출발
        // l 왼쪽, r 오른쪽, u위쪽, d아래쪽 -> 사전순 d, l, r u

        // 미궁 가로, 세로 길이 <= 50
        // k <= 2500
        // 최고 경우의수 4^2500 -> 전탐색 절대 불가능

        // 문제 설계
        // 전탐색이 무조건 불가능 -> 가지치기 해야함
        // 어떻게? 현재위치 -> end까지의 거리를 계산 ex. remain = 5
        // k-remain이 짝수라면 가능 홀수라면 불가능


        String answer = dfs(n, m, x, y, r, c, k, new StringBuilder());
        if (answer == null) {
            answer = "impossible";
        }

        return answer;
    }

    private String dfs(int rowNum, int colNum, int currentRow, int currentCol, int endRow, int endCol, int k, StringBuilder load) {
        int remainLength = Math.abs(currentRow - endRow) + Math.abs(currentCol - endCol);
        // end까지의 거리가 < k 일때 종료
        if (remainLength > k) {
            return null;
        }
        // 남은 k - 남은 거리 가 홀수라면 종료
        if ((k - remainLength) % 2 == 1) {
            return null;
        }
        // k == 0 이라면
        if (k == 0) {
            return load.toString();
        }

        for (String direction : directions) {
            int nextRow = currentRow, nextCol = currentCol;
            if (direction.equals("d")) {
                nextRow += 1;
            } else if (direction.equals("l")) {
                nextCol -= 1;
            } else if (direction.equals("r")) {
                nextCol += 1;
            } else if (direction.equals("u")) {
                nextRow -= 1;
            }

            if (isInboard(rowNum, colNum, nextRow, nextCol)) {
                load.append(direction);
                String result = dfs(rowNum, colNum, nextRow, nextCol, endRow, endCol, k-1, load);
                load.deleteCharAt(load.length() - 1);

                if (result != null) {
                    return result;
                }
            }

        }

        return null;
    }

    private boolean isInboard(int rowNum, int colNum, int currentRow, int currentCol) {
        return currentRow >= 1 && currentRow <= rowNum && currentCol >= 1 && currentCol <= colNum;
    }
}
