// https://leetcode.com/problems/count-and-say/

import java.util.*;

class Solution {
    public String countAndSay(int n) {
        if (n == 1)
            return "1";
        String s = countAndSay(n - 1); // har ek n apne pichhe wale ko dekhega
        // 2 dekhega ki 1 ek baar hai toh "11"output dega
        // fir 3 dekhega ki 2 wale me 2 baar 1 h toh "21" output dega
        // fir 4 dekhega ki 3 wale me 1 baar 2 aur 1 baar 1 hai toh "1211" output dega
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int count = 1;
            char ch = s.charAt(i);
            while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                count++;
                i++;
            }
            sb.append(count).append(ch);
        }
        return sb.toString();
    }
}
