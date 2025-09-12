// https://www.acmicpc.net/problem/25329
// 03 start


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static double maxResult = -1;
    public static int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        // 통화 시간 학생이름 주어진다.
        // 통화 기본 시간 100분, 기본요금 10원
        // 추가 단위 시간 50분, 추가 요금 3원

        // 통화요금, 학생이름 순으로 정렬하라

        HashMap<String, CallFee> feeMap = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            String[] split = line.split(" ");

            Integer time = timeToMinutes(split[0]);
            String name = split[1];
            CallFee callFee = feeMap.getOrDefault(name, new CallFee(name, 0));

            callFee.addTime(time);

            feeMap.put(name, callFee);
        }

        List<CallFee> collect = feeMap.values()
                .stream()
                .sorted((callFee, t1) -> {
                    if (!callFee.getCallFee().equals(t1.getCallFee())) {
                        return -(callFee.getCallFee() - t1.getCallFee());
                    } else {
                        return callFee.getName().compareTo(t1.getName());
                    }
                })
                .collect(Collectors.toList());
        collect
                .forEach(t -> System.out.println(t.getName() + " " + t.getCallFee()));

        br.close();
    }

    private static Integer timeToMinutes(String timeString) {
        String[] split = timeString.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

    public static class CallFee {
        private String name;
        private Integer callTime;

        public CallFee(String name, Integer callTime) {
            this.name = name;
            this.callTime = callTime;
        }

        public Integer getCallFee() {
            if (callTime <= 100) return 10;
            return 10 + ((callTime-100) / 50 * 3) + ((callTime-100) % 50 > 0 ? 3 : 0);
        }

        public void addTime(Integer time) {
            this.callTime = this.callTime + time;
        }

        public String getName() {
            return this.name;
        }
    }

}


