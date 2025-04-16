// https://school.programmers.co.kr/learn/courses/30/lessons/72412
// 43 start


import java.util.*;

class Solution {
    static HashMap<String, List<Integer>> map;

    public int[] solution(String[] info, String[] query) {
        // cpp, java, python 중 하나를 선택
        // backend와 frontend 중 하나를 선택
        // junior와 senior 중 하나를 선택
        // 소울푸드로 chicken과 pizza 중 하나를 선택
        // 코딩테스트 점수 1~100,000점

        // 조건을 만족하는 사람중 코딩테스트 점수를 X점 이상 받은 사람은 모두 몇명인가?

        // info 예시
        // "java and backend and junior and pizza 100"

        // javabackendjuniorpizza & 100 으로 구별
        int[] answer = new int[query.length];
        map = new HashMap<String, List<Integer>>();

        for (String string : info) {
            String[] p = string.split(" ");
            makeSentence(p, "", 0);
        }

        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll(" and ", "");
            String[] q = query[i].split(" ");
            answer[i] = map.containsKey(q[0]) ? binarySearch(q[0], Integer.parseInt(q[1])) : 0;
        }

        return answer;
    }

    private static int binarySearch(String key, int score) {
        List<Integer> list = map.get(key);
        int start = 0, end = list.size() - 1;

        // start ... key ... end
        // start ~ key-1 | key+1 ~ end

        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < score)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return list.size() - start;
    }


    private static void makeSentence(String[] p, String str, int cnt) {
        if (cnt == 4) {
            if (!map.containsKey(str)) {
                List<Integer> list = new ArrayList<Integer>();
                map.put(str, list);
            }
            map.get(str).add(Integer.parseInt(p[4]));
            return;
        }
        makeSentence(p, str + "-", cnt + 1);
        makeSentence(p, str + p[cnt], cnt + 1);
    }

}
