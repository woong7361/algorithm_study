// https://school.programmers.co.kr/learn/courses/30/lessons/92342
// 01 start


import java.util.*;

class Solution {
    public int[] solution(int n, int[] info) {
        int[] answer = {};
        // 양궁대회
        // 라이언과 어피치
        // 어피치 n발 후 라이언 n발
        // 각 k점에 맞춘 발수가 많을때 k점을 가져간다. 동률이라면 어피치가 득점한다.

        // 현재상황은 어피치가 n발을 다 쏜 상태
        // 라이언이 가장 큰 점수차이로 이기기 위해서는 어떤 과녁에 점수를 맞춰야할까?

        // 생각을 해보자
        // 점수의 기댓값에 대해서 ...
        // 내가 상대방의 점수를 뻇어오기 가능 -> 2배의 점수를 얻는다고 생각하면 된다.
        // 그냥 득점이다. -> k점의 점수만을 가져온다.

        // if 어피치 9점에 2발 있다.
        // 라이언 9점 얻어오려면 9점에 3발 해야함 -> 3발에 18점 -> 1발당 기댓값 6점
        // 6점 1발 -> 1발당 기댓값 6
        // 5점 3발

        // 라이언 8점 얻어오려면 8점에 1발 해야함 -> 1발당 기댓값 8점

        // 결론 1발당 기댓값이 가장 높은 곳에 화살을 쏴야한다. && 화살을 쏘았을때 득점 가능이어야한다.
        // 얻은 점수를 어피치 점수와 비교하여 우승 가능한지 확인

        // 해결 방법
        // 어피치 점수 집계
        int peachRate = 0;
        for (int i = 0; i < info.length; i++) {
            if (info[i] > 0) {
                peachRate += (10 - i);
            }
        }

        // 라이언이 쏘았을때 기준 화살당 기댓값 계산 // 1발당 기댓값이 같다면 발수가 적은것부터? 불가능 경우의수 세어야함

        // 득점 가능하다면 어피치 점수와 라이언 점수 수정
        // 만약 모두 득점 불가능해지고 화살이 남는다면 0점에 몰아두기
        // 결과 비교하여 결론

        boolean[] visit = new boolean[11];
        Arrays.fill(visit, false);

        int[] current = new int[11];
        Arrays.fill(current, 0);

        Result maxResult = new Result(null, -1);
        for (int i = 9; i >= 0; i--) {
            Result temp = dfs(i, info, visit, current, n, peachRate, 0);
            if (temp.getRate() > maxResult.getRate()) {
                maxResult = temp;
            }
        }

        if (maxResult.getRate() == -1) {
            return new int[]{-1};
        } else {
            return maxResult.getAnswer();
        }
    }

    private Result dfs(int i, int[] info, boolean[] visit, int[] current, int arrow, int pr, int rr) {
        // 화살이 부족할때 -> 점수 집계
        if (info[i] >= arrow) {
            if (pr >= rr) {
                return new Result(null, -1);
            } else {
                int[] ints = Arrays.copyOf(current, 11);
                ints[10] = arrow;
                return new Result(ints, rr-pr);
            }
        }

        current[i] = info[i] + 1;
        visit[i] = true;

        if (info[i] > 0) {
            pr = pr - (10 - i);
        }

        Result maxResult = new Result(null, -1);
        for (int j = 9; j >= 0; j--) {
            if (visit[j]) {
                continue;
            }

            Result temp = dfs(j, info, visit, current, arrow - (info[i] + 1), pr, rr + (10 - i));
            if (temp.getRate() > maxResult.getRate()) {
                maxResult = temp;
            }
        }

        current[i] = 0;
        visit[i] = false;

        return maxResult;
    }

    class Result{
        public Result(int[] answer, int rate) {
            this.answer = answer;
            this.rate = rate;
        }

        private int[] answer;
        private int rate;

        public int[] getAnswer() {
            return answer;
        }

        public int getRate() {
            return rate;
        }
    }
}


