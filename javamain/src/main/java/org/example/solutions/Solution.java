package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/133502
// 50 start


import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        // 햄버거 가게 포장
        // 빵 야채 고기 빵 순서대로만 가능
        // stack 과 같이 무조건 그순서대로 있어야함

        // 빵 야 고 빵 들어오면 stack 에서 빼기
        // 빠졌을때 역순 확인 [빵 야 고] 까지 있나

        // 빵 = 1, 야채 = 2, 고기 = 3

        List<Integer> recipe = List.of(1, 2, 3, 1);
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> hamburger = new Stack<>();

        for (int i = 0; i < ingredient.length; i++) {
            if (ingredient[i] == recipe.get(hamburger.size())) {
                hamburger.push(ingredient[i]);
            } else {
                stack.push(ingredient[i]);
            }

            if (hamburger.size() == 4) {
                hamburger.clear();

                answer += 1;

                if (stack.peek() == 1) {

                }

            }
        }

        return answer;
    }
}