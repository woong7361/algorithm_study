// https://school.programmers.co.kr/learn/courses/30/lessons/138476
// 50 start


import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        // 과수원에서 귤 수확
        // k골라 상자 하나 판매
        // 굴 크기별로 분류했을때 서로 다른 종류의 수를 최소화

        // ex. 1 3 2 5 4 5 2 3 & k=6
        // 1 4 를 제외하면 2 3 5로 총 3가지 서로다른 최소

        // 개수 순으로 MAP 생성

        HashMap<Integer, Integer> tangerineCountMap = new HashMap<Integer, Integer>();
        for (int tangerineSize : tangerine) {
            tangerineCountMap.put(
                    tangerineSize,
                    tangerineCountMap.getOrDefault(tangerineSize, 0) + 1
            );
        }

        // 정렬
        List<Integer> sortedKeyValues = tangerineCountMap.keySet()
                .stream()
                .sorted((t1, t2) -> tangerineCountMap.get(t2) - tangerineCountMap.get(t1))
                .collect(Collectors.toList());

        System.out.println("sortedKeyValues = " + sortedKeyValues.toString());

        int boxCount = 0;
        for (Integer sortedKeyValue : sortedKeyValues) {
            answer += 1;

            boxCount += tangerineCountMap.get(sortedKeyValue);
            if(boxCount > k) {
                break;
            }
        }

        return answer;
    }
}
