// https://school.programmers.co.kr/learn/courses/30/lessons/340198

import java.util.*;
import java.util.stream.Collectors;


class Solutions {
    public int solution(int[] mats, String[][] park) {
        int answer = 0;

        Arrays.sort(mats);
        int maxLength = mats[mats.length - 1];

        // 정사각형 모양 돗자리
        // 깔수있는 가장 큰 돗자리

        // park 확인 -> 이미 자리있다면 넘어가기
        // 자리 없다면? check 해야지 어떻게?  그냥? full 연산?
        for (int row = 0; row < park.length; row++) {
            for (int col = 0; col < park[0].length; col++) {
                if (park[row][col].equals("-1")) {

                    //check max mats
                    // 1일때 2일때 3일때 ... maxLength-1 일때까지 한줄씩 확인하기
                    // r,c   r+1,c   r+2,c
                    // r,c+1 r+1,c+1 r+2,c+1
                    // r,c+2 r+1,c+2 r+2,c+2
                    for (int i = 0; i < maxLength; i++) {
                        boolean flag = false;
                        // row
                        for (int j = 0; j <= i; j++) {
                            if (!(row + i < park.length && col + j < park[0].length && park[row + i][col + j].equals("-1"))) {
                                flag = true;
                                break;
                            }

                            if (!(row + j < park.length && col + i < park[0].length && park[row + j][col + i].equals("-1"))) {
                                flag = true;
                                break;
                            }
                        }

                        if (flag) {
                            break;
                        }

                        answer = Math.max(answer, i + 1);
                    }

                }
            }
        }

        // 오름차순 정렬이지
        // 1 3 5 , a=4
        int result = -1;
        for (int mat : mats) {
            if (mat <= answer) {
                result = mat;
            } else {
                break;
            }
        }

        return result;
    }
}
