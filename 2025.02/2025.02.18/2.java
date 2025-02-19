// https://school.programmers.co.kr/learn/courses/30/lessons/140107
// 39 start


class Solution {
    public long solution(int k, int d) {
        long answer = 0;

        // 양의 정수 k, d
        // 원점으로부터 x축 방향으로 a*k, y축으로 b*k떨어진 위치에 점을 찍는다 ( a, b 양의 정수)
        // 원점과 거리가 d를 넘는 위치에는 점을 안찍는다. (사분 원)
        // ex k = 2, d = 4 -> (0, 0), (0, 2), (0, 4), (2, 0), (2, 2), (4, 0)
        // 총 점이 몇개 찍히는지?

        //    |
         //   |
         //   |
        // ---------------------
        // 점을 찎는데
        // (0, n)부터 시작 n*k <= d
        // (k, 루트/n^2-k^2)   n^2 <= d


        // x축 기준으로 d보다 작은수라면 keep going
        // x = a*k 일때 y의 최대점을 찾는다. & y의 개수를 찾아서 answer 추가

        for (int x = 0; x <= d; x = x+k) {
            // x제곱 + y제곱 <= d제곱
            // -> d제곱 - x제곱 루트 씌우고 floor하면 최댓값 나오고
            // 그 수가 7 k가 4 -> 7/4 (몫) -> 1 -> 0,1 2개 -> answer += (몫+1)

            double maxY = Math.floor(Math.sqrt(Math.pow(d, 2) - Math.pow(x, 2)));
            long dotNumber = (long) (maxY / k) + 1L;

            answer += dotNumber;
        }


        return answer;
    }
}
