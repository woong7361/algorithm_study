package org.example.solutions;

// Leet code 14 | Longest Common Prefix | https://leetcode.com/problems/longest-common-prefix/description/
import java.util.*;

class Solution {
    public String longestCommonPrefix(String[] strs) {

        Integer minLength = Arrays.stream(strs)
                .map(str -> str.length())
                .mapToInt(str -> str)
                .min()
                .getAsInt();


        System.out.println("minLength = " + minLength);
        StringBuilder result = new StringBuilder();
        char comparable = '!';
        for(int i = 0; i < minLength; i++) {
            for (String str : strs) {
                if (comparable == '!') {
                    comparable = str.charAt(i);
                }

                if (comparable != str.charAt(i)) {
                    comparable = '!';
                    break;
                }
            }

            if (comparable == '!') {
                break;
            } else {
                result.append(String.valueOf(comparable));
                comparable = '!';
            }

        }

        return result.toString();
    }
}












