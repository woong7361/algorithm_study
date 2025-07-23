// https://school.programmers.co.kr/learn/courses/30/lessons/131129
// 53 start


import java.util.*;

class Solution {
    public int[] solution(int target) {
        // 카운트 다운
        // 점수를 깍아서 0으로 만드는 게임, 남은 점수보다 큰 점수를 득점하면 버스트로 실격된다.
        // 1-20의 점수 & 싱글, 더블, 트리플 & 불, 아우터 불(구분없이 50점)
        // 두 선수가 같은 라운드에 0점을 만들었을 때는 싱글 or 불을 더 많이 던진 선수가 우승 & 그것도 아니면 선공이 승리

//        문제 설계
        // 싱글, 불을 많이 던져야함 50 or 1~20점  (0점을 가장 빨리 만들면서)

        // 1 <= target <= 100000

        // ex. 150 50 50 50   60 60 30

        // 역순으로 가는게 편할듯?  상향식 dp로
        // 0 -> 0
        // 1 ~ 20 -> 1
        // 21 = 20 + 1 or ...

        // 순서는 불 시도(50) & single 시도 (1~20) & (double, triple 시도 22, ..., 60)

        Set<Integer> singleBull = new HashSet<>();
        Set<Integer> doubleTriple = new HashSet<>();
        for (int i = 1; i <= 20; i++) {
            singleBull.add(i);

            doubleTriple.add(i * 2);
            doubleTriple.add(i * 3);
        }
        singleBull.add(50);
        doubleTriple.removeAll(singleBull);

        int[] dp = new int[target+1];
        int[] sbCount = new int[target + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
//        sbCount[0] = 0;
        for (int i = 1; i <= target; i++) {
            // dp 값을 가장 최솟값으로 갱신
            // 그중에서 sbCount는 가장 최대값으로 갱신

            for (Integer t : singleBull) {
                if (t > i) continue;

                if (dp[i - t] + 1 < dp[i]) {
                    dp[i] = dp[i - t] + 1;
                    sbCount[i] = sbCount[i - t] + 1;
                } else if (dp[i - t] + 1 == dp[i]) {
                    sbCount[i] = Math.max(sbCount[i], sbCount[i - t] + 1);

                }
            }

            for (Integer t : doubleTriple) {
                if (t > i) continue;

                if (dp[i - t] + 1 < dp[i]) {
                    dp[i] = dp[i - t] + 1;
                    sbCount[i] = sbCount[i - t];
                } else if (dp[i - t] + 1 == dp[i]) {
                    sbCount[i] = Math.max(sbCount[i], sbCount[i - t]);
                }
            }

        }

        return new int[] {dp[target], sbCount[target]};
    }
}
