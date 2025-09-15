// https://www.acmicpc.net/problem/17128
// 48 start


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 원형 큐
        // Ai ~ Ai+4까지의 곱 의 합 S
        // q번 바꿀때마다 합 구하기


        // 문제 설계
        // Si의 정의 Ai ~ Ai+3까지의 곱의 합
        // Ai의 값을 바꾸면
        // Si-3 Si-2 Si-1 Si 값이 음수로 바뀐다.

        // Stotal - (Si-3 Si-2 Si-1 Si)*2

        // 반복 진행

        // 문제 해결
        // Stotal 구한다.
        // Si[] Array 구한다.
        // Ai 변경시 Si ~ Si+3의 값 구한다.
        // Stotal 갱신
        // 단 i > n -> i = 0

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] Ai = new int[N];
        for (int i = 0; i < N; i++) {
            Ai[i] = Integer.parseInt(st.nextToken());
        }
        int[] interruptIndex = Arrays.stream(br.readLine().split(" "))
                .mapToInt(t -> Integer.parseInt(t) - 1)
                .toArray();

        int[] Si = new int[N];
        for (int i = 0; i < N; i++) {
            Si[i] = 1;
            for (int j = 0; j < 4; j++) {
                Si[i] = Si[i] * Ai[(i + j) % N];
            }
        }
        int sTotal = Arrays.stream(Si).sum();

        for (int interrupt : interruptIndex) {
            int interruptedSum = 0;
            for (int i = 0; i < 4; i++) {
                int index = interrupt - i < 0 ? interrupt - i + N : interrupt - i;

                Si[index] = -Si[index];
                interruptedSum += Si[index];
            }

            sTotal = sTotal + interruptedSum * 2;
            System.out.println(sTotal);
        }

        br.close();
    }




}


