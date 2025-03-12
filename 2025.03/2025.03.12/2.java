// https://school.programmers.co.kr/learn/courses/30/lessons/92335
// 25 start


import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        int answer = -1;

        // 피로도 시스템
        // 일정 피로도 이상을 사용해서 던전을 탐험 가능
        // 던점 탐험시 최소 피로도와 소모되는 소모 피로도가 존재
        // 최대한 많은 던전을 탐험하고 싶다. 어떻게 하면 되곘는가?


        // 정렬로 생각한다? -> X

        // 불가능하다고 생각됨
        // 던전은 8개 이하 -> 전탐색 하는게 맞다.


        // 해결방법
        // visit 추가
        // 던전 list 던저주고 시작하기
        // 최대 돌 수 있는 던전 수 반환

        boolean[] visit = new boolean[dungeons.length];
        Arrays.fill(visit, false);

        return adventureDungeon(dungeons, visit, k);
    }

    public int adventureDungeon(int[][] dungeons, boolean[] visit, int fatigue) {
        // 탐험
        int result = -1;
        for (int i = 0; i < dungeons.length; i++) {
            if (! visit[i] && fatigue >= dungeons[i][0]) {
                visit[i] = true;
                result = Math.max(result, 1 + adventureDungeon(dungeons, visit, fatigue - dungeons[i][1]));
                visit[i] = false;
            }
        }

        return result;
    }
}
