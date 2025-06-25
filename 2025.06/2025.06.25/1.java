// https://school.programmers.co.kr/learn/courses/30/lessons/12936
// 26 start


import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < n+1; i++) {
            numbers.add(i);
        }

        k = k - 1;
        while (!numbers.isEmpty()) {
            if (numbers.size() == 1) {
                answer[n - numbers.size()] = numbers.remove((int) k);
            } else {
                long divNum = factorial(numbers.size() - 1);
                answer[n - numbers.size()] = numbers.remove((int) (k / divNum));
                k = k % divNum;
            }
        }

        return answer;
    }

    private long factorial(int n) {
        long result = 1;

        for (int i = 1; i <= n; i++) {
            result *= i;
        }

        return result;
    }
}
