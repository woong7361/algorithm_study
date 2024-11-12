// https://leetcode.com/problems/generate-parentheses/solutions/

import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        dfs(0, 0, n, new StringBuilder(), result);

        return result;
    }

    private void dfs(int openCnt, int closeCnt, int endCnt, StringBuilder currentString, List<String> result) {
        if (openCnt > endCnt || closeCnt > endCnt) {
            return;
        }
        if (openCnt < closeCnt) {
            return;
        }

        if (openCnt == endCnt && closeCnt == endCnt) {
            result.add(currentString.toString());
            return;
        }

        // open add
        dfs(openCnt + 1, closeCnt, endCnt, currentString.append('('), result);
        currentString.deleteCharAt(currentString.length() - 1);

        // close add

        dfs(openCnt, closeCnt+1, endCnt, currentString.append(')'), result);
        currentString.deleteCharAt(currentString.length() - 1);

    }
}
