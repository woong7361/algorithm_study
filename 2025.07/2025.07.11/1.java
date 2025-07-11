// https://school.programmers.co.kr/learn/courses/30/lessons/152995
// 35 start


import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        // 이진 트리를 수로
        // 이진수를 저장할 빈 문자열
        // 주어진 이진트리에 더미노드를 추가하여 포화 이진트리로, 루트 노드는 그대로 유지
        // 포화 이진트리를 가장 왼쪽노드부터 가장 오른쪽 노드까지 왼쪽에 있는 순서대로 살펴본다.
        // 살펴본 노드가 더미노드면 0, 더미노드가 아니면 1
        // 완성된 문자열을 10진수로 변환하여 반환한다.

        // 어떤 수가 주어졌을 때, 하나의 이진트리로 해당 수를 표현할 수 있는지 알고싶다.

        // 문제 설계 !
        // node가 끊어지지 않고 이어져야함 -> 1의 모든 개수 확인 (node의 개수) == root부터 시작하여 연결된 모든 node 확인
        // 포화 이진트리 총 node의 수 2^n-1 -> 0 padding 추가
        // ex. 58 = 0111010(2) ->

        // 순회는 어떻게 할것인가? 절반 나누기 -> root node == 1 진행 | root node == 0 버리기

        for (int i = 0; i < numbers.length; i++) {
            String binaryString = Long.toBinaryString(numbers[i]);
            binaryString = padding(binaryString);

            int nodeCount = binaryString.replaceAll("0", "").length();
            int linkNodeCount = getLinkNodeCount(binaryString, 0, binaryString.length() - 1);
//
//            System.out.println("binaryString = " + binaryString);
//            System.out.println("nodeCount = " + nodeCount + "    linkNodeCount = " + linkNodeCount + "\n");

            answer[i] = nodeCount == linkNodeCount ? 1 : 0;
        }

        return answer;
    }


    // 011 1 010
    // 0 1 1  |  0 1 0

    private int getLinkNodeCount(String binaryString, int left, int right) {
        int mid = (right + left) / 2;
        int rootValue = Character.getNumericValue(binaryString.charAt(mid));

        if (left == right) {
            return rootValue;
        }

        int result = rootValue;
        if (rootValue == 1) {
            result += getLinkNodeCount(binaryString, left, mid-1);
            result += getLinkNodeCount(binaryString, mid+1, right);
        }

        return result;
    }

    private String padding(String binaryString) {
        StringBuilder paddingString = new StringBuilder();

        long n = 0;
        while (binaryString.length() > Math.pow(2, n) - 1) {
            n++;
        }
        long paddingCount = (long) (Math.pow(2, n) - 1) - binaryString.length();

        for (int i = 0; i < paddingCount; i++) paddingString.append("0");
        paddingString.append(binaryString);

        return paddingString.toString();
    }
}
