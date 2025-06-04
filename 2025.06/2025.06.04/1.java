// https://school.programmers.co.kr/learn/courses/30/lessons/42587
// 18 start


import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        // location에 해당하는 프로세스가 몇번째에 실행되는지 알아보기
        int[] priorityArray = new int[10];
        Arrays.fill(priorityArray, 0);

        LinkedList<Integer> processQueue = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            priorityArray[priorities[i]]++;
            if (i == location) {
                processQueue.add(-1);
            } else {
                processQueue.add(priorities[i]);
            }
        }

        // 현재 처리해야할 priority level
        int currentPriorityLevel = getCurrentPriorityLevel(priorityArray);
        while ( !processQueue.isEmpty()) {
            Integer process = processQueue.removeFirst();
            // if target 이라면
            // target이 처리해야한다면 처리 & break
            if (process == -1 && currentPriorityLevel == priorities[location]) {
                answer++;
                break;
            }

            // 일반 이라면 처리 or 넘기기
            // 처리했다면 우선순위 다시 check
            if (process == currentPriorityLevel) {
                priorityArray[currentPriorityLevel]--;
                currentPriorityLevel = getCurrentPriorityLevel(priorityArray);
                answer++;
            } else {
                processQueue.addLast(process);
            }
        }


        return answer;
    }

    private static int getCurrentPriorityLevel(int[] priorityArray) {
        int currentPriorityLevel = 0;
        for (int i = priorityArray.length-1; i > 0; i--) {
            if (priorityArray[i] > 0) {
                currentPriorityLevel = i;
                break;
            }
        }
        return currentPriorityLevel;
    }


}
