// https://school.programmers.co.kr/learn/courses/30/lessons/12923
// 23 start


import java.util.*;

class Solution {
    public int[] solution(long begin, long end) {
        int beg = (int) begin;
        int en = (int) end;
        int[] answer = new int[en-beg+1];

        // 숫자 0이 적힌 블록이 설치된 도로에 다른 숫자가 적신 블록을 설치
        // 블록 번호가 n 일 때 가장 첫 블록은 n*2 번째 위치에 설치, 그 다음은 n*3, n*4, ...
        // 블록은 1이 적힌 블록부터 숫자를 1씩 증가시키며 순서대로 설치.
        // ex. '1' -> [2,3,4,5,...] , '2' -> [4,5,6,7,...]
        // 특정 구간에 어떤 블록이 깔렸는지 알고싶다.
        // 1 <= 길이 <= 1,000,000,000
        // 1 <= 최대 숫자 <= 10,000,000
        // 1 <= end-begin <= 5000

        // 설계
        // 모드 도로를 계산하는것? 말안된다. 너무 많다. begin ~ end 까지만 계산 해본다고 한다? 그래도 많을 수 있다.
        // 소수일 때 -> 1
        // 그게 아니라면? 약수중 가장 큰 수

        // 해결방법
        // int[end-begin] 생성 & fori문으로 begin ~ end 까지 진행
        // num을 2로 나눈 나머지가 1 이면 '1', 나머지가 0 이면 몫을 값으로 처리한다.

        for (int num = beg;  num <= en; num++) {
            int block = 1;

            for (int div = 2; div*div <= num; div++)
                if (num % div == 0 && num / div <= 10000000) {
                    block = num / div;
                    break;
                }

            answer[num - beg] = block;
        }

        if (begin == 1) {
            answer[0] = 0;
        }

        return answer;
    }
}
