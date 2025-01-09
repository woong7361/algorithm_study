package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/176963


import java.util.HashMap;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        // 추억점수
        // 사진속에 나오는 인물의 그리움 점수 합산
        // 사람이 다수일때는 각 사람의 그리움 점수 합산

        // name 그리워하는 사람들
        // 각 사람별 그리움점수 정수 배열 yearning

        // name -> yearning map 생성 - 그리움 점수 map
        // photo iter
        //  photo 내부의 사람들이 yearning map에 있다면 점수 추가, 없다면 넘어가기

        HashMap<String, Integer> yearningMap = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            yearningMap.put(name[i], yearning[i]);
        }

        for (int i = 0; i < photo.length; i++) {
            for (String personInPhoto : photo[i]) {
                answer[i] += yearningMap.getOrDefault(personInPhoto, 0);
            }
        }

        return answer;
    }
}