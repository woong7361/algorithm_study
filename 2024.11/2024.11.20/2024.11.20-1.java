// https://school.programmers.co.kr/learn/courses/30/lessons/340213?language=java

import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        // 현재 오프닝 사이에 있다면 오프닝 끝으로 이동
        if (transToSecond(pos) >= transToSecond(op_start) && transToSecond(pos) <= transToSecond(op_end)) {
            pos = op_end;
        }

        for (String command : commands) {
            if (command.equals("next")) {
                pos = transToMinutes(transToSecond(pos) + 10);
            } else if (command.equals("prev")) {
                pos = transToMinutes(transToSecond(pos) - 10);
            }

            // video보다 length 보다 크면 length
            if (transToSecond(pos) > transToSecond(video_len)) {
                pos = video_len;
            }
            // 0보다 작으면 0
            if (transToSecond(pos) < 0) {
                pos = "00:00";
            }

            // 현재 오프닝 사이에 있다면 오프닝 끝으로 이동
            if (transToSecond(pos) >= transToSecond(op_start) && transToSecond(pos) <= transToSecond(op_end)) {
                pos = op_end;
            }
        }

        return pos;
    }


    public int transToSecond(String current){
        String[] split = current.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

    public String transToMinutes(int seconds){
        int minute = seconds / 60;
        int second = seconds % 60;

        return String.format("%02d:%02d", minute, second);
    }
}
