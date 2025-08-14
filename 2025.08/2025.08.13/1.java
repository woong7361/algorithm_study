// https://www.acmicpc.net/problem/1011
// 13 start

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 처음 이동 1
        // 그 다음 이동 k-1 or k or k+1
        // 단 y지점에 도착해서 안정성을 위해 도착하기 직전 이동거리는 반드시 1이다.
        // x에서 y까지의 이동의 최솟값은?
        // 0 <= x < 2^31

        // 문제 설게
        // 양쪽 무조건 1
        // 최솟값으로 나아가려면 +로 가야한다.
        // 1 2 ... n ... n ... 2 1
        // y-x가 targetValue
        // targetValue - 2n 갱신
        // if targetValue < 2n 이면 양쪽에 더하기가 불가능 ->
        //      if targetValue <= n 이면 1개만 추가하면 가능
        //      if n < targetValue < 2n 이면 2개를 추가해야 가능
        // 반복

        ArrayList<Integer> result = new ArrayList<>();

        int N = Integer.valueOf(br.readLine());
        for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
            int targetValue = -Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());

            int answer = 0;
            int moveDistance = 1;

            while (targetValue > 0) {
                if (targetValue >= 2 * moveDistance) {
                    targetValue = targetValue - 2 * moveDistance;
                    moveDistance++;
                    answer += 2;
                } else if (targetValue > moveDistance) {
                    targetValue = 0;
                    answer += 2;
                } else {
                    targetValue = 0;
                    answer += 1;
                }
            }

            result.add(answer);
        }

        for (Integer i : result) {
            System.out.println(i);
        }


        br.close();
    }

}
