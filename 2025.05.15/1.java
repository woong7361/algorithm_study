// https://school.programmers.co.kr/learn/courses/30/lessons/42890
// 39 start


import java.util.*;
import java.util.stream.Collectors;

class Solution {
    List<String> resultList = new ArrayList<>();

    public int solution(String[][] relation) {
        resultList = new ArrayList<>();
        // 후보키의 최대 개수를 구하시오
        // 유일성 & 최소성을 만족

        // 컬럼은 8개 이하
        // row는 20개 이하

        // 해결방안
        // 전탐색
        // 1 ... n 까지,
        // 유일성 검사 -> SET 으로 해결
        // 최소성 검사 -> LIST CONTAIN 연산으로 해결
        for (int i = 1; i < relation[0].length + 1; i++) {
            permutation(relation, new ArrayList<>(), i);
        }

        return resultList.size();
    }

    // 자기 이후 번호(컬럼)만 재귀
    private void permutation(String[][] relation, List<Integer> key, int size) {
        // 원소의 개수가 작은 순서대로 실행
        if (key.size() == size) {
            System.out.println("key = " + getKeyString(key));
            // 유일성 체크
            if (uniqueTest(key)) {
                return;
            }

            // 최소성 체크
            if (minimalityTest(relation, key)) {
                resultList.add(getKeyString(key));
            }

            return;
        }

        // 재귀
        for (int column = key.isEmpty() ? 0 : key.get(key.size() - 1) + 1; column < relation[0].length; column++) {
            key.add(column);
            permutation(relation, key, size);
            key.remove(Integer.valueOf(column));
        }
    }

    private boolean uniqueTest(List<Integer> key) {
        return resultList.stream()
                .anyMatch(candidateKey -> getKeyString(key).contains(candidateKey));
    }

    private boolean minimalityTest(String[][] relation, List<Integer> key) {
        HashSet<String> set = Arrays.stream(relation)
                .map(row -> {
                    StringBuilder value = new StringBuilder();
                    key.stream()
                            .forEach(column -> value.append(row[column]));

                    return value.toString();
                })
                .collect(Collectors.toCollection(HashSet::new));

        return set.size() == relation.length;
    }

    private String getKeyString(List<Integer> key) {
        StringBuilder t = new StringBuilder();

        key.stream()
                .map(String::valueOf)
                .forEach(t::append);
        return t.toString();
    }
}
