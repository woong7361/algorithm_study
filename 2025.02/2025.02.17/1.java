// https://school.programmers.co.kr/learn/courses/30/lessons/148653
// 03 start


class Solution {
    public int solution(int storey) {
        int answer = 0;

        // 10의 배수로 만들기
        // <5 -가 빠름 5> +가 빠름 ==5 앞자리 숫자를 보고 +or-결정

        while (storey > 0) {
            int digit = storey % 10;
            storey /= 10;

            if (digit == 5) {
                if (storey % 10 >= 5) {
                    answer += (10 - digit);
                    storey++;
                } else {
                    answer += digit;
                }
            } else if (digit > 5) {
                answer += (10 - digit);
                storey++;
            }
            else{
                answer += digit;
            }
        }

        return answer;
    }
}
