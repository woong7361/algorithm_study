// https://school.programmers.co.kr/learn/courses/30/lessons/12981
// 50 start


import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[]{0, 0};

        // 1부터 n까지 번호 n명의 사람 영어 끝말있기 진행
        // 마지막까지 가면 1번으로 다시 돌아온다.
        // 앞사람이 말한 단어의 마지막 문자로 시작하는 단어 말하기
        // 이전에 등장했던 단어 X
        // 한 글자 단어 X
        // 가장 먼저 탈락하는 사람의 번호화 그 사람이 몇번째에 탈락하는지를 구하라


        // A B A B 2명
        // 0 1 2 3
        //



        HashSet<String> usedWords = new HashSet<>();
        String tailWord = null;
        for (int index = 0; index < words.length; index++) {
            // if dup or word length <= 1 -> break
            String word = words[index];

            if ((tailWord != null && !word.startsWith(tailWord)) || usedWords.contains(word)) {
                answer[0] = (index % n) + 1;
                answer[1] = (index) / n + 1;

                break;
            }

            usedWords.add(word);
            tailWord = word.substring(word.length()-1);
        }

        return answer;
    }
}
