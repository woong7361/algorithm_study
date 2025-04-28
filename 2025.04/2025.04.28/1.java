// https://school.programmers.co.kr/learn/courses/30/lessons/42885
// 33 start


import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        // 구명보트에 최대 2명 & 무게제한 있음
        // 구명보트를 최대한 적게 사용하여 모든 사람을 구출
        // 필요한 구명보트의 개수는?

        // 1명 넣고, 그 무게중 가장 가까운사람

        // 가장 가벼운사람 넣고, 가장 무거운사람 index 찾기
        // ...

        int lightIndex = 0;
        int heavyIndex = people.length - 1;

        Arrays.sort(people);

        while (lightIndex < heavyIndex) {
            if (limit < people[lightIndex] + people[heavyIndex]) {
                heavyIndex--;
            } else {
                lightIndex++;
                heavyIndex--;
            }

            answer++;
        }

        if (heavyIndex == lightIndex) {
            answer++;
        }

        return answer;
    }

    // 50 70 80
    // 0  1  2
    // 0  1
    // 00
}
