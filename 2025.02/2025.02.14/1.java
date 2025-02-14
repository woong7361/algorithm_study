// https://school.programmers.co.kr/learn/courses/30/lessons/159994

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";

        // 카드 뭉치 2개
        // 원하는 순서의 단어 배열 만들기
        // 카드뭉치에서 순서대로 1개씩 사용
        // 재사용 불가
        // 카드 반드시 사용해야함
        // 기존의 카드뭉치의 순서를 바꿀 수 없음

        // dfs 풀이
        boolean b = cardSelect(cards1, 0, cards2, 0, goal, 0);

        if (b) {
            return "YES";
        } else {
            return "NO";
        }
    }


    private boolean cardSelect(String[] cards1, int index1,
                               String[] cards2, int index2,
                               String[] goal, int goalIndex) {
        if (goalIndex == goal.length) {
            return true;
        }

        boolean result = false;
        if (index1 < cards1.length && goal[goalIndex].equals(cards1[index1])) {
             result = result || cardSelect(cards1, index1 + 1, cards2, index2, goal, goalIndex + 1);
        }
        if (index2 < cards2.length && goal[goalIndex].equals(cards2[index2])) {
            result = result || cardSelect(cards1, index1, cards2, index2 + 1, goal, goalIndex + 1);
        }

        return result;
    }
}
