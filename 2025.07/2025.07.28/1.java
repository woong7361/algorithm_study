// https://school.programmers.co.kr/learn/courses/30/lessons/42584
// 22 start


import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Arrays.fill(answer, 0);
        // 가격이 떨어지지 않은 기간은 몇초인지 return 하시오
        // 나보다 크거나 같다? 진행
        // 나보다 작다? 그러면 count -> 그 전의 가격도 check

        // fori 문
        // stack에 price 추가
        // 현재 초는 i로 확인

        Stack<List<Integer>> priceStack = new Stack<>();
        for (int i = 0; i < prices.length; i++) {
            if (i == 0) {
                priceStack.push(List.of(i, prices[i]));
                continue;
            }

            while (!priceStack.isEmpty() && priceStack.peek().get(1) > prices[i]) {
                List<Integer> downPrice = priceStack.pop();
                answer[downPrice.get(0)] = i - downPrice.get(0);
            }

            priceStack.push(List.of(i, prices[i]));
//            System.out.println("priceStack.toString() = " + priceStack.toString());
//            System.out.println("answer = " + Arrays.toString(answer));
        }

        while (!priceStack.isEmpty()) {
            List<Integer> downPrice = priceStack.pop();
            answer[downPrice.get(0)] = prices.length-1 - downPrice.get(0);
        }
        answer[prices.length - 1] = 0;


        return answer;
    }
}
