// https://school.programmers.co.kr/learn/courses/30/lessons/150369

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        // 택배를 배달
        // n개의 집에 배달한다.
        // 모두 크기가 같은 재활용 택배 상자에 담아 배달
        // 배달 & 수거
        // 트럭에는 cap만큼 상자를 싫을 수 있고, 최소 이동거리는? 얼마인가

        // deliveries -> (배달/수거)
        // 최소 이동거리를 이동하려면 배달 다하고 수거 하면 좋음
        // 1. 마지막 수거 이후에 배달이 많을때
            // 가장 마지막부터 배달 하고 수거
        // 2. 수거 이후에 배달이 애매할때
            // 일단 수거는 다 해야하니까 수거되기 전까지 배달 남은걸 앞에 배달하기

        // ex. if cap = 3
        // 1/1 2/1 1/2

        // 4/0 0/0 1/4
        // 0/3 0/0 3/0

        // 2/2 0/1  5/0

        // 배달 / 픽업
        // 픽업 지워나가 -> 배달도 지워나가 -> 배달 0됐어 -> 픽업 0됐어
        // 픽업이 먼저 0이되면 상관 X -> 앞에 배달하고 끝까지 가서 픽업 하면 되니까
        // 배달이 먼저 0이 되면? -> 뒤에 다 배달하고 나머지 픽업하면 됨
        //

        // ! 배달을 완료 했을때 수거를 다 할 수 있어야함
        // 수거가 중심이다! 수거를 완료할 수 있는 집은 뒤에서 부터 더해서 찾는다. -> 찾았다.
        // 수거집부터 이후까지 배달이 충분한가? -> 뒤에서부터 배달
        // 아니다! -> 부족한 부분은 기점 집 이전에 배달 완료 후 수거
        int pickupReverseIndex = pickups.length-1;
        int deliverReverseIndex = deliveries.length-1;

        for (; pickupReverseIndex >= 0; pickupReverseIndex--) {
            if (pickups[pickupReverseIndex] != 0) {
                break;
            }
        }
        for (; deliverReverseIndex >= 0; deliverReverseIndex--) {
            if (deliveries[deliverReverseIndex] != 0) {
                break;
            }
        }

        while (pickupReverseIndex >= 0 || deliverReverseIndex >= 0) {
            answer = answer + (Long.max(pickupReverseIndex, deliverReverseIndex) + 1L) * 2L;

            int pickupCount = cap;
            int deliverCount = cap;

            for (; pickupReverseIndex >= 0 ; pickupReverseIndex--) {
                if (pickups[pickupReverseIndex] <= pickupCount) {
                    pickupCount -= pickups[pickupReverseIndex];
                    pickups[pickupReverseIndex] = 0;
                } else {
                    pickups[pickupReverseIndex] -= pickupCount;
                    pickupCount = 0;
                }

                if (pickupCount == 0 && pickups[pickupReverseIndex] != 0) {
                    break;
                }
            }

            for (; deliverReverseIndex >= 0 ; deliverReverseIndex--) {
                if (deliveries[deliverReverseIndex] <= deliverCount) {
                    deliverCount -= deliveries[deliverReverseIndex];
                    deliveries[deliverReverseIndex] = 0;
                } else {
                    deliveries[deliverReverseIndex] -= deliverCount;
                    deliverCount = 0;
                }

                if (deliverCount == 0 && deliveries[deliverReverseIndex] != 0) {
                    break;
                }
            }
        }

        return answer;
    }
}
