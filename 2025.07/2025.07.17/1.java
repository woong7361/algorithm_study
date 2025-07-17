import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        // 억억단
        // 1억 * 1억 구구단
        // e를 정하여 알려주고, e 이하의 임의의 수 s를 여러개 이야기
        // 각 s에 대하여 s보다 크거나 같고, e보다 작거나 같은 수 중에서 가장 억억단에서 가장 많이 등장한 수를 답해야한다.
        // 여러개가 있다면 그중 가장 작은 수를 답한다.

        // e <= 5,000,000

        // 문제 설계
        // 어떤 숫자가 억억단에서 몇개가 나오는지 확인하고 싶다.
        // 나누어서 확인 ex. 12 = 2 * 2 * 3 => 3개 => 2 2개, 3 1개
        //  2를 선택 X 1 2 & 3을 선택 X, 1
        // 3*2 = 6가지 맞나? => 1*12. 2*6. 3*4, 4*3, 6*2, 12*1 => 중복/2
        // 전부 안나누어 질때 1가지

        // 근데 DP로 시간단축이 아닐시 Time Out 가능성 존재

        // 문제 해결
        // starts 의 min값 찾기
        // 몇개 나오는지 배열 구성
        // starts 순회하면서 계산

        int[] copy = Arrays.copyOf(starts, starts.length);

        Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();
        Arrays.sort(starts);
        long[] freq = new long[e + 1];
        int[] result = new int[e + 2];

        for (int num = starts[0]; num <= e; num++) {
            findSubSet(dp, num);
            freq[num] = countMany(dp.get(num));
        }


        long maxValue = -1;
        for (int num = e; num >= starts[0]; num--) {
            result[num] = result[num + 1];
            if (freq[num] >= maxValue) {
                result[num] = num;
                maxValue = freq[num];
            }
        }

        for (int i = 0; i < copy.length; i++) {
            answer[i] = result[copy[i]];
        }


        return answer;
    }

    private long countMany(Map<Integer, Integer> subSet) {
        if (subSet.containsKey(1)) {
            return 1;
        }

        long result = 1;
        for (Integer value : subSet.values()) {
            result = result * (value+1);
        }

        return result;
    }

    private Map<Integer, Integer> findSubSet(Map<Integer, Map<Integer, Integer>> dp, int num) {
        // dp에 있을 경우 그냥 반환
        if (dp.containsKey(num)) {
            return dp.get(num);
        }

        HashMap<Integer, Integer> subSet = new HashMap<>();
        // 그게 아니면 1~num까지로 계속 나누어서 확인
        for (int divNum = 2; divNum <= Math.sqrt(num); divNum++) {
            // 나누어질 때
            if (num % divNum == 0) {
                Map<Integer, Integer> subsubSet = findSubSet(dp, num / divNum);
                subSet.putAll(subsubSet);

                subSet.put(divNum, subSet.getOrDefault(divNum, 0) + 1);
                break;
            }

        }
        // 소수일 경우 (empty) => 자신만 담기
        if (subSet.isEmpty()) {
            subSet.put(num, 1);
        }

        // 등록 하기
        dp.put(num, subSet);

        return subSet;
    }
}
