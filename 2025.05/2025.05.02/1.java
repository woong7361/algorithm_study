// https://school.programmers.co.kr/learn/courses/30/lessons/64065
// 51 start


import java.util.*;

class Solution {
    public int[] solution(String s) {
        // 중복된 원소 가능
        // 순서가 있다.
        // 개수는 유한하다.
        // 부분집합이 있을때 튜플을 구하여라 - 집합은 원소의 순서가 바뀌어도 상관 없다.
        // {{a1}, {a1, a2}, {a1, a2, a3}, {a1, a2, a3, a4}, ... {a1, a2, a3, a4, ..., an}}

        // 결론 원소 1개 -> 가장 첫번째 수
        // 원소 2개 -> 첫번째를 제외한 수가 두번째 수
        // 원소 n개 -> 나오지 않은 수를 빼면 n번째 수가 나온다.
        // n은 최대 500

        // String s ex. "{{2},{2,1},{2,1,3},{2,1,3,4}}"

        HashMap<Integer, List<Integer>> subSetMap = new HashMap<>();
        ArrayList<Integer> subSet = new ArrayList<>();
        StringBuilder numberBuilder = new StringBuilder();
        for (int i = 1; i < s.length()-1; i++) {
            if (s.charAt(i) == '{') {
                subSet = new ArrayList<>();
            } else if (s.charAt(i) == '}') {
                subSet.add(Integer.parseInt(numberBuilder.toString()));
                numberBuilder = new StringBuilder();

                subSetMap.put(subSet.size()-1, subSet);
            } else if (s.charAt(i) == ',') {
                if (numberBuilder.length() > 0) {
                    subSet.add(Integer.parseInt(numberBuilder.toString()));
                    numberBuilder = new StringBuilder();
                }
            } else {
                numberBuilder.append(s.charAt(i));
            }
        }

        int maxValue = subSetMap.keySet().stream().mapToInt(t -> t).max().getAsInt()+1;

        HashMap<Integer, Boolean> visit = new HashMap<>();
        int[] answer = new int[maxValue];
        for (int i = 0; i < maxValue; i++) {
            Integer value = subSetMap
                    .get(i).stream()
                    .filter(t -> visit.getOrDefault(t, true))
                    .findFirst()
                    .get();

            visit.put(value, false);

            answer[i] = value;
        }

        return answer;

//        return new int[]{1, 23};
    }
}
