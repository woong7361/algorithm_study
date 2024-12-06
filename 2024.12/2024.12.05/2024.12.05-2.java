// https://school.programmers.co.kr/learn/courses/30/lessons/258712


import java.util.HashMap;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        // 선물을 누가 많이 받을지 예측
        // 더 많이 선물 준사람이 선물 하나 받음 - 같다면?
        // A == B or A = B = 0 -> 선물지수가 큰사람이 작은사람에게 선물을 하나 받는다.
        // 선물지수 = 선물은 준 수 - 선물을 받은 수
        // 선물지수 또한 같다면 선물을 주고받지 않는다.
        // 선물을 가장 많이 받는 친구가 받을 선물의 개수는?


        // 누가 선물을 더 많이 주었나 판별해야함
            // 선물 map 작성 ex. muzi frodo : 3
        HashMap<String, Integer> giftCountMap = new HashMap<>();
        HashMap<String, Integer> giftScoreMap = new HashMap<>();
        for (String gift : gifts) {
            giftCountMap.put(gift, giftCountMap.getOrDefault(gift, 0) + 1);

            // 선물 지수를 판별해야함
                // 선물 map 작성하면서 받으면 -1, 주면 +1
            String[] split = gift.split(" ");
            String sender = split[0];
            String receiver = split[1];

            giftScoreMap.put(sender, giftScoreMap.getOrDefault(sender, 0) + 1);
            giftScoreMap.put(receiver, giftScoreMap.getOrDefault(receiver, 0) - 1);
        }

        // 각 friends가 다른 friends를 확인하면서 선물을 줘야하는지 확인 -> 받는건 중복
        HashMap<String, Integer> resultMap = new HashMap<>();
        for (String friend : friends) {
            for (String targetFriend : friends) {
                if (friend.equals(targetFriend)) {
                    continue;
                }
                // 선물을 줘야하는지 확인하고, 주어야한다면 resultMap +1

                if (giftCountMap.getOrDefault(friend + " " + targetFriend, 0) > giftCountMap.getOrDefault(targetFriend + " " + friend, 0)) {
                    // 내가 준게 더 많다면 받아야지
                    continue;
                } else if (giftCountMap.getOrDefault(friend + " " + targetFriend, 0) < giftCountMap.getOrDefault(targetFriend + " " + friend, 0)) {
                    // 내가 준게 더 적다면 줘야지
                    resultMap.put(targetFriend, resultMap.getOrDefault(targetFriend, 0) + 1);
                } else {
                    // 준게 같거나 없다면
                    if (giftScoreMap.getOrDefault(friend, 0) < giftScoreMap.getOrDefault(targetFriend, 0)) {
                        // score가 내가 작다면 줘야지
                        resultMap.put(targetFriend, resultMap.getOrDefault(targetFriend, 0) + 1);
                    }
                }
            }
        }

        for (Integer value : resultMap.values()) {
            answer = Math.max(answer, value);
        }

        return answer;
    }
}
