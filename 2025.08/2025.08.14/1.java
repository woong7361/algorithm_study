// https://www.acmicpc.net/problem/1013
// 20 start


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());


        // 전파의 기본 단위 {0, 1}
        // x+()는 임의의 개수 x(1개 이상)의 반복으로 이루어진 전파의 집합을 의미한다.
//        (xyx)+ ( ) 는 괄호 내의 xyx의 반복으로 이루어진 전파의 집합을 뜻한다. 아래는 이해를 돕기 위한 예제이다.
//
//        1+ = { 1, 11, 111, 1111, 11111, … }
//        10+ = { 10, 100, 1000, 10000, 100000, … }
//        (01)+ = { 01, 0101, 010101, 01010101, 0101010101, … }
//        (1001)+ = { 1001, 10011001, 100110011001, … }
//        10+11 = { 1011, 10011, 100011, 1000011, 10000011, … }
//        (10+1)+ = { 101, 1001, 10001, 1011001, 1001101, 100011011000001, … }
//         + 외에도 or 를 의미하는 | 기호가 있다. { x | y } 는 x 혹은 y 를 의미하는 것으로, { 0+ | 1+ } 는 { 0 , 1 , 00 , 11 , 000 , 111 , … } 의 집합을 의미한다.
//        (100 | 11)+ = { 100 , 11 , 10011 , 11100 , 1110011100 , 100111111100100, … }

        // 실제 전파 (100+1+ | 01)+
        // 다양한 전파 기록중 위의 패턴을 지니는 전파를 가려내는 프로그램이 필요하다.
        // 1 <= 문자열의 길이 <= 200

        // 문제 설계
        // 정규표현식의 문제이다.

        // 2가지로 나뉜다.
        // 01 -> 무조건 고정이다.
        // 100+1+  -> 100 고정 시작 -> 0 >= 0개 -> 1>= 1개

        // 따로 나오지는 않는다.
        // 2가지 matcher로 진행 예정... 01 matcher, 100+1+ matcher
        // 0부터 시작해서 matcher가 만족하는 부분을 구한다.
        // 만족하면 check 해둔다. 다음부터 만족하는 부분을 구한다.

        int iterCount = Integer.parseInt(br.readLine());
        for (int _t = 0; _t < iterCount; _t++) {
            String testCase = br.readLine();
            boolean[] check = new boolean[testCase.length()];
            Arrays.fill(check, false);

            for (int i = 0; i < testCase.length(); i++) {
                if (i == 0 || check[i-1]) {
                    matcher(testCase, i, new LinkedList<>(List.of("0", "1")), check);
                    matcher(testCase, i, new LinkedList<>(List.of("1", "0", "0+", "1+")), check);
                }
            }

            if (check[testCase.length() - 1]) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        br.close();
    }

    private static void matcher(String testCase, int start, LinkedList<String> regex , boolean[] check) {
        while (start < testCase.length() && !regex.isEmpty()) {
            // 단순 숫자일 떄 단순 비교
            // +가 있는 경우 계속 비교

            String compareString = regex.poll();
            if (compareString.endsWith("+")) {
                if (testCase.substring(start, start + 1).equals(compareString.substring(0, 1))) {
                    // 최소 한개라도 같다면
                    while (start < testCase.length() && testCase.substring(start, start + 1).equals(compareString.substring(0, 1))) {
                        if (regex.isEmpty()) check[start] = true;
                        start++;
                    }
                } else {
                    return;
                }
            } else {
                if (testCase.substring(start, start + 1).equals(compareString)) {
                    if (regex.isEmpty()) check[start] = true;
                    start++;
                } else {
                    return;
                }
            }

        }
    }

}
