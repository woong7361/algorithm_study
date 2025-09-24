// https://www.acmicpc.net/problem/1062
// 36 start


import java.io.*;
import java.util.*;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 영어 글자중 K개만 알 수 있다.
        // N개의 단어가 주어진다.
            // 단어는 anta로 시작 tica로 끝나며, 8 ~ 15 자 이다.
            // 모든 단어는 소문자로 이루어져있다.
            // 모든 단어는 중복되지 않는다.
        // 읽을 수 있는 단어의 최대값은?
        // 주어지는 단어는 50개 이하다.
        // K는 26 이하이다.

        // 문제 설계
        // 주어진 단어를 알파벳 true(1) false(0)로 나눈다.
        // 내가 배운 alphabet masking array를 준비한다.
        // XOR 연산을 통해 0이 된다면 읽을 수 있는 단어다.

        // 문제 해결
        // 주어진 단어를 알파벳 appear array로 변환
        // antic -> 5개 알파벳 필수 -> 2^21번의 mask 생성 후 비교 => 약 200만번 이하

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] wordsAlphabetAppear = new int[N][26];
        for (int i = 0; i < N; i++) {
            for (char alphabet : br.readLine().toCharArray()) {
                wordsAlphabetAppear[i][alphabet - 'a'] =  1;
            }
        }

        int result = getMaxReadableWords(new int[26], wordsAlphabetAppear, K, 0);

        System.out.println(result);

        br.close();
    }


    public static int getMaxReadableWords(int[] mask, int[][] wordsAlphabetAppear, int maxLearnCount, int index) {
        // 더 적은 수를 배우면 return
        if (26 - index < maxLearnCount - sumArrayFor(mask)) {
            return 0;
        }

        // 더 많은 수를 배우면 return
        if (sumArrayFor(mask) > maxLearnCount) {
            return 0;
        }

        // z -> 25 까지 mask 작성을 했다면 비교 후 return
        // 배운 알파벳의 수가 K개 이어야한다.
        if (index == 26) {
            if(sumArrayFor(mask) == maxLearnCount) return getReadableWordCount(mask, wordsAlphabetAppear);
            else return 0;
        }

        // a n t i c 중 1개라면 무조건 1을 mask에 기입
        int result = 0;
        if (index == 'a' - 'a' || index == 'n' - 'a' || index == 't' - 'a' || index == 'i' - 'a' || index == 'c' - 'a') {
            mask[index] = 1;
            result = Math.max(result, getMaxReadableWords(mask, wordsAlphabetAppear, maxLearnCount, index + 1));
            mask[index] = 0;
        } else {
            // next index로 이동 | 0 or 1
            mask[index] = 1;
            result = Math.max(result, getMaxReadableWords(mask, wordsAlphabetAppear, maxLearnCount, index + 1));
            mask[index] = 0;

            result = Math.max(result, getMaxReadableWords(mask, wordsAlphabetAppear, maxLearnCount, index + 1));
        }

        // return시 max값 return
        return result;
    }

    public static int getReadableWordCount(int[] mask, int[][] wordsAlphabetAppear) {
        int result = 0;
        for (int[] wordAhphabets : wordsAlphabetAppear) {
            for (int i = 0; i < 26; i++) {
                // mask가 더 클때
                if (mask[i] == 0 && wordAhphabets[i] == 1) {
                    result--;
                    break;
                }
            }
            result++;
        }

        return result;
    }

    public static int sumArrayFor(int[] ints) {
        int result = 0;
        for (int t : ints) {
            result += t;
        }
        return result;
    }
}


