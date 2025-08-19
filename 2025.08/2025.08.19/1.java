// https://www.acmicpc.net/problem/1021
// 08 start


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());


        // N개의 원소를 지니고있는 양방향 순환 큐

        // 3가지 연산 가능
        // 1. 첫번째 원소 뽑아내기
        // 2. 왼쪽으로 한칸 이동하기
        // 3. 오른쪽으로 한칸 이동하기

        // 큐에 처음에 포함되어있던 수 N이 주어진다.
        // 그리고 뽑아내려고 하는 원소의 위치가 주어진다. 이때 그 원소를 주어진 순서대로 뽑아내는데 드는 2번 3번 연산의 최솟값은?

        // 문제 설계
        // 구현 하자
        int answer = 0;

        ArrayList<Integer> queue = new ArrayList<>();
        LinkedList<Integer> targets = new LinkedList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            targets.add(Integer.parseInt(st.nextToken())-1);
        }
        for (int i = 0; i < N; i++) {
            queue.add(i);
        }

        int current = 0;
        while (!targets.isEmpty()) {
            int target = targets.poll();

            // 우측으로 이동
            int right = current;
            int rightCount = 0;
            while (!queue.get(right).equals(target)) {
                rightCount++;
                right = right + 1 >= queue.size() ? 0 : right + 1;
            }

            // 좌측으로 이동
            int left = current;
            int leftCount = 0;
            while (!queue.get(left).equals(target)) {
                leftCount++;
                left = left - 1 < 0 ? queue.size() - 1 : left - 1;
            }

            answer += Math.min(rightCount, leftCount);

            current = right;
            queue.remove(current);
            if (current == queue.size()) {
                current = 0;
            }
        }

        System.out.println(answer);

        br.close();
    }


}
