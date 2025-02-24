// https://school.programmers.co.kr/learn/courses/30/lessons/155652
// 52 start



import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;

        // 최소 객실 사용 -> 최소한의 방만 사용
        // 사용시 10분 청소시간 필요
        //

        // 가장 많이 겹치는 부분 찾기 -> booktime + 10 해서
        // 겹치는 갯수 return

        // 해결법


        // 시작 순서대로 정렬
        // 14:10 - 19:20 나옴 -> 기준
        // 14:20 - 15:20 나옴 -> 겹치나? ->  나 & 겹치는 부분 추가
        // but. 시작시간이 마감시간보다 큰게 나오면 이제 빼준다.

        ArrayList<List<Integer>> lists = new ArrayList<>();
        for (String[] book : book_time) {
            String[] start = book[0].split(":");
            String[] end = book[1].split(":");
            lists.add(List.of(Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]),
                    Integer.parseInt(end[0]) * 60 + Integer.parseInt(start[1]) + 10,
                    0)
            );
        }
        // 내림차순
        lists.sort((b1, b2) -> b1.get(0) - b2.get(0));

        // 시작
        int usingRoomCount = 0;
        PriorityQueue<Integer> endTimeList = new PriorityQueue<>();
        for (List<Integer> book : lists) {
            usingRoomCount += 1;

            while (!endTimeList.isEmpty() && endTimeList.peek() <= book.get(0)) {
                endTimeList.poll();
                usingRoomCount -= 1;
            }

            endTimeList.add(book.get(1));

            answer = Math.max(answer, usingRoomCount);
        }

        return answer;
    }
}
