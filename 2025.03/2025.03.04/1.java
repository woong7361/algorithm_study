// https://school.programmers.co.kr/learn/courses/30/lessons/131130
// 00 start


import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        // 숫자 카드 더미
        // 1~100
        // 2이상 100이하 하나 정한다. 그 수보다 작거나 같은 숫자카드 준비한다.
        // 상자에 카드를 한장씩 넣고, 무작위 배열 & 1번 부터 순서대로 숫자 부여
        // 임의의 상자 선택하여 숫자 카드 확인, 확인한 카드에 적힌 번호에 해당하는 상자를 열어 카드 숫자 확인
        // 열어야 하는 상자가 이미 열려있을때까지 반복
        // 이렇게 연 상자가 1번 그룹
        // 1번 그룹 상자를 제외한 상자를 동일한 방식으로 진행 -> 2번 상자 그룹
        // 1번 상자그룹 수 * 2번 상자 그룹 수 = 점수
        // 얻을 수 있는 최고 점수는 얼마인가?
        // 단 1번에서 다 열었으면 0점


        // index    1 2 3 4 5 6 7 8
        // ex.      8 6 3 7 2 5 1 4
        // 8 선택 -> 4 -> 7 -> 1 끝
        // 6 선택 -> 5 -> 2 -> 끝
        // 3 선택 끝
        // 7 선택 -> 1 -> 8 -> 4

        // 결론 loop 존재

        // 모든 loop 확인 후 max loop 2개 뽑아서 곱 반환

        // 해결 방법
        boolean[] visit = new boolean[cards.length + 1];
        Arrays.fill(visit, false);
        PriorityQueue<Integer> pq = new PriorityQueue<>((t1, t2) -> t2 - t1);

        // cards 순회하며 loop 확인 , visit 이라면 지나친다.
        for (int i = 0; i < cards.length; i++) {
            if (visit[i]) {
                continue;
            }

            // visit이 아니라면 선택한 카드의 번호를 따라 다음 카드로 이동하여 loop의 수를 센다. visit도 한다.
            int loopCount = countLoop(cards, visit, i);
            pq.add(loopCount);
        }

        // 끝난다면 loop의 최대 수 2개를 뽑아 곱한다.
        if (pq.size() < 2) {
            return 0;
        } else {
            return pq.poll() * pq.poll();
        }
    }

    private int countLoop(int[] cards, boolean[] visit, int i) {
        if (visit[i]) {
            return 0;
        }

        visit[i] = true;

        int nextIndex = cards[i]-1;
        return countLoop(cards, visit, nextIndex) + 1;
    }
}
