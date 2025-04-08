package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/77885
// 26 start


import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        // fx: x보다 크고 x와 비트가 1~2개 다른수중 제일 작은 수

        // f2 = 3   10   11
        // f7 = 11  111 -> 1011
        // fn 은?

        // 해결방법
        // 일단 x보다 커야한다. -> 2진수로 만들었을때 한개를 0 -> 1로 바꾸거나, 1개를 0->1 로 바꾸고 그 아랫 자리를 1->0으로 바꾼다.  (2가지)
        // 그중 제일 작은 수를 선택한다.

        // x를 2진수로 만든다. -> string
        // 1. x의 가장 작은 자릿수의 0에서 1을 추가할때 한가지 수를 list에 추가한다. (0->1 1개)

        // 2.   x에 padding 0 추가
        //      x뒤에 1이 있다면 x에서 가장 작은 자릿수의 0을 1로 변경 후, 그 아랫 자릿수의 1을 0으로 변경(0->1, 1->0 1개)
        // 010
        // 011
        // 01
        // 0

        for (int i = 0; i < numbers.length; i++) {
            answer[i] = functionX(numbers[i]);
        }

        return answer;
    }

    private long functionX(long number) {
        long result = -1;

        long index = 0;
        long temp = number;
        while (temp >= 0) {
            if (temp % 2 == 0L && index == 0) {
                result = Math.max(result, number + (long) Math.pow(2, index));
                break;
            } else if (temp % 2 == 0) {
                result = Math.max(result, number + (long) Math.pow(2, index) - (long) Math.pow(2, index - 1));
                break;
            }

            index += 1;
            temp = temp / 2L;
        }
        System.out.println("0->1 result = " + result);

        return result;
    }
}