// https://school.programmers.co.kr/learn/courses/30/lessons/258709
// 49 start


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    private List<List<Integer>> t = new ArrayList<>();

    public int[] solution(int[][] dice) {
        List<Integer> answer = null;
        int maxWin = -1;
        // A와 B가 n개의 주사위를 가지고 승부, 각 주사위는 1~n의 번호를 가지고 있다, 주사위에 쓰인 수의 구성은 모두 다르다. 6면 주사위다.
        // 각각 가져간 주사위를 모두 굴린 후 나온 수들을 모두 합해 점수를 계산한다.
        // A가 자신이 승리할 확률이 높도록 주사위를 가져가려한다.
        // 주사위	 구성
        // #1	    [1, 2, 3, 4, 5, 6]
        // #2	    [3, 3, 3, 3, 4, 4]
        // #3	    [1, 3, 3, 4, 4, 4]
        // #4	    [1, 1, 4, 4, 5, 5]

        // 각 주사위를 가져가 승 무 패를 계산 -> 승이 제일 많은 경우의 수는 어떤 주사위를 가져갈때인가?

        // 조건
        // n <= 10, n은 2의 배수, 1 <= dice[i] <= 100

        // 문제 설계
        // 가장 핵심 -> 총 경우의 수는 같다. -> 특정 조합의 승수가 제일 높을 때를 찾자
        // dice가 10개 밖에 되지 않으니 전부다 해보기 nCx 조합 가져가기 -> 결과값 쌓기 & 반대편 결과값도 쌓기
        // 두개를 비교해가며 총 10C5 = 28*9 = 252
        // 주사위 5를 모두 굴리는 경우의 수 = 6^5 = 7776

        // 되려나?
        diceDivide(dice.length, new ArrayList<>());
        System.out.println("t = " + t);

        for (List<Integer> aDice : t) {
            List<Integer> bDice = IntStream.range(0, dice.length)
                    .filter(i -> !aDice.contains(i))
                    .mapToObj(i -> Integer.valueOf(i))
                    .collect(Collectors.toList());

            int winSum = checkWinRate(dice, aDice, bDice);

            if (winSum > maxWin) {
                answer = aDice;
                maxWin = winSum;
            }
        }


        return answer.stream()
                .map(integer -> integer + 1)
                .mapToInt(integer -> integer)
                .toArray();
    }

    private int checkWinRate(int[][] dice, List<Integer> aDice, List<Integer> bDice) {
        int winSum = 0;

        PriorityQueue<Integer> aResult = new PriorityQueue<>();
        PriorityQueue<Integer> bResult = new PriorityQueue<>();

        getResult(dice, aDice, new ArrayList<>(), aResult);
        getResult(dice, bDice, new ArrayList<>(), bResult);

        int winCount = 0;
        while (!aResult.isEmpty()) {
            Integer poll = aResult.poll();

            while (!bResult.isEmpty() && bResult.peek() < poll) {
                bResult.poll();
                winCount += 1;
            }

            winSum += winCount;
        }

        return winSum;
    }

    private void getResult(int[][] dice, List<Integer> aDice, List<Integer> current, PriorityQueue<Integer> result) {
        if (current.size() == aDice.size()) {
            result.add(current.stream().mapToInt(integer -> integer).sum());
            return;
        }
        // current 의 수에 맞는 dice 가져오기
        int[] targetDice = dice[aDice.get(current.size())];
        for (int number : targetDice) {
            current.add(number);
            getResult(dice, aDice, current, result);
            current.remove(current.size() - 1);
        }

        // dice에서 숫자 뽑기
    }

    private void diceDivide(int length, List<Integer> current) {
        if (current.size() == length / 2) {
            t.add(List.copyOf(current));
            return;
        }

        for (int i = current.isEmpty() ? 0 : current.get(current.size() - 1) + 1; i < length; i++) {
            current.add(i);
            diceDivide(length, current);
            current.remove(current.size() - 1);
        }

    }
}
