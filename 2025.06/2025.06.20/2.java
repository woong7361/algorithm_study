// https://school.programmers.co.kr/learn/courses/30/lessons/12953
// 30 start


import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int answer = 0;

        // 두수의 최소 공배수
        // 두수의 배수 중 곹옹이 되는 가장 작은 수
        // n개의 수의 최소 공배수는 ?

        // 1 <= arr <= 15

        // 주의사항

        // 해결 방법
        // (option) 자기 자신의 배수를 찾는다. ex. 2 4 -> 2하나로 압축 가능
        // 가장 큰수를 찾아 곱해본다. -> 각각의 수들로 나머지가 0이면 된다.

        int maxValue = Arrays.stream(arr)
                .max()
                .getAsInt();

        int count = 1;
        while (! checkMultiple(arr, maxValue * count)) {
            count++;
        }


        return maxValue * count;
    }

    private boolean checkMultiple(int[] arr, int value) {
        boolean result = true;

        for (int num : arr) {
            if (value % num != 0) {
                result = false;
                break;
            }
        }

        return result;
    }
}
