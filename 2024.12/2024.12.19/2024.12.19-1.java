// https://school.programmers.co.kr/learn/courses/30/lessons/340199

import java.util.*;

class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 1;
        while (Math.max(bill[0], bill[1]) > Math.max(wallet[0], wallet[1]) || Math.min(bill[0], bill[1]) > Math.min(wallet[0], wallet[1])) {
            answer += 1;
            bill = fold(bill);
        }

        return answer;
    }

    public int[] fold(int[] bill) {
        int big = Math.max(bill[0], bill[1]) / 2;
        int small = Math.min(bill[0], bill[1]);

        return new int[]{big, small};
    }
}
