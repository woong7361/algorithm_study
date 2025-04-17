// https://school.programmers.co.kr/learn/courses/30/lessons/72411
// 49 start


import java.util.*;

class Solution {
    HashMap<String, Integer> courseCandidate = new HashMap<>();;
    public String[] solution(String[] orders, int[] course) {
        // 스카피
        // 가장 많이 함꼐 주문한 메뉴들을 조합하여 코스요리 구성
        // 코스요리는 최소 2가지 이상의 단품메뉴로 구성
        // 최소 2명 이상의 손님으로부터 주문된 단품 메뉴 조합에 대해서만 메뉴 후보에 등록

        // orders 배열 2이상 10이하
        // AB AC AD ... Map에 추가 course에 담긴 숫자만큼 ...

        courseCandidate = new HashMap<>();
        for (String order : orders) {
            char[] menus = order.toCharArray();
            Arrays.sort(menus);
            for (int i : course) {
                findCourseCandidate(menus, new StringBuilder(), i, 0);
            }
        }

        ArrayList<String> result = new ArrayList<>();
        for (int i : course) {
            OptionalInt max = courseCandidate.keySet().stream()
                    .filter(key -> key.length() == i)
                    .filter(key -> courseCandidate.get(key) > 1)
                    .mapToInt(key -> courseCandidate.get(key))
                    .max();

            if (max.isPresent()) {
                courseCandidate.keySet().stream()
                        .filter(key -> key.length() == i)
                        .filter(key -> courseCandidate.get(key) == max.getAsInt())
                        .forEach(key -> result.add(key));
            }
        }
        Collections.sort(result);

        return result.toArray(i -> new String[i]);
    }

    private void findCourseCandidate(char[] order, StringBuilder course, int courseCount, int index) {
        if (course.length() >= courseCount) {
            courseCandidate.put(
                    course.toString(),
                    courseCandidate.getOrDefault(course.toString(), 0) + 1
            );
            return;
        }

        for (int i = index; i < order.length; i++) {
            course.append(order[i]);
            findCourseCandidate(order, course, courseCount, i+1);
            course.deleteCharAt(course.length() - 1);
        }
    }
}
