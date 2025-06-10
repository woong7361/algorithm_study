// https://school.programmers.co.kr/learn/courses/30/lessons/17687
// 17 start


import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        // 여러 사람이 숫자 하나씩을 차례대로 말하는 게임
        // 규칙
            // 1. 0부터 시작해서 차례대로 말한다
            // 2. 10 이상의 숫자부터는 한자리씩 끊어서 말한다. ex. 10 -> 1 | 0
        // 16진법으로 이 게임을 진행하기로 한다.
        // 튜브는 자신이 말해야하는 숫자를 스마트폰에 미리 출력하려고 한다.

        // 진법 n, 미리 구해야할 수자의 개수 t, 게임에 참가하는 인원 m, 튜브의 순서 p


        // 해결법
        // 현재 숫자 -> n진법 변환
        // 사람에 대입하면서 진행
        // p번째 숫자가 온다면 기록, p번째 숫자는 변수로 확인 (p--을 하면서 p==0일때 , p==0일때 한바퀴 돌려준다 +m을 해주어)
        int currentNumber = 0;
        while (t > 0) {
            String temp = Integer.toString(currentNumber, n).toUpperCase();

            for (int index = 0; index < temp.length(); index++) {
                if (t > 0 && p == 1) {
                    answer += temp.charAt(index);
                    t--;
                    p += (m);
                }

                p--;
            }

            currentNumber++;
        }

        return answer;
    }

}
