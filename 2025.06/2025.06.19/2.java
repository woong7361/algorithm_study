// https://school.programmers.co.kr/learn/courses/30/lessons/12973
// 32 start


import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int answer = 0;

        // 알파벳 소문자로 이루어진 문자열
        // 문자열에서 같은 알파벳이 붙어있는 짝 찾기
        // 둘을 제거한후 문자열을 붙인다.
        // 반복한다.
        // 모두 지우는 것을 성공하면 1 불가능하면 0
        // 문자열 길이 <= 1000000

        // stack 사용
        // peek()을 사용해서 중복되는지 보기
        // b a a b

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().equals(c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        if (stack.isEmpty()) {
            answer = 1;
        }

        return answer;
    }
}
