// https://www.acmicpc.net/problem/3613
// 35 start


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 자바 첫문자 소문자 나머지 알아서
        // c++ 밑줄을 사용

        // c++ <-> java 형식의 프로그램 개발

        // 영어 알파벳과 밑줄로만 이루어져있다.
        // 둘다 소문자로 시작한다.

        // 둘다 아니면 ERROR!를 출력한다.
        // ERROR일 경우: 첫번째에 소문자가 아니다. _가 두번 연속 나온다. 맨 마지막이 _이다. 대문자와 _가 같이있다.


        String originString = br.readLine();

        if (!Character.isLowerCase(originString.charAt(0)) || originString.contains("__") || originString.endsWith("_")
                || (!originString.toLowerCase().equals(originString) && originString.contains("_"))
        ) {
            System.out.println("Error!");
            return;
        }

        StringBuilder builder = new StringBuilder();
        if (originString.contains("_")) {
            // c++ -> java
            for (int i = 0; i < originString.length(); i++) {
                if (originString.charAt(i) == '_') {
                    i += 1;
                    builder.append(Character.toUpperCase(originString.charAt(i)));
                } else {
                    builder.append(originString.charAt(i));
                }
            }
        } else {
            // java -> c++
            // 대문자를 만나면 _ + 소문자
            for (char c : originString.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    builder.append("_");
                }
                builder.append(Character.toLowerCase(c));
            }
        }

        System.out.println(builder.toString());

        br.close();
    }
}


