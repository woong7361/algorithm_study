// https://www.acmicpc.net/problem/1027
// 10 start


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 고층빌딩이 가장 많이 보이는 고층빌딩을 찾으려한다.
        // 빌딩은 총 N개, 빌딩은 선분으로 표현된다.
        // i번째 빌딩은 i,0 ` i,높이 의 선분으로 나타낼 수 있다.
        // 선분이 막히지 않아야 보인다고 할 수 있다.

        // 1 <= N <= 50

        // 문제 설계
        // 좌표라고 생각 & A와 B를 있는 직선의 방정식을 세운다.
        // A와 B 사이의 모든 점을 확인하여 모두 직선 아래에 있다면 볼 수 있는 건물이다. (같아도 안된다.)
        // 가장 큰 값을 찾는다.


        int N = Integer.parseInt(br.readLine());

        int[] answer = new int[N];
        double[] buidngs = new double[N];

        String[] lines = br.readLine().split(" ");

        for (int i = 0; i < lines.length; i++) {
            buidngs[i] = Double.parseDouble(lines[i]);
        }

        for (int i = 0; i < buidngs.length; i++) {
            answer[i] = countSeeBuilding(buidngs, i);
        }

        System.out.println(Arrays.stream(answer).max().getAsInt());

        br.close();
    }

    private static int countSeeBuilding(double[] buidngs, int i) {
        // 왼쪽으로
        int leftCount = 0;
        int left = i - 1;
        while (left >= 0) {
            // 직선의 방정식 세우기
//            (left, buidings[left]) (i, building[i])
//            y = ((buidngs[i] - buidngs[left]) / (i - left)) * (x - left) + buidngs[left]
            leftCount++;
            for (int t = left + 1; t < i; t++) {
                if (buidngs[t] >= ((buidngs[i] - buidngs[left]) / (i - left)) * (t - left) + buidngs[left]) {
                    leftCount--;
                    break;
                }
            }

            left--;
        }

        // 오른쪽으로
        int rightCount = 0;
        int right = i + 1;
        while (right < buidngs.length) {

            rightCount++;
            for (int t = right - 1; t > i; t--) {
                if (buidngs[t] >= ((buidngs[right] - buidngs[i]) / (right - i)) * (t - i) + buidngs[i]) {
                    rightCount--;
                    break;
                }
            }

            right++;
        }

        return leftCount + rightCount;
    }


}


