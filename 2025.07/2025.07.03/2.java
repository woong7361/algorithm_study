package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/258707
// 55 start


import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int turn = 0;
        // 1~n 사이의 수가 적힌 카드뭉치, 동전 coin개
        // 카드 3/n장을 뽑아 모두 가진다. (카드는 6의 배수이다.)
        // 카드와 교환 가능한 동전 coin개를 가지고 있다.
        // 라운드 시작시 카드 2장 뽑기 -> 카드뭉치에 카드가 없다면 게임 끝
        // 뽑은 카드는 카드 한 장당 동전 하나를 소모해 가지거나, 동전을 소모하지 않고 버린다.
        // 카드에 적힌 수의 합이 n+1이 되도록 카드 2장을 내고 다음 라운드 진행, 내지 못한다면 게임을 종료
        // 게임에서 도달 가능한 최대 라운드의 수를 return 하여라

        // 문제 설계
        // cards의 배열은 이미 정해져있다. & 각 카드는 하나의 n+1이 되는 짝을 가지고 있다.
        // n <= 1000 이니 순서대로 진행한다. ->

        // 새로 뽑은 카드가
        //      현재 손패의 n+1 짝이다 -> 무조건 뽑아야함 (coin 1소모)
        //      새로 뽑은 2개의 카드가 n+1 짝이다. -> 생각 해야함 (coin 2소모)
        //      미래에 뽑을 카드의 n+1 짝이다 -> 생각 해야함 coin (2소모)

        // 결론 -> 1 coin 소모로 가능한 것부터 먼저 소모 -> 불가능? 한지점에 도달? -> 2 coin 소모 후 다시 1coin 소모로 도전 ...

        // card couple array -> 짝궁 index 적기 ->
        //      hand에 존재 1
        //      앞코인에는 적지 않음 (-2) 짝의 뒷코인에 2

        // 해결 방법
        // card couple array 설계대로 생성
        // hand[]에 가능한 couple 숫자 check -> h개
        // index n/3 부터 +2 진행하면서 카드 뽑기 & 코인 소모
        // 카드 뽑을 때 h-1
        // 뽑은 카드가 -1 -> h+1 (coin -1)
        // 뽑은 카드가 1 -> t+1 (t는 현재 coin 2개 소모로 가능한 경우의 수)
        // if(h < 0) -> t 소모(coin -2)
        // but. coin < 2 이면 끝...

        ArrayList<Integer> hands = new ArrayList<>();
        for (int i = 0; i < cards.length / 3; i++) {
            hands.add(cards[i]);
        }
        int availableHand = getHandSumCount(hands, cards.length+1);

        HashSet<Integer> handSet = new HashSet<>(hands);
        HashSet<Integer> deckSet = new HashSet<>();
        int[] cardCoupleArray = new int[cards.length];
        for (int i = cards.length/3; i < cards.length; i++) {
            int findNum = cards.length + 1 - cards[i];

            if (handSet.contains(findNum)) {
                cardCoupleArray[i] = 1;
            } else if (deckSet.contains(findNum)) {
                cardCoupleArray[i] = 2;
            } else {
                cardCoupleArray[i] = -1;
                deckSet.add(cards[i]);
            }
        }

        int twoCoinAvailable = 0;
        for (int i = cards.length / 3; i < cards.length+2; i += 2) {
            // 마지막 이라면 한번더 추가
            if (i == cards.length) {
                turn++;
                break;
            }

            // 핸드 소모
            availableHand--;
            turn++;

            // 코인 1개 이상 있고, 핸드에 있는 거로 영입 가능하면
            for (int j = i; j < i+2; j++) {
                if (coin > 0 && cardCoupleArray[j] == 1) {
                    availableHand++;
                    coin--;
                }

                if (cardCoupleArray[j] == 2) {
                    twoCoinAvailable++;
                }
            }

            if (availableHand < 0) {
                if (twoCoinAvailable > 0 && coin >= 2) {
                    availableHand++;
                    coin -= 2;
                } else {
                    break;
                }
            }
        }

        return Math.min(turn, cards.length / 3 + 1);
    }

    private int getHandSumCount(ArrayList<Integer> hands, int sum) {
        int count = 0;

        Collections.sort(hands);
        int start = 0, end = hands.size() - 1;

        while (start < end) {
            if (hands.get(start) + hands.get(end) > sum) {
                end--;
            } else if (hands.get(start) + hands.get(end) < sum) {
                start++;
            } else {
                count++;
                end--;
                start++;
            }
        }

        return count;
    }
}



