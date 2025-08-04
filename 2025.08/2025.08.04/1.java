// https://school.programmers.co.kr/learn/courses/30/lessons/258705
// 57 start


class Solution {
    public int solution(int n, int[] tops) {
        int answer = 0;

        // 문제 설계
        // 예상대로 dfs 시간초과 -> dp로 풀이 변경
        // 세로 마름모가 관건
        // n[1] = 1,
        // n[2] = (세로 가능할 때) 뒤가 없다면 -> 가로 마름모, 세로 마름모, 없다 (3)가지 가능
        // n[3] = (세로 불가능) 뒤가 없다면  -> 가로 마름모, 없다 (2) 가지 가능

        // (세로 불가능일 때) n[x] = n[x-1] + n[x-2]
        // (세로 가능일 때) n[x] = n[x-1] + n[x-2] + if(top[x/2]==1) ? n[x-1] : 0

        int[] dp = new int[2 * n + 1];
        dp[0] = 1;
        dp[1] = tops[0] == 1 ? 3 : 2;


        for (int i = 2; i < 2 * n + 1; i++) {
            if (i % 2 == 0) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
            } else {
                dp[i] = (dp[i - 1] + dp[i - 2]
                        + (tops[i / 2] == 1 ? dp[i - 1] : 0)) % 10007;
            }
        }

        return dp[2 * n];
    }
}

//class Solution {
//    public int solution(int n, int[] tops) {
//        int answer = 0;
//
//        // 한변의 길이가 1인 정삼각형 2n+1개를 이어붙여 윗변의 길이가 n, 아랫변의 길이가 n+1인 사다리꼴을 만들었다.
//        // 그 윗변에 길이가 1인 정삼각형 x개를 붙였다.
//        // 이렇게 만든 모형을 정삼각형과, 정삼각형 2개를 이어붙인 마름모로 모두 채우려한다. 모든 경우의 수를 구하여라!\
//
//        // 제한 사항
//        // n <= 100000 -> dfs를 사용해도 되는가?
//
//
//        // 문제 설계
//        // 사다리꼴의 모양 3가지 존재 -> 사다리꼴만 채우면 나머지는 삼각형이라 생각해도된다.
//        // 각 사각형을 돌면서 사다리꼴을 채우면서 총 가짓수를 센다. -> dfs() 시간초과날 가능성이 높을듯하긴함
//        // visit[2n+1]
//        // 2n 일 때 2n,2n+1이 비어있다면 사다리꼴 or X
//        // 2n+1 일 때 2n+1,2n+2가 비어있다면 사다리꼴 or X & 2n+1이 비어있고, tops[n/2]==1 이라면 사다리꼴 or X
//
//        boolean[] fill = new boolean[2 * n + 1];
//        Arrays.fill(fill, false);
//
//        answer = countFillOut(0, fill, tops, n);
//
//        return answer;
//    }
//
//    private int countFillOut(int location, boolean[] fill, int[] tops, int n) {
//        // 마지막에 도착하면 끝
//        if (location == 2 * n) {
//            return 1;
//        }
//
//        int result = 0;
//
//        // 삼각형으로 진행
//        result += countFillOut(location + 1, fill, tops, n);
//
//        // 가능하다면 가로 마름모로 진행
//        if (!fill[location] && !fill[location + 1]) {
//            fill[location] = true;
//            fill[location + 1] = true;
//
//            result += countFillOut(location + 1, fill, tops, n);
//
//            fill[location] = false;
//            fill[location + 1] = false;
//        }
//
//        // 홀수고 가능하다면 세로 마름모로 진행
//        if (location % 2 == 1) {
//            if (!fill[location] && tops[location / 2] == 1) {
//                fill[location] = true;
//
//                result += countFillOut(location + 1, fill, tops, n);
//
//                fill[location] = false;
//            }
//        }
//
//        return result % 10007;
//    }
//}
