// https://www.acmicpc.net/problem/1717
// 10 start


import java.io.*;
import java.util.*;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // N+1 개의 집합 원소가 존재
        // 합집합 연산 & 교집합에 있는지 확인하는 연산 2개 존재
        // N <= 1000000 => int Array 4Mb
        // 같은 집합이 된다는 것은 해당 집합에 속하는 원소들을 수정하기

        // union find 찾기

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        int[] elements = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            elements[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken()), set1 = Integer.parseInt(st.nextToken()), set2 = Integer.parseInt(st.nextToken());

            if (command == 0) {
                union(elements, set1, set2);
            } else {
                sb.append(isInterSaction(elements, set1, set2) + "\n");
            }

        }
        bw.write(sb.toString());
        bw.flush();

        br.close();
    }

    private static String isInterSaction(int[] elements, int set1, int set2) {
        int head1 = findHead(elements, set1);
        int head2 = findHead(elements, set2);

        if (head1 == head2) {
            return "YES";
        } else {
            return "NO";
        }
    }

    private static void union(int[] elements, int set1, int set2) {
        // 둘이 같다면? 버린다.
        if (set1 == set2) {
            return;
        }

        // head를 찾는다.
        int head1 = findHead(elements, set1);
        int head2 = findHead(elements, set2);

        // head를 연결한다.
        elements[head1] = head2;
    }

    private static int findHead(int[] elements, int set) {
        if (elements[set] == set) {
            return set;
        }

        return elements[set] = findHead(elements, elements[set]);
    }

}


