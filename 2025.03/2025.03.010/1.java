package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/92341
// 16 start


import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 주차장의 입차시간과 출차시간 기록이 주어진다.
        // 차량별로 주차 요금을 계산하고싶다.
        // 기본요금과, 단위시간당 요금이 있다.
        // 초과한 시간이 단위시간으로 나누어지지 않다면 '올림' 한다.
        // 차량 번호가 작은 자동차부터 청구할 주차요금을 배열로 반환하라

        // fees[0] fees[1] 기본시간, 기본요금
        // fess[2] fees[3] 단위시간 단위요금

        // record: "시각 차량번호 내역" 내역은 IN or OUT
        // 기본 오름차순
        // 하루동안의 입출차만 기록되어있다.

        // 해결법

        // 처음 들어오면 IN_MAP
        HashMap<Integer, Integer> inMap = new HashMap<>();
        HashMap<Integer, Integer> timeMap = new HashMap<>();
        for (String record : records) {
            String[] splitRecord = record.split(" ");

            Integer carNumber = Integer.valueOf(splitRecord[1]);
            if (splitRecord[2].equals("IN")) {
                inMap.put(carNumber, timeToMinutes(splitRecord[0]));
            } else if (splitRecord[2].equals("OUT")) {
                timeMap.put(
                        carNumber,
                        timeMap.getOrDefault(carNumber, 0) + timeToMinutes(splitRecord[0]) - inMap.remove(carNumber));
            } else {
                continue;
            }
        }

        for (Integer carNumber : inMap.keySet()) {
            timeMap.put(
                    carNumber,
                    timeMap.getOrDefault(carNumber, 0) + timeToMinutes("23:59") - inMap.get(carNumber));
        }

        // OUT 들어오면 시간 계산 후 INMAP 초기화 (두번 들어오는거 생각)
        // 끝난 후 차량번호 순으로 정렬 key
        // 계산

        List<Integer> sortedCarNumber = timeMap.keySet()
                .stream()
                .sorted((t1, t2) -> t1 - t2)
                .collect(Collectors.toList());

        ArrayList<Integer> result = new ArrayList<>();
        for (Integer carNumber : sortedCarNumber) {
            // 기준시간보다 같거나 낮을때
            Integer totalTime = timeMap.get(carNumber);
            int totalFees = 0;
            if (totalTime <= fees[0]) {
                totalFees = fees[1];
            } else {
                totalTime -= fees[0];
                totalFees += fees[1];

                totalFees = totalFees + (totalTime / fees[2]) * fees[3];
                if (totalTime % fees[2] > 0) {
                    totalFees += fees[3];
                }
            }

            result.add(totalFees);

            // 기준시간보다 클때
        }

        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    public int timeToMinutes(String time) {
        String[] temp = time.split(":");
        return Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
    }
}
