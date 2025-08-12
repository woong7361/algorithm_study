
// https://www.acmicpc.net/problem/30805
// 10 start


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 어떤 수열이 다른 수열보다 사전 순으로 나중이라는 것은
        // 두 수열중 첫 번째 수가 큰 쪽은 사전 순으로 나중이다.
        // 같다면 다음 수를 비교한다.
        // 길이가 0인 수열과 다른 수열을 비교한다면 다른 수열이 나중이다.
        // 수열 A, B가 주어질때, 수열 A와 B가 공통으로 갖는 부분 수열들 중 사전 순으로 가장 나중인 것을 구하시오

        // 1 <= A의 길이 B의 길이 <= 100

        // 문제 설계
        // A는 n번
        // B는 mn번 시행하는 이중 for문으로 진행 (시간 가능)
        // A가 동일하다? (부분 수열 추가) B 이후로 진행 A 
        // A의 다음 수가 나보다 크다? 그러면 max 부분수열 대체 가능
        // 항상 부분수열과 비교하여 진행할지 말지 결정하기 


        ArrayList<List<Integer>> maxSubSequence = new ArrayList<>();

        int ALength = Integer.parseInt(br.readLine());
        String[] Aarray = br.readLine().split(" ");
        int[] A = new int[ALength];
        for (int i = 0; i < Aarray.length; i++) {
            A[i] = Integer.parseInt(Aarray[i]);
        }
        int BLength = Integer.parseInt(br.readLine());
        String[] Barray = br.readLine().split(" ");
        int[] B = new int[BLength];
        for (int i = 0; i < Barray.length; i++) {
            B[i] = Integer.parseInt(Barray[i]);
        }

        for (int i = 0; i < A.length; i++) {
            // 부분수열의 수가 들어갈 자리 loc
            int loc = maxSubSequence.size();

            while (loc > 0) {
                // 현재 수가 내가 들어갈 위치의 앞의 수보다 크다면 내가 거기 들어간다.
                if (A[i] > maxSubSequence.get(loc-1).get(0)) {
                    loc--;
                } else {
                    break;
                }
            }
//            System.out.println("A[i] = " + A[i] + " loc = " + loc);

            // 들어갈 위치 구함 loc
            for (int start = (loc == 0) ? 0 : (maxSubSequence.get(loc - 1).get(1) + 1); start < B.length; start++) {
                if (A[i] == B[start]) {
                    while (maxSubSequence.size() > loc) {
                        maxSubSequence.remove(maxSubSequence.size()-1);
                    }

                    maxSubSequence.add(List.of(A[i], start));
                    break;
                }
            }

//            System.out.println("maxSubSequence = " + maxSubSequence);
        }


        System.out.println(maxSubSequence.size());
        String[] array = maxSubSequence.stream()
                .map(it -> String.valueOf(it.get(0)))
                .toArray(String[]::new);

        String join = String.join(" ", array);
        System.out.println(join);

        br.close();
    }

}
