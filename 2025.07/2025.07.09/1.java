// https://school.programmers.co.kr/learn/courses/30/lessons/214288
// 40 start


import java.util.*;

class Solution {
    public int solution(int k, int n, int[][] reqs) {
        int answer = 0;
        // 1:1 상담
        // 멘토 n명, 1~k번으로 분류되는 상담 유형
        // 각 멘토는 k개의 상담 유형중 하나만 담당 가능 -> 자신의 유형만 상담 가능
        // 참가자가 상담을 요청했을 때 부터 상담을 시작하기까지 기다린 시간의 합이 최소가 되도록 상담 유형별로 멘토 인원 정하기
        // 단, 각 유형별로 멘토 인원이 적어도 한 명 이상이어야 합니다.
        // 멘토 인원을 적절히 배정했을 때 참가자들이 상담을 받기까지 기다린 시간을 모두 합한 값의 최솟값을 return 하도록 solution 함수를 완성해 주세요.

        //참가자 번호	시각	   상담 시간	상담 유형
        //1번 참가자	10분	60분	1번 유형
        //2번 참가자	15분	100분	3번 유형
        //3번 참가자	20분	30분	1번 유형
        //4번 참가자	30분	50분	3번 유형
        //5번 참가자	50분	40분	1번 유형
        //6번 참가자	60분	30분	2번 유형
        //7번 참가자	65분	30분	1번 유형
        //8번 참가자	70분	100분	2번 유형

        // reqs의 원소는 [a, b, c] 형태의 길이가 3인 정수 배열이며, c번 유형의 상담을 원하는 참가자가 a분에 b분 동안의 상담을 요청했음을 의미합니다.
        // reqs는 a를 기준으로 오름차순으로 정렬
        // 1 ≤ k ≤ 5
        // k ≤ n ≤ 20

        // 문제 설계
        // 전 탐색? 가능하긴 할듯? - 최소 1명이니 1명씩 두고, permutation 돌리는 식으로 but. 너무 많은 시간이 걸린다.
        // ! 대기시간이 가장 많이 줄어드는 곳으로 사람을 배치하는것이 좋다.
        // 각 유형별로 1~(n-k+1) 배치의 대기시간을 계산 후 가장 시간이 많이 줄어드는 곳부터 멘토 투입 -> 계산

        // 문제 해결
        // 각 유형별로 상담인원 나누기 hashMap<Dequeue<...>>
        // 1~n-k+1 의 멘토가 있을 때 대기시간 계산
        // 어떻게 계산함? idle 멘토 수, 각 멘토가 끝나는 time, 투입 시간, -> idle 하지 않을 때 <대기> -> idle 변환 후 투입 (PriorityQueue 사용하면 좋을듯)
        // 각 유형당 멘토 1명식 배정 후 가장 대기시간이 많이 줄어드는 곳으로 멘토 투입

        HashMap<Integer, ArrayList<Counsel>> counselMap = new HashMap<>();

        for (int[] req : reqs) {
            int type = req[2] - 1;
            if (! counselMap.containsKey(type)) {
                counselMap.put(type, new ArrayList<>());
            }
            counselMap.get(type).add(new Counsel(req[0], req[1]));
        }

        int[] mentorAllocation = new int[k];
        Arrays.fill(mentorAllocation, 1);

        int[][] mentorPerDelayTime = new int[k][n - k + 3];

//         type 별로 계산 0~k-1
        for (int type = 0; type < k; type++) {
            for (int mentorNum = 1; mentorNum < n - k + 3; mentorNum++) {
                int delayTime = 0;

                PriorityQueue<Integer> counselEndTimeQueue = new PriorityQueue<>();

                ArrayList<Counsel> counsels = counselMap.getOrDefault(type, new ArrayList<>());
                for (Counsel counsel : counsels) {
                    // 새로 들어온 startTime = 30, 기존에 que에 있는 endTime 10 20 30 40 -> 10 20 30 빼기
                    while (! counselEndTimeQueue.isEmpty() && counselEndTimeQueue.peek() < counsel.startTime) {
                        counselEndTimeQueue.poll();
                    }

                    if (mentorNum == counselEndTimeQueue.size()) {
                        Integer prevEndTime = counselEndTimeQueue.poll();
                        delayTime += (prevEndTime - counsel.startTime);
                        counselEndTimeQueue.add(prevEndTime + counsel.counselTime);
                    } else {
                        counselEndTimeQueue.add(counsel.getEndTime());
                    }

                }


                mentorPerDelayTime[type][mentorNum] = delayTime;
            }
        }

        // 1명씩 투입
        for (int i = k; i < n; i++) {
            int minType = 0;

            // 줄어드는게 가장 큰 type을 찾기
            for (int type = 1; type < k; type++) {
                int minShrinkDelay = mentorPerDelayTime[minType][mentorAllocation[minType]] - mentorPerDelayTime[minType][mentorAllocation[minType] + 1];
                int shrinkDelay = mentorPerDelayTime[type][mentorAllocation[type]] - mentorPerDelayTime[type][mentorAllocation[type] + 1];

                if (shrinkDelay > minShrinkDelay) {
                    minType = type;
                }
            }

            mentorAllocation[minType] += 1;
        }

        for (int type = 0; type < k; type++) {
            answer += mentorPerDelayTime[type][mentorAllocation[type]];
        }

        return answer;
    }

    private static class Counsel {
        int startTime;
        int counselTime;

        public Counsel(int startTime, int counselTime) {
            this.startTime = startTime;
            this.counselTime = counselTime;
        }

        public int getEndTime() {
            return this.startTime + this.counselTime;
        }
    }
}
