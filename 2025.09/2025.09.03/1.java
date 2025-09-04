// https://www.acmicpc.net/problem/1041
// 15 start


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());


        // 주사위 N^3 개로 N N N 정육각형 만들기
        // 정육각형의 보이는 수의 가장 작은 합은?

        // 문제 설게
        // 1. 하나만 보이는 주사위의 수 -> 상단면(n-2)^2 + 옆면(n-2)(n-1)*5
        // 2. 2면만 보이는 주사위의 수 -> 4(n-2) + 4(n-1)
        // 3. 3면만 보이는 주사위의 수 -> 4개
        // 총?

        // 1면만 보이는 주사위의 최솟값 -> 가장 작은 수
        // 2면만 보이는 주사위의 수의 최솟값 -> 연결된 2면중 가장 작은 합
        //      A F, C D, B E 가 아닌 모든 조합의 합중 최솟값
        // 3면만 보이는 주사위의 수의 최솟값 -> 일자가 아닌 3면중 가장 작은 합
        //      A E D, A D B, A B C, A C E
        //      F E D, F D B, F B C, F C E 조합 중 최솟값

        long n = Integer.parseInt(br.readLine());

        HashMap<Character, Integer> dice = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int temp = 'A';
        for (int i = 0; i < 6; i++) {
            dice.put((char) (temp + i), Integer.parseInt(st.nextToken()));
        }

        long minOneSide = dice.values().stream()
                .min(Comparator.comparingInt(integer -> integer))
                .orElse(0);

        if (n == 1) {
            long sum = 0;
            long max = 0;
            for (Integer value : dice.values()) {
                sum += value;
                max = Math.max(max, value);
            }
            sum -= max;
            System.out.println(sum);
            return;
        }

        long minTwoSide = Integer.MAX_VALUE;
        List<Character> keys = List.of('A', 'B', 'C', 'D', 'E', 'F');
        for (int i = 0; i < keys.size(); i++) {
            for (int j = i+1; j < keys.size(); j++) {
                if (
                        (keys.get(i).equals('A') && keys.get(j).equals('F'))
                        || (keys.get(i).equals('C') && keys.get(j).equals('D'))
                        || (keys.get(i).equals('B') && keys.get(j).equals('E'))
                ) continue;

                minTwoSide = Math.min(minTwoSide, dice.get(keys.get(i)) + dice.get(keys.get(j)));
            }
        }

        Character[][] threeSides = {
                {'A', 'E', 'D'}, {'A', 'D', 'B'}, {'A', 'B', 'C'}, {'A', 'C', 'E'}
                , {'F', 'E', 'D'}, {'F', 'D', 'B'}, {'F', 'B', 'C'}, {'F', 'C', 'E'}
        };

        long minThreeSide = Integer.MAX_VALUE;
        for (Character[] threeSide : threeSides) {
            minThreeSide = Math.min(minThreeSide, dice.get(threeSide[0]) + dice.get(threeSide[1]) + dice.get(threeSide[2]));
        }
        long result = calc(n, minOneSide, minTwoSide, minThreeSide);

        System.out.println(result);

        br.close();
    }

    public static long calc(long N, long face_1, long face_2, long face_3){
        long face_one = 4*(N-1)*(N-2) + (N-2)*(N-2);
        long face_two = 4*(N-2) + 4*(N-1);
        long face_three = 4;

        long sum = 0;
        sum = face_one*face_1 + face_two*face_2 + face_three*face_3;
        return sum;

    }
}


