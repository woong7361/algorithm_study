// https://school.programmers.co.kr/learn/courses/30/lessons/250137


import java.util.HashMap;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;

        // t초 붕대 1초마다 x 회복, t초 연속 성공하면 y추가 체력 회복
        // 공격 당하면 취소, 끝나면 즉시 붕대 사용
        // 체력 0이면 사망, 풀이면 회복 X

        // bandaget 시전시간, 초당회복량, 추가 회복량,
        // attack 공격시간, 피해량

        // attacks가 비어있을 때까지 붕대감기 시행

        int time = 0;
        int attackIndex = 0;
        int consecutiveBandageTime = 0;
        int maxHealth = health;
        while (attackIndex < attacks.length) {
            time++;
            //공격이 있는지 확인
                // 있다면 체력 - , 연속시간 초기화
            if (attacks[attackIndex][0] == time) {
                health -= attacks[attackIndex][1];
                consecutiveBandageTime = 0;
                attackIndex += 1;
            }
             else {
                // 공격이 없다면
                // 체력 +x, 연속시간 +1
                health += bandage[1];
                consecutiveBandageTime += 1;
                if (consecutiveBandageTime == bandage[0]) {
                    // 연속시간이 달성되었다면? 체력 +y, 연속시간 초기화
                    health += bandage[2];
                    consecutiveBandageTime = 0;
                }
            }

             // 체력이 full이상이면 % 체력
            if (health > maxHealth) {
                health = maxHealth;
            }

            // 체력이 <=0 이면 return -1
            if (health <= 0) {
                health = -1;
                break;
            }

        }

        return health;
    }
}