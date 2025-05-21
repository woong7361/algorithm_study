
// https://school.programmers.co.kr/learn/courses/30/lessons/42860
// 39 start


import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int solution(String name) {
        int answer = 0;

        // 처음에는 A로만 이루어져 있다. (각 자리수마다)
        // 조이스특만을 이용해 글자를 만들기
        // 위: 다음 알파벳, 아래: 이전 알파벳, 왼쪽: 커서를 왼쪽으로, 오른쪽: 커서를 오른쪽으로
        // 가장 빠른 방법은?


        // ! 각 알파벳이 얼마나 움직여야하는가?
        // ! 어느 방향으로 움직여야 가장 빠른가?

        // 1. 각 자리수에서 얼마나 움직여야하는지 계산 (절댓값 계산 A -> Z or Z -> A 정방향 역방향)
        //      1) 윗 방향: char (target) - char a
        //      2) 아랫 방향: a -> z = 1: char z - char (target) + 1
        // 2. 움직여야하는 방향 계산하기
        //      1) 왼쪽으로 쭉, 2) 오른쪽으로 쭉, 3) 왼쪽 오른쪽

        IntStream moveStream = name.chars()
                .map(target -> {
                    return Math.min(target - 'A', 'Z' - target + 1);
                });

        int[] array = moveStream
                .map(it -> it > 0 ? 0 : -1)
                .toArray();

        int[] visit = new int[name.length()];
        Arrays.fill(visit, -1);
        find(Arrays.copyOf(array, array.length), visit, 0);


        return answer;
    }

    private int find(int[] dest, int[] visit, int index) {
        // 전부 방문했다면 끝
        if (allVisit(dest, visit)) {
            return 0;
        }

        // 오른쪽 방문, 왼쪽 방문
        int leftIndex = index - 1 < 0 ? dest.length - 1 : index - 1;
        visit[leftIndex] = 1;
        int a = find(dest, visit, leftIndex);
        visit[leftIndex] = -1;

        int rightIndex = (index + 1) % dest.length;
        visit[rightIndex] = 1;
        int b = find(dest, visit, rightIndex);
        visit[rightIndex] = 1;

        return Math.min(a, b) + 1;
    }

    private boolean allVisit(int[] dest, int[] visit) {
        for (int index = 0; index < dest.length; index++) {
            if (!(dest[index] == 0 && visit[index] > 0)) {
                return false;
            }
        }

        return true;
    }


}
