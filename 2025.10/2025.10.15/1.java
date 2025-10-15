// https://www.acmicpc.net/problem/2156
// 52 start


import java.io.*;
import java.util.*;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 포도주를 가장 많이 먹는 방법은?
        // 잔을 선택하면 모두 마셔야한다.
        // 연속으로 놓여있는 3잔을 마실수는 없다

        // 문제 설계
        // DP로 해결
        // 과거가 0연속 선택일 때
        // 과거가 1연속 선택일 때
        // 과거가 2연속 선택일 때

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        int[] wine = new int[N+1];

        for(int i=1; i<=N; i++){
            wine[i] = Integer.parseInt(br.readLine());
        }

        dp[0]=0;
        for(int i=1; i<=N;i++){
            if(i ==1){
                dp[i]=wine[i];
            }else if(i==2){
                dp[i]=wine[i]+wine[i-1];
            }else{
                dp[i]= Math.max(dp[i-1],Math.max(dp[i-2]+wine[i],dp[i-3]+wine[i]+wine[i-1]));
            }
        }

        System.out.println(dp[N]);

        br.close();
    }
}


