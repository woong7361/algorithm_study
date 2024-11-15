// https://leetcode.com/problems/longest-palindromic-substring/solutions/5276071/video-using-two-pointers-python-javascript-java-c/

import java.util.*;

class Solution {
    public String longestPalindrome(String s) {
        if (s.length() <= 0) {
            return "";
        }

        String result = s.substring(0, 1);

        // 홀수일 때 -> i+1, i-1 같아야함
        for (int i = 1; i < s.length()-1; i++) {

            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                left--;
                right++;
            }

            String temp = s.substring(left+1, right);

            if (result.length() < temp.length()) {
                result = temp;
            }
        }

        // 짝수일 때 우선 i, i+1 같아야하고 -> i-1 i+2 같아야함
        for (int i = 0; i < s.length()-1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                continue;
            }

            int left = i - 1;
            int right = i + 2;
            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                left--;
                right++;
            }

            String temp = s.substring(left+1, right);

            if (result.length() < temp.length()) {
                result = temp;
            }
        }

        return result;
    }
}
