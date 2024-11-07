// Leet code 13 | roman to integer | https://leetcode.com/problems/roman-to-integer/description/
import java.util.*;


//Example 1:
//
//Input: s = "III"
//Output: 3
//Explanation: III = 3.
//Example 2:
//
//Input: s = "LVIII"
//Output: 58
//Explanation: L = 50, V= 5, III = 3.
//Example 3:
//
//Input: s = "MCMXCIV"
//Output: 1994
//Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

class Solution {
    public int romanToInt(String s) {

        int result = 0;

        HashMap<Character, Integer> romanMapper = new HashMap<>();
        romanMapper.put('I', 1);
        romanMapper.put('V', 5);
        romanMapper.put('X', 10);
        romanMapper.put('L', 50);
        romanMapper.put('C', 100);
        romanMapper.put('D', 500);
        romanMapper.put('M', 1000);

        int prev = 0;
        // 일단 더해 & 나보다 큰수가 나오면 빼 inverse 해서 더해
        for (int i = 0; i < s.length(); i++) {
            int current = romanMapper.get(s.charAt(i));

            if (prev == 0) {
                prev = current;
            } else if (current > prev) {
                result += current - prev;
                prev = 0;
            } else {
                result += prev;
                prev = current;
            }
        }

        return result+prev;
    }
}
