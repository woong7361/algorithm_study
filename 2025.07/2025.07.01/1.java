
// https://school.programmers.co.kr/learn/courses/30/lessons/12909
// 51 start


import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        // 올바른 괄호를 찾아라 -> return true or false
        // 문자열 s는 ( ) 로만 이루어져있다.

        // 문제 설계
        // 제대로 닫치기만 하면 된다.
        // stack에 ( ) 짝이 맞다면 없앤다 Stack이 남아있다면 false 없다면 true

        Stack<Character> stack = new Stack<>();

        for (Character c : s.toCharArray()) {
            // c가 ')' 이고, stack.peek()이 '(' 이면 pop

            // c가 ')' 일때 못지우면 return false
            if (c.equals('(')) {
                stack.push(c);
            } else if (c.equals(')') && !stack.isEmpty() && stack.peek().equals('(')) {
                stack.pop();
            } else{
                answer = false;
                break;
            }
        }

        if (!stack.isEmpty()) {
            answer = false;
        }

        return answer;
    }
}
