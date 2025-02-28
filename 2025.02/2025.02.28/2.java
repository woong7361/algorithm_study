// https://school.programmers.co.kr/learn/courses/30/lessons/154539
// 55 start


import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int [numbers.length];
        // 정수 배열 numbers,
        // 자신보다 뒤에 있는 수중 자신 보다 크면서 가장 가끼이있는 큰수를 '뒷 큰수'
        // 뒷 큰수의 배열을 반환하라 -> 없다면 -1

        // 해결법

        // 우선순위 큐 min HEap 사용
        // 9 1 에서
        // 5 들어옴 -> heap에 있다면 나보다 작은애 다 빼서 값 갱신 & 나 추가
        // 맨 마지막에 heap에 남아있는 애들 -1로 갱신

        // minHeap에 index랑 같이 넣어줘야함

        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>((t1, t2) -> t1.get(0) - t2.get(0));

        for (int i = 0; i < numbers.length; i++) {
            //비어있지 않고, 나중에 나온 숫자가 더 크다면
            while (!minHeap.isEmpty() && minHeap.peek().get(0) < numbers[i]) {
                List<Integer> poll = minHeap.poll();
                answer[poll.get(1)] = numbers[i];
            }

            minHeap.add(List.of(numbers[i], i));
        }

        for (List<Integer> min : minHeap) {
            answer[min.get(1)] = -1;
        }

        return answer;
    }

    // 9 1 5 3 6 2

    //stack
    // 9
    // 9 비교 1 -> 9 1
    // 9 1  비교 5

}
