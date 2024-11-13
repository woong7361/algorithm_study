// https://leetcode.com/problems/factorial-trailing-zeroes/
import java.util.*;

class Solution {
    public int trailingZeroes(int n) {
        if (n <= 0) {
            return 0;
        }

        //숫자가 너무 커짐
        // dp 형식으로 가자
        // 1 -> n 까지 가면서 /5 or /2 시행 & 기록
        int totalDiv2Count = 0;
        int totalDiv5Count = 0;

        HashMap<Integer, List<Integer>> dictionary = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            int div2Count = 0;
            int div5Count = 0;

            // i를 2로
            int temp = i;
            while (temp % 2 == 0) {
                temp = temp / 2;
                div2Count++;
            }

            while (temp % 5 == 0) {
                temp = temp / 5;
                div5Count++;
            }

            totalDiv2Count += div2Count;
            totalDiv5Count += div5Count;

            // dict에 있으면 ++ & 기록
            // 없으면 안나눠질때까지 & 기록
        }

        return Math.min(totalDiv2Count, totalDiv5Count);
    }
}
