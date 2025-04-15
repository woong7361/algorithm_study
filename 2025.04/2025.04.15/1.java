package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/176962


import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = {};

        //왼쪽이 더 크면 양수 -> 오름차순(ASC) default
        List<String[]> sortedPlans = Arrays.stream(plans)
                .sorted((strings, temp) -> {
                    String[] t1 = strings[1].split(":");
                    String[] t2 = temp[1].split(":");
                    if (Integer.parseInt(t1[0]) > Integer.parseInt(t2[0])) {
                        return 1;
                    } else if (Integer.parseInt(t1[0]) < Integer.parseInt(t2[0])) {
                        return -1;
                    } else {
                        if (Integer.parseInt(t1[1]) > Integer.parseInt(t2[1])) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                })
                .collect(Collectors.toList());

        Deque<String[]> pauses = new ArrayDeque<>();
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < sortedPlans.size(); i++) {
            String[] plan = sortedPlans.get(i);

            // 마지막 원소면 그냥 추가
            if (i == sortedPlans.size() - 1) {
                result.add(plan[0]);
                break;
            }

            String[] nextPlan = sortedPlans.get(i+1);
            int timeInterval = getTimeInterval(plan[1], nextPlan[1]);
            pauses.push(plan);

            // time Interval 쓰기
            while (timeInterval > 0 && !pauses.isEmpty()) {
                String[] pop = pauses.pop();

                if (timeInterval >= Integer.parseInt(pop[2])) {
                    timeInterval = timeInterval - Integer.parseInt(pop[2]);
                    result.add(pop[0]);
                } else {
                    pauses.push(new String[]{pop[0], pop[1], String.valueOf(Integer.parseInt(pop[2]) - timeInterval)});
                    timeInterval = 0;
                }
            }
        }

        while (!pauses.isEmpty()) {
            result.add(pauses.pop()[0]);
        }

        return result.toArray(new String[0]);
    }

    private int getTimeInterval(String t1, String t2){
        String[] split1 = t1.split(":");
        int t1Time = Integer.parseInt(split1[0]) * 60 + Integer.parseInt(split1[1]);

        String[] split2 = t2.split(":");
        int t2Time = Integer.parseInt(split2[0]) * 60 + Integer.parseInt(split2[1]);

        return t2Time - t1Time;
    }
}
