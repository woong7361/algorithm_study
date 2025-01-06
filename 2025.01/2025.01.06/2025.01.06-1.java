// https://school.programmers.co.kr/learn/courses/30/lessons/178870


class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {0, 1000001};
        // 합이 k인 부분수열

        // 왼쪽 p1, 오른쪽 p2
        // p1 ~ p2의 합 sum1
        // sum1 < k p2 한칸 오른쪽
        // sum1 == k 기록
        // sum1 > k p1 오른쪽
        // 만약 원소 하나가 k보다 크다면 그냥 끝내기 (최적화용)
        int length = sequence.length;

        int p1 = 0;
        int p2 = 0;
        int sumSeq = sequence[0];

        // p2가 마지막일때 까지
        while (p2 < length){
            // p2++
            if (sequence[p2] > k) {
                break;
            }

            sumSeq += sequence[p2];

            //sumSeq > k 라면 p1 조정
            while (sumSeq > k) {
                sumSeq -= sequence[p1];
                p1 += 1;
            }

            // sumSeq == k인지 확인 하고 answer 처리
            if (sumSeq == k) {
                if (p2 - p1 < answer[1] - answer[0]) {
                    answer[0] = p1;
                    answer[1] = p2;
                }
            }

            p2 += 1;
        }

        return answer;
    }
}
