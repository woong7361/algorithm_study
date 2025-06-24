// https://school.programmers.co.kr/learn/courses/30/lessons/12951
// 35 start


import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";


        // jadenCase 단어의 첫문잔가 대문자 & 그 외의 알파벳은 소문자
        // 단 첫문자가 알파벳이 아닐 때에는 이어지는 알파벳은 소문자로 쓰면 된다.
        // 문자 s가 주어졌을때 jaden case로 바꾸는 함수를 작성하시오
        // 공백이 연속해서 나올 수 있다.

        // 해결법
        // split " " -> 첫번째 소문자인 문자인가? -> 맞다면 대문자로 변경 (+26)

        StringBuilder builder = new StringBuilder();

        boolean prevBlank = true;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                builder.append(' ');
                prevBlank = true;
            } else if (prevBlank && Character.isLowerCase(s.charAt(i))) {
                builder.appendCodePoint(s.charAt(i) - 32);
                prevBlank = false;
            } else if (!prevBlank && Character.isUpperCase(s.charAt(i))) {
                builder.appendCodePoint(s.charAt(i) + 32);
                prevBlank = false;
            } else {
                builder.appendCodePoint(s.charAt(i));
                prevBlank = false;
            }
        }


        return builder.toString();
    }
}
