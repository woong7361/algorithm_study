// https://school.programmers.co.kr/learn/courses/30/lessons/17680
// 39 start


import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        // DB 캐시 크기에 따른 실행 시간 측정 프로그램 작성
        // 캐시크기 , 도시 이름 배열을 입력받는다.
        // 0 <= 캐시크기 <= 30
        // 캐시 교체 알고리즘은 LRU
        // cache hit -> 1sec, miss -> 5sec

        // DEQUEUE 활용? -> 중간에 접근하는 시간 소요 but. 캐시 교체가 빠르다.
        // Map 활용? -> 중간에 접근 시간 바로 가능 but. 캐시 교체가 느리다.

        if (cacheSize == 0) {
            return 5 * cities.length;
        }

        LinkedList<String> dequeue = new LinkedList<>();

        for (String city : cities) {
            city = city.toUpperCase();
            if (dequeue.contains(city)) { //hit
                dequeue.remove(city);
                dequeue.add(city);
                answer += 1;
            } else { //miss
                dequeue.add(city);
                if (dequeue.size() > cacheSize) {
                    dequeue.removeFirst();
                }

                answer += 5;
            }
            System.out.println("dequeue = " + dequeue);
        }


        return answer;
    }
}
