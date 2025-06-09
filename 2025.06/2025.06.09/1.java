import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        // 접두사 찾기
        // 어떤 번호가 다른 번호의 접두어인 경우가 있다면 true, 아니라면 false

        // 100만개
        // 반복문은 사용못함 너무 연산량이 많음
        // 해시테이블 쓰는게 좋을듯

        // 전화번호가 최대 20자
        // 전화번호를 길이에 따른 hashSet에 넣고 확인

        HashSet<String> phoneNumberSet = new HashSet<>();
        for (String phoneNumber : phone_book) {
            phoneNumberSet.add(phoneNumber);
        }

        for (int i = 0; i < phoneNumberSet.size(); i++) {
            String phoneNumber = phone_book[i];
            phoneNumberSet.remove(phoneNumber);

            for (int j = 0; j < phoneNumber.length()+1; j++) {
                if (phoneNumberSet.contains(phoneNumber.substring(0, j))) {
                    answer = false;
                    break;
                }
            }

            if (!answer) {
                break;
            }
            phoneNumberSet.add(phoneNumber);
        }

        return answer;
    }
}
