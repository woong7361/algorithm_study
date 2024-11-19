// https://leetcode.com/problems/count-and-say/

import java.util.*;

class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String string = countAndSay(n-1);

        StringBuilder result = new StringBuilder();
        // 21 -> 1211
        for (int i = 0; i < string.length(); i++) {
            char start = string.charAt(i);
            int count = 1;

            // i가 다음과 같다면 ++
            while (i + 1 < string.length() && start == string.charAt(i + 1)) {

                count++;
                i++;
            }

            //i가 다음과 같지 않거나 끝 index -> i++ 해줄 필요 X
            result.append(count);
            result.append(start);
        }

        return result.toString();
    }
}
