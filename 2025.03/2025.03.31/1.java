// https://school.programmers.co.kr/learn/courses/30/lessons/84512
// 20 start


import java.util.*;

class Solution {
    public int solution(String word) {
        int answer = 0;

        // 사전에 알파벳 A E I O U만을 사용해 만들 수 있는 길이 5 이하의 모든 단어가 수록
        // 첫단어 A, 두번쨰 AA, 마지막 UUUUU

        // 이 단어가 사전에서 몇번째 단어인지 알아내시오

        // 첫단어 A, AA, AAA, AAAA, AAAAA, AAAAE, ...
        // A0000

        // A = 1, E = 2, I = 3, O = 4, U = 5

        // 10000 시작 0이 있다면 채우기 +1로 11000 11100 11110 11111
        // 다 채웠으면 추가하기 11112 11113 11114 11115
        // 5가 넘어가면 위로 넘기기 11120 11121 11122 11123 11124 11125

        String target = "";
        for (int i = 0; i < 5; i++) {
            if (i >= word.length()) {
                target += "0";
                continue;
            }

            if (word.charAt(i) == 'A') {
                target += "1";
            } else if (word.charAt(i) == 'E') {
                target += "2";
            } else if (word.charAt(i) == 'I') {
                target += "3";
            } else if (word.charAt(i) == 'O') {
                target += "4";
            } else if (word.charAt(i) == 'U') {
                target += "5";
            }
        }

        System.out.println("targert = " + target);

        String wordArr = "00000";
        while (!wordArr.equals(target)) {
            answer += 1;
            // 0이 있다면 1로 만들기 왼쪽부터
            if (wordArr.contains("0")) {
                for (int i = 0; i < 5; i++) {
                    if (wordArr.charAt(i) == '0') {
                        wordArr = wordArr.substring(0, i) + "1" + wordArr.substring(i+1);
                        break;
                    }
                }

                continue;
            }

            // 10진수로 만들고 +1 -> 6진수로 변환
            wordArr = Integer.toString(Integer.parseInt(wordArr, 6) + 1, 6);
        }

        return answer;
    }
}
