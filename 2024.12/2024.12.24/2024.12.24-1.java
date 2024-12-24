// https://school.programmers.co.kr/learn/courses/30/lessons/250121?language=java


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int[][] answer;

        // code, data, maximum, remain
        // ext -> one of these
        // val_ext -> less than
        // sort by -> one of these ,ASC sort

        // ext & val ext -> filtering function 4
        List<int[]> dataList;
        if (ext.equals("code")){
            dataList = Arrays.stream(data)
                    .filter(d -> d[0] < val_ext)
                    .collect(Collectors.toList());
        } else if (ext.equals("date")) {
            dataList = Arrays.stream(data)
                    .filter(d -> d[1] < val_ext)
                    .collect(Collectors.toList());
        } else if (ext.equals("maximum")) {
            dataList = Arrays.stream(data)
                    .filter(d -> d[2] < val_ext)
                    .collect(Collectors.toList());
        } else if (ext.equals("remain")) {
            dataList = Arrays.stream(data)
                    .filter(d -> d[3] < val_ext)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("not match ext");
        }

        // sort_by -> sorting function 4
        if (ext.equals("code")){
            dataList
                    .sort((source, target) -> Integer.compare(source[0], target[0]));
        } else if (ext.equals("date")) {
            dataList
                    .sort((source, target) -> Integer.compare(source[1], target[1]));
        } else if (ext.equals("maximum")) {
            dataList
                    .sort((source, target) -> Integer.compare(source[2], target[2]));
        } else if (ext.equals("remain")) {
            dataList
                    .sort((source, target) -> Integer.compare(source[3], target[3]));
        } else {
            throw new IllegalArgumentException("not match sort_element");
        }

        answer = dataList.toArray(new int[dataList.size()][]);

        return answer;
    }

}