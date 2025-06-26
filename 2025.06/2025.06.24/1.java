// https://school.programmers.co.kr/learn/courses/30/lessons/12941
// 13 start


import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        // 길이가 같은 배열 A B & 각 배열은 자연수로 이루어졌다.
        // A B 각각에서 한 개의 숫자를 뽑아 곱한다. -> 배열의 길이만큼 반복한다. -> 두 수를 곱합 값을 누적하여 더한다.
        // 누적값이 최소가 되는 경우의 수는?

        // !
        // 중복해서 뽑지 못한다.

        // 해결방법 ...
        // 가장 큰게 가장 적게있어야한다. -> 한개 배열은 오름차순 -> 다른 배열은 내림차순

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0; i < A.length; i++) {
            answer = answer + A[i] * B[A.length - 1 - i];
        }

        return answer;
    }
}
