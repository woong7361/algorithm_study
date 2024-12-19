package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/250125


import java.util.*;

class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;

        // board 에서 h,w color 저장
        String color = board[h][w];
        int maxH = board.length-1;
        int maxW = board[0].length-1;

        // 상하좌우 살펴보며 color 비교 1,0 -1,0 0,-1 0,1
        int[][] dhws = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        for (int[] dxy : dhws) {
            // next dhw
            int nextH = dxy[0] + h;
            int nextW = dxy[1] + w;

            // range check
            if (nextH < 0 || nextH > maxH || nextW < 0 || nextW > maxW) {
                continue;
            }

            // color check
            if (board[nextH][nextW].equals(color)) {
                continue;
            }
            System.out.println("nextH, nextW = " + nextH + " " + nextW);
            // result ++
            answer++;
        }

        return answer;
    }
}