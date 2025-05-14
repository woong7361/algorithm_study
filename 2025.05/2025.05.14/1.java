// https://school.programmers.co.kr/learn/courses/30/lessons/49993
// 57 start


import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = skill_trees.length;
        // 스킬트리 순서 존재
        // 가능한 스킬트리 개수를 return
        // 스킬은 알파벳 대문자로 표현된다.

        // skill list 작성,
        // 순서대로 탐색 -> skill list에 있다면 잠깐 멈춘다.
        // 멈추었을때 CBD에서 B가 있다면?

        char[] skillArray = skill.toCharArray();

        for (String skillTree : skill_trees) {
            HashMap<Character, Integer> indexMap = new HashMap<>();

            for (int i = 0; i < skillTree.length(); i++) {
                indexMap.put(skillTree.charAt(i), i);
            }

            int prevIndex = -1;
            for (char ski : skill.toCharArray()) {
                // 정상 0 1 2 3 5 8
                // 비정상 0 1 X X 2
                // -1 0 | 0 1 | 1 X | X X | X 2 !!여기서 발생
                // next가 더 커야함
                if (indexMap.getOrDefault(ski, 100) < prevIndex) {
                    answer -= 1;
                    break;
                }

                prevIndex = indexMap.getOrDefault(ski, 100);
            }
        }

        return answer;
    }
}
