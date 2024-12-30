// https://school.programmers.co.kr/learn/courses/30/lessons/150370


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {

        // A : 만료일 Map 형식으로 만들기
        HashMap<String, Integer> expPeriodMap = new HashMap<>();
        for (String term : terms) {
            String[] split = term.split(" ");
            String company = split[0];
            Integer month = Integer.parseInt(split[1]);

            expPeriodMap.put(company, month);
        }
        for (String s : expPeriodMap.keySet()) {
            System.out.println("s = " + s);
        }

        HashMap<Integer, Integer> expDateMap = new HashMap<>();
        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i];
            String[] split = privacy.split(" ");

            int totalDays = getTotalDay(split[0]);
            System.out.println("split[0] = " + split[0]);
            String company = split[1];

            int expTotalDays = totalDays + expPeriodMap.getOrDefault(company, -1) * 28;

            expDateMap.put(i + 1, expTotalDays);
        }

        // 순회하면서 파기해야할 개인정보 찾기

        ArrayList<Integer> integers = new ArrayList<>();
        int todayTotalDay = getTotalDay(today);
        for (Integer key : expDateMap.keySet()) {
            Integer totalDays = expDateMap.get(key);


            if (totalDays <= todayTotalDay) {
                integers.add(key);
            }
        }

        return integers.stream()
                .mapToInt(t -> t)
                .toArray();
    }


    private int getTotalDay(String date) {
        String[] dateSplit = date.split(".");


        return Integer.parseInt(dateSplit[0]) * 28 * 12 +
                Integer.parseInt(dateSplit[1]) * 28 +
                Integer.parseInt(dateSplit[2]);
    }
}
