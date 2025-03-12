// https://school.programmers.co.kr/learn/courses/30/lessons/87946
// 00 start


import java.util.*;
        import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        // 임의의 정수 n -> k 진수로 변환
        // 변환된 수 안에 아래 조건에 맞는 소수가 몇개인지 알아보기
        // 0P0처럼 소수 양쪽에 0 이있다.
        // P0 or 0P 처럼 왼쪽 또는 오른쪽에 0이 있고 아무것도 없는 경우
        // P 처럼 소수 양쪽에 아무것도 없는 경우
        // 단 P는 각 자릿수에 0을 포함하지 않는 소수 ex. 101 안된다.

        // 해결법
        // n을 k진수로 바꾼다. -> String builder
        // n%k 왼쪽, n = n/k, if n < k -> n 왼쪽에 붙이기

        // ex. 4/2 = 2 4%2 = 0 -> 나머지 왼쪽에 붙이기

        StringBuilder builder = new StringBuilder();
        while (true) {
            if (n < k) {
                builder.append(n);
                break;
            }

            builder.append(n % k);
            n = n / k;
        }

        String kNumber = builder.reverse().toString();

        // 0이 아니면 무조건 추가
        // 0이고 비어있지 않다면 마무리
        // 마지막에 0이 아니면 추가하고 마무리 해주기
        ArrayList<Long> candidate = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < kNumber.length(); i++) {
            if (i == kNumber.length() - 1 && kNumber.charAt(i) != '0') {
                temp.append(kNumber.charAt(i));
                candidate.add(Long.parseLong(temp.toString()));
                temp = new StringBuilder();
            }

            if (kNumber.charAt(i) != '0') {
                temp.append(kNumber.charAt(i));
            }

            if (kNumber.charAt(i) == '0' && temp.length() != 0) {
                candidate.add(Long.parseLong(temp.toString()));
                temp = new StringBuilder();
            }
        }

        long count = candidate.stream()
                .filter(c -> isPrime(c))
                .count();

        return (int) count;
    }

    public boolean isPrime(long num) {
        if (num < 2) return false;
        return IntStream.rangeClosed(2, (int)Math.sqrt(num))
                .noneMatch(i -> num % i == 0);
    }
}
