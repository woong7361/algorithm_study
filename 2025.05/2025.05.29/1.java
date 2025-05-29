
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(int[] numbers) {
        // 0 또는 양의 정수가 주어졌을때, 정수를 이어 붙여 만들 수 있는 가장 큰수를 알아내 주세요
        // ex. 6 10 2 -> 6210 가장 큰 수

        // sorting

        // 자리 비교 ...
        // 이어붙이는 수 비교이니
        // 둘을 이어붙여서 수 비교
        // 둘중 큰수가 앞으로

        // 0 만있다면 0으로 return

        List<String> collect = Arrays.stream(numbers)
                .mapToObj(it -> String.valueOf(it))
                .sorted((s, t1) ->
                                -Long.compare(Long.valueOf(s + t1), Long.valueOf(t1 + s))
                )
                .collect(Collectors.toList());

        if (new HashSet<>(collect).size() == 1 && new HashSet<>(collect).contains("0")) {
            return "0";
        }

        return String.join("", collect);
    }
}
