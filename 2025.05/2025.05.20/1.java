// https://school.programmers.co.kr/learn/courses/30/lessons/142085
// 18 start


import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        // 디펜스 게임
        // 병사 n명
        // 라운드마다 enemy[i] 적
        // 병사와 적의 1대1 교환비
        // 무적권 k번 가능
        // 최대한 많은 수의 라운드 진행하고파

        // 무적권? 어떻게 해야함

        // 해결법
        // 가장 큰거에 무적권 쓰면 되는거 아님?
        // 1. enemy를 더해가면서 n을 넘어갈때를 찾는다. ( 안넘어가면 끝 )
        // 2. 그 바로 전에서 최대값을 무적권을 사용한다.
        // 3. 다음 값을 추가해가며 또 다른 최대값을 찾는다.

        // ! 최댓값은 어떻게 할 것이냐 변수 1개는 안되는데 -> priority Queue 사용 MaxHeap

        int enemySum = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int index = 0;
        for (index = 0; index < enemy.length; index++) {
            // 넘어가나요?
            enemySum += enemy[index];
            queue.add(-enemy[index]);
            if (enemySum > n) {

                // 넘어갔을떄 무적권이 있나요?
                if (k > 0 && !queue.isEmpty()) {
                    int maxValue = -queue.poll();
                    enemySum -= maxValue;
                    k -= 1;
                } else {
                    index -= 1;
                    break;
                }
            }
        }

        index += 1;

        if (index > enemy.length) {
            return enemy.length;
        }
        return index;
    }
}
