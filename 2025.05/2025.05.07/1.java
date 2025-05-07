// https://school.programmers.co.kr/learn/courses/30/lessons/131127
// 04 start


import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        // 일정 금액지불하면 10일동한 회원 자격 부여
        // 회원을 상대로 매일 한가지의 할인상품 존재 - 하루에 하나씩 구매 가능
        // 자신이 원하는 제품과 수량이 할인하는 날짜와 10일 연속으로 일치할 경우에 맞추어서 회원가입


        // 해결 방법
        // want 와 current

        // map - want : number
        // discount 초기 10개 쭉쭉 추가
        // 10개 초과시 오른쪽 +1 왼쪽 -1 -> index로 하기
        // map의 value가 모두 0일때 끝

        HashMap<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        for (int i = 0; i < 10; i++) {
            if (wantMap.containsKey(discount[i])) {
                wantMap.put(discount[i], wantMap.get(discount[i]) - 1);
            }

            if (i == 9) {
                if (checkMember(wantMap)) {
                    answer += 1;
                }
            }
        }

        for (int i = 10; i < discount.length; i++) {
            if (wantMap.containsKey(discount[i])) {
                wantMap.put(discount[i], wantMap.get(discount[i]) - 1);
            }
            if (wantMap.containsKey(discount[i - 10])) {
                wantMap.put(discount[i-10], wantMap.get(discount[i-10]) + 1);
            }

            if (checkMember(wantMap)) {
                answer += 1;
            }
        }

        return answer;
    }

    private boolean checkMember(HashMap<String, Integer> wantMap) {
        for (Integer value : wantMap.values()) {
            if (!value.equals(0)) {
                return false;
            }
        }

        return true;
    }
}
