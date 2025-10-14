// https://www.acmicpc.net/problem/17298
// 11 start


import java.io.*;
import java.util.*;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static int[][] horizental = {{-1, 0}, {1, 0}};
    public static int[][] vertical = {{0, 1}, {0, -1}};


    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수열 Ai가 주어진다.
        // 오큰수 NGE(i)를 구하려고 한다. Ai의 오큰수는 오른쪽에 있으면서 Ai보다 큰 수 중에서 가장 왼쪽에 있는 수를 의미한다. 그러한 수가 없는 경우에 오큰수는 -1이다.
        // 총 N개의 수 NGE(1), NGE(2), ..., NGE(N)을 공백으로 구분해 출력한다.
        // N (1 ≤ N ≤ 1,000,000)
        // A의 원소 A1, A2, ..., AN (1 ≤ Ai ≤ 1,000,000)

        // 문제 설계
        // 변곡점 문제?
        // 나보다 큰수가 나오면 exit 하는 문제
        // 나보다 작은 수가 나오면? continue 문제
        // stack에 쌓여간다.
            // 비어있다면 stack에 쌓는다.
            // 나보다 작으면 stack에 쌓는다.
            // 나보다 크다면 exit 한다.
                // 그 다음 stack의 수도 check 한다.
        // ! 단 이렇게 되면 sout의 순서 보장 못한다.
        // 백만을 저장해야 힌다.

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        int[] result = new int[N];
        Stack<Integer> stack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            result[i] = -1;
        }

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                result[stack.pop()] = numbers[i];
            }

            stack.add(i);
        }

        StringBuilder sb = new StringBuilder();
        // result 출력
        for (int num : result) {
            sb.append(num);
            sb.append(' ');
        }
        System.out.println(sb.toString());

        br.close();
    }





}


