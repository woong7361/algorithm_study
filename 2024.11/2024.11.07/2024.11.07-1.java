// Leet code 9 | palindrome Number | https://leetcode.com/problems/palindrome-number/

import java.util.*;

class Solution {
    public boolean isPalindrome(int x) {

        String str = String.valueOf(x);

        String[] reverse = new String[str.length()];
        for (int i = 0; i < str.length(); i++) {
            reverse[str.length()-1 - i] = String.valueOf(str.charAt(i));
        }
        String join = String.join("", reverse);

        System.out.println("join = " + join);

        return str.equals(join);
    }

}
