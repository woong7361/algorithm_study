// https://school.programmers.co.kr/learn/courses/30/lessons/131128

// 23 start

class Solution {
    public String solution(String X, String Y) {
        String answer = "";

        // 두 정수 X, Y 0 <= k <= 9

        // 임의의 자리에서 공통으로 나타나는 수의 내림차순
        // 없다면 -1
        // 자릿수는 상관 없음

        // x, y 의 숫자 count

        // 중복된 숫자 큰수부터 Building

        int[] xNumCount = new int[10];
        int[] yNumCount = new int[10];

        for (int i = 0; i < X.length(); i++) {
            int num = Character.getNumericValue(X.charAt(i));
            xNumCount[num] += 1;
        }

        for (int i = 0; i < Y.length(); i++) {
            int num = Character.getNumericValue(Y.charAt(i));
            yNumCount[num] += 1;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            int minCount = Math.min(xNumCount[i], yNumCount[i]);

            if (minCount != 0 && i == 0 && builder.length() == 0) {
                builder.append(i);
            } else if (minCount != 0) {
                for (int j = 0; j < minCount; j++) {
                    builder.append(i);
                }
            }
        }

        if (builder.length() == 0) {
            answer = "-1";
        } else {
            answer = builder.toString();
        }

        return answer;
    }
}
