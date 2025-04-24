// https://school.programmers.co.kr/learn/courses/30/lessons/68645
// 33 start


import java.util.*;

class Solution {
    public int[] solution(int n) {
        // 정수 n이 매개변수
        // 밑변과 높이가 n인 삼각형
        // 맨위부터 반시계방향으로 달팽이 채우기
        // 맨 위부터 모두 순서대로 합친 새로운 배열 return

        // count 1 시작
        // int[n][?] list 생성
        // 높이 n
        // 각 행에 n번 추가 type 0
        // 해당 열에 n-1번 추가 type 1
        // 거꾸로 행에 n-2번 추가 ... type 2
        // n이 1이 될때까지 추가하기 ...


        int type = 0;
        int number = 1;
        int count = n;
        int level = 0;
        List<List<Integer>> triangle = new ArrayList<>();
        List<Deque<Integer>> sideTriangle = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            triangle.add(new ArrayList<>());
            sideTriangle.add(new LinkedList<>());
        }

        while (count > 0) {
            if (type == 0) {// 0 2 4 ...
                for (int i = 0; i < count; i++) {
                    triangle.get(level*2 + i).add(number++);
                }

                count--;
            } else if (type == 1) { // n-1 n-2 n-3 ...
                for (int i = 0; i < count; i++) {
                    triangle.get(n - 1 - level).add(number++);
                }

                count--;
            } else if (type == 2) { // n-2 n-3 n-4 ...
                for (int i = 0; i < count; i++) {
                    sideTriangle.get(n - 2 - level - i).addFirst(number++);
                }
                count--;
            }

            if (type == 2) {
                level++;
            }
            type = (type + 1) % 3;
        }

        for (int i = 0; i < n; i++) {
            triangle.get(i).addAll(sideTriangle.get(i));
        }

        System.out.println("triangle.toString() = " + triangle.toString());

        return triangle.stream()
                .flatMap(it -> it.stream())
                .mapToInt(Integer::valueOf)
                .toArray();
    }
}
