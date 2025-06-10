package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/17684
// 54 start


import java.util.*;

class Solution {
    public int[] solution(String msg) {
        // 메시지 압축 무손실 알고리즘
        // LZW
        // 1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
        // 2. 사전에서 현재 길이와 일치하는 가장 긴 문자열 w를 찾는다.
        // 3. w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
        // 4. 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
        // 5. 단계 2로 돌아간다.
        // 압축 알고리즘이 영어 대문자로만 처리한다고 할때 사전의 색인은 A~Z 까지 1~26까지의 색인으로 시작한다.
        // 1 <= msg <= 1000


        // 해결법
        // Map initial - 대문자 26개 - 1~26 색인
        // 입력 단어가 Map에 있는지 확인
        //      있다면 가장 긴 입력 단어 찾기
        // 처리되지 않은 다음 글자가 있다면 w+c를 Map에 등록

        ArrayList<Integer> results = new ArrayList<>();
        HashMap<String, Integer> compressionDictionary = new HashMap<>();
        initialDictionary(compressionDictionary);

        int msgIndex = 0;
        int msgLength = msg.length();

        while (msgIndex < msgLength) {
            // 현재 단어 시작
            StringBuilder wordBuilder = new StringBuilder();
            wordBuilder.append(msg.charAt(msgIndex));

            // 여기서는 word 만들기만
            while (msgIndex+1 < msgLength) { // 다음 단어가 있다면 check
                if (compressionDictionary.containsKey(wordBuilder.toString() + msg.charAt(msgIndex + 1))) {
                    msgIndex++;
                    wordBuilder.append(msg.charAt(msgIndex));
                } else {
                    break;
                }
            }
            // 현재 다음 단어 위치
            // 현재 wordBuider도 안맞는 위치

            // 넘어간다면? 그냥 가능

            // answer에 추가하기
            results.add(compressionDictionary.get(wordBuilder.toString()));

            // 다음 단어가 있다면 사전에 등록
            msgIndex++;
            if (msgIndex < msgLength) {
                wordBuilder.append(msg.charAt(msgIndex));
                compressionDictionary.put(wordBuilder.toString(), compressionDictionary.size()+1);
            }

        }

        int[] answer = new int[results.size()];
        for (int i = 0; i < results.size(); i++) {
            answer[i] = results.get(i);
        }
        return answer;
    }

    private void initialDictionary(HashMap<String, Integer> compressionDictionary) {
        char word = 'A';

        for (int i = 1; i < 27; i++) {
            compressionDictionary.put(Character.toString(word++), i);
        }
    }
}
