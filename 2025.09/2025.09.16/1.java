// https://www.acmicpc.net/problem/15312
// 58 start


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 영어 알파벳 이름 궁합
        // 일의 자리 수의 합
        // 이름 한글자를 번갈아 두어야함
        // A와 B의 이름 숫자는 같다.
        int[] evaluation = new int[]{3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};

        String me = br.readLine();
        String you = br.readLine();

        int[] groups = new int[me.length() * 2];
        for (int i = 0; i < me.length(); i++) {
            groups[i*2] = evaluation[me.charAt(i) - 'A'];
            groups[i*2 + 1] = evaluation[you.charAt(i) - 'A'];
        }

        int[] next = new int[groups.length - 1];
        while (groups.length > 2) {
            for (int i = 0; i < groups.length - 1; i++) {
                next[i] = (groups[i] + groups[i + 1]) % 10;
            }

            groups = next;
            next = new int[groups.length - 1];
        }

        System.out.println(groups[0] + "" + groups[1]);

        br.close();
    }
}


