// https://school.programmers.co.kr/learn/courses/30/lessons/42839
// 43 start


import java.util.*;

class Solution {
    public int solution(String numbers) {
        // 소수찾기
        // 흩어진 종이조각 모아 소수 만들기 -> 몇개?
        // 종이조각 <= 7
        // ex. 17 -> 7, 17, 71

        // 모든 조합 찾기
        // 길이가 1부터 numbers.legnth 까지

        HashSet<Integer> subSets = new HashSet<>();
        for (int i = 1; i < numbers.length()+1; i++) {
            boolean[] visit = new boolean[numbers.length()];
            Arrays.fill(visit, false);

            subSets.addAll(find(numbers.toCharArray(), "", visit, i));
        }

        return (int) subSets.stream()
                .filter(it -> isPrimeNumber(it))
                .count();

    }

    private Set<Integer> find(char[] numbers, String current, boolean[] visit, int maxLength) {
        if (current.length() == maxLength) {
            return Set.of(Integer.parseInt(current));
        }

        HashSet<Integer> result = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            if (! visit[i]) {
                visit[i] = true;
                result.addAll(find(numbers, current + numbers[i], visit, maxLength));
                visit[i] = false;
            }
        }

        return result;
    }

    private boolean isPrimeNumber(int number) {
        if (number == 0 || number == 1) {
            return false;
        }

        for (int i = 2; i < (number / 2)+1 ; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
