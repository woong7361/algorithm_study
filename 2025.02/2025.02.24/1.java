// https://school.programmers.co.kr/learn/courses/30/lessons/155652
// 52 start


import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";

        // 두 문자열 s, skip, index가 주어질때 문자열 s의 각 알파벳을 index 만큼 두의 알파벳으로
        // index만큼의 뒤의 알파벳이 z를 넘어갈 경우 a로 돌아온다
        // skip에 있는 알파벳은제외하고 건너뛴다.

        // ex. s aukks, skip wbqd, index 5

        Set<Integer> skipSet = new HashSet<>();
        for (int i = 0; i < skip.length(); i++) {
            skipSet.add((int) skip.charAt(i));
        }

        StringBuilder result = new StringBuilder();
        // iteration s, -> chartAt + index , but. skip have? ++
        for (int i = 0; i < s.length(); i++) {
            int asciiNumber = (int) s.charAt(i);

            // asciiNumber + 1...index까지 skip이 있다면  +1 더
            int skipCount = 0;
            while (skipCount < index) {
                // next
                asciiNumber = asciiNumber == 123 ? 97 : asciiNumber + 1;

                if (skipSet.contains(asciiNumber)) {
                    continue;
                }

                skipCount += 1;
            }

            result.appendCodePoint(asciiNumber);
        }

        return result.toString();
    }
}
