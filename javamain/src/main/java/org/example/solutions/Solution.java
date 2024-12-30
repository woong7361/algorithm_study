package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/181188


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;

        // 해결법
        // 모든 미사일 요격해야한다.

        // targets 정렬하기 정렬 순서 open, close 오름차순
        Arrays.sort(targets, (ints1, ints2) -> {
            if (Integer.compare(ints1[0], ints2[0]) < 0) {
                return -1;
            } else if (Integer.compare(ints1[0], ints2[0]) > 0) {
                return 1;
            } else {
                return Integer.compare(ints1[1], ints2[1]);
            }
        });
        List<int[]> temp = Arrays.asList(targets);
        LinkedList<int[]> list = new LinkedList<>(temp);

        for (int[] ints : list) {
            System.out.println("ints" + ints[0] + " " + ints[1]);
        }

        // 0부터 시작 0보다 큰 수 의 폐구간의 최솟값 찾기
        // 현재 미사일 위치가 폐구간보다 작다면 발사 & 해당 targets들 지우기
        // 아니라면 다음 수 ex. 1로 시작하는 target들 추가 & 최솟값 갱신

        // 만약 minD 값이 현재 나온 시작점보다 크다면? 이전에 새로 값을 표시해야함

        // 같은 값 빼고 & 최솟값 갱신 하고 &
        //  다음 값이 존재하면서 최솟값보다 크다면 +1  추가로 최솟값 초기화
        //  다음 값이 존재 하지 않으면 +1
        int missileD = 0;
        int minD = 100000001;
        while (! list.isEmpty()) {
            // 가장 작은값 갱신
            int[] remove = list.remove(0);
            int currentD = remove[0];
            minD = Math.min(minD, remove[1]);

            while (! list.isEmpty() && list.get(0)[0] == currentD) {
                list.remove(0);
                System.out.println("removed = " + list[0] + " " + list[1]);
            }

            if (list.isEmpty()) {
                System.out.println("currentD = " + currentD);
                answer += 1;
            }

            if (!list.isEmpty() && list.get(0)[0] >= currentD) {
                System.out.println("currentD = " + currentD);
                answer += 1;
                minD = 100000001;
            }
        }

        return answer;
    }
}