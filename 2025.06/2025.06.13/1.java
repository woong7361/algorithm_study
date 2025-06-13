// https://school.programmers.co.kr/learn/courses/30/lessons/17677
// 29 start


import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        //  비슷한 뉴스 많다.
        // 자카드 유사도
        // 두 집합 A, B 사이의 자카드 유사도는 두 집합의 교집합의 크기를 두 집합의 합 집합의 크기로 나눈값
        // 모두 공집합일 경우 1로 정의한다.
        // 원소의 중복을 허용하는 다중집합의 경우에도 적용할 수 있다.
        // ex. 다중집합 A = {1, 1, 2, 2, 3}, 다중집합 B = {1, 2, 2, 4, 5}라고 하면, 교집합 A ∩ B = {1, 2, 2}, 합집합 A ∪ B = {1, 1, 2, 2, 3, 4, 5}가 되므로, 자카드 유사도 J(A, B) = 3/7, 약 0.42가 된다.

        // 2 <= str1, str2 <= 1000
        // 입력으로 들어온 두 문자열을 2글자씩 끊어 만든다. , 이때 영문자로만된 글자 쌍만 유효, 기타 공백이나 특수문자, 숫자가 들어오면 버린다.
        // 대소문자의 차이는 무시한다.

        // 입력으로 들어온 두 문자의 자카드 유사열은 얼마인지 출력하라

        // 해결방법
        // 합집합 -> 각각 Map 선언해서, 원소의 숫자 세기 -> A와B 모두 있다면 그중 큰수 적용 but. 하나만 있다면 그 수만 적용
        // 교집합 -> 각 Map의 key를 SET에 모두 적용하여 중복인 수만

        HashMap<String, Integer> aSubMap = extractSubMap(str1);
        HashMap<String, Integer> bSubMap = extractSubMap(str2);

        Set<String> keySet = new HashSet<>(aSubMap.keySet());
        keySet.addAll(bSubMap.keySet());

        if (keySet.size() == 0) {
            return 65536;
        }

        double intersectionSize = keySet.stream()
                .map(subString -> Math.min(
                        aSubMap.getOrDefault(subString, 0),
                        bSubMap.getOrDefault(subString, 0))
                ).reduce(0, (integer, integer2) -> integer + integer2);

        double unionSize = keySet.stream()
                .map(subString -> Math.max(
                        aSubMap.getOrDefault(subString, 0),
                        bSubMap.getOrDefault(subString, 0))
                ).reduce(0, (integer, integer2) -> integer + integer2);

        double zakartaLike = intersectionSize / unionSize;

        return (int) Math.floor(zakartaLike * 65536);
    }

    private HashMap<String, Integer> extractSubMap(String str1) {
        HashMap<String, Integer> subMap = new HashMap<>();
        for (int i = 0; i < str1.length()-1; i++) {
            // 문자열이라면
            if (Character.isAlphabetic(str1.charAt(i)) && Character.isAlphabetic(str1.charAt(i + 1))) {
                String subString = str1.substring(i, i + 2).toUpperCase();
                subMap.put(subString, subMap.getOrDefault(subString, 0) + 1);
            }

        }
        return subMap;
    }
}
