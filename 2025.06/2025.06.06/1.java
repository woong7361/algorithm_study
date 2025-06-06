// https://school.programmers.co.kr/learn/courses/30/lessons/42578
// 37 start


import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer;
        // 의상을 똑같은걸 입을 수 없음 하나라도 바꿔입어야함
        // 종류별로 최대 하나씩 가능
        // 하루에 최소 하나씩은 입음
        // 서로 다른 옷의 조합의 수는?

        // 종류 .. n개
        // 종류당 각 개수 Xn개
        // 조합의 수 = 1개만 입을때 + 2개 입을때 + 3개 입을때 + n개 입을때
        // 1개만 입을때 Xn의 합
        // 2개만 입을때 Xi * Xy
        // ...

        // 흠... 각 종류당 개수에 +1 (안입는 경우의 수)
        // 뺴야할 경우의 수가 모두 안입을때 -> 1가지
        // 모든 조합의 수 = (X1+1) * (X2+1) * ... * (Xn+1) - 1

        HashMap<String, Integer> clothTypeCountMap = new HashMap<>();
        for (String[] clothe : clothes) {
            String type = clothe[1];

            clothTypeCountMap.put(type,
                    clothTypeCountMap.getOrDefault(type, 0) + 1);
        }

        Collection<Integer> values = clothTypeCountMap.values();
        answer = values.stream()
                .map(v -> v + 1)
                .reduce(1, (a, b) -> a * b);

        return answer-1;
    }
}
