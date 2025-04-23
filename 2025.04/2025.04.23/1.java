// https://school.programmers.co.kr/learn/courses/30/lessons/161989

// 52분 start
class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;

        // n미터 벽 덧칠
        // 구역 n개 순서대로 1 ~ n
        // 롤러의 길이 m 미터
        // 칠하는 횟수 최소화

        // 그리디

        // 문제 풀이
        // section 의 최소 선택 -> 칠함 -> 어디까지? -> 다음 있는지 확인

        int lastRolling = -1;
        for (int i = 0; i < section.length; i++) {
            int start = section[i];
            if (start > lastRolling) {
                // 1번 칠해야함 & size=1 ->  last Rolling = 1
                lastRolling = start + m - 1;
                answer += 1;
            }
        }
        return answer;
    }


}
