package org.example.solutions;

// https://school.programmers.co.kr/learn/courses/30/lessons/62048
// 51 start


import java.util.*;

class Solution {
    public long solution(int w, int h) {
        long answer = 0;
        // 가로 w, 세로 h 직사각형 / 1*1 격자
        // 대각선 그었다.
        // 사용할 수 있는 사각형의 수는?

        // 0,0 | w,h
        // Y = (h/w) * X
        // 0,0 | 1,1

        // 좌대각 크다.
        double width = w;
        double height = h;
//        double slope = height / width;

        double prevY = 0;
        double nextY = 0;
        for (double i = 1; i < w+1; i++) {
            nextY = height * i / width;
            // 두수의 차 올림값 추가
            answer += Math.ceil(nextY) - Math.floor(prevY);

            prevY = nextY;

        }

        return (long)h * (long)w - answer;
    }


}