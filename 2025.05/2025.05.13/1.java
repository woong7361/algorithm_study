// https://school.programmers.co.kr/learn/courses/30/lessons/150368

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    public int[] dfs(List<Emoticon> emoticons, int[] priceList, int[][] users, int index) {
        if (emoticons.size() == priceList.length) {
            return calculate(emoticons, users);
        }
        int[] result = new int[] {-1, -1};

        int[] discountPercents = {10, 20, 30, 40};
        for (int discount : discountPercents) {
            Emoticon e = new Emoticon(discount, priceList[index]);
            emoticons.add(e);
            int[] dfs = dfs(emoticons, priceList, users, index+1);

            if (dfs[0] > result[0]) {
                result = dfs;
            } else if (dfs[0] == result[0] && dfs[1] > result[1]) {
                result = dfs;
            }

            emoticons.remove(e);
        }

        return result;
    }

    private int[] calculate(List<Emoticon> emoticons, int[][] users) {
        int plusMember = 0;
        int totalCash = 0;

        for (int[] user : users) {
            int sumValue = emoticons.stream()
                    .mapToInt(emoticon -> emoticon.getCalculatedPrice(user))
                    .sum();

            if (sumValue >= user[1]) {
                plusMember += 1;
            } else {
                totalCash += sumValue;
            }
        }

        return new int[]{plusMember, totalCash};
    }

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};

        return dfs(new ArrayList<Emoticon>(), emoticons, users, 0);

//        return answer;
    }

    public static class Emoticon {
        private int discount;
        private int price;

        private int getDiscountPrice() {
            return (price/100) * (100 - discount);
        }

        public int getCalculatedPrice(int[] user) {
            // user1 이 30 이면 30 이상 할인하는 것 모두 구매
            if (user[0] > discount) {
                return 0;
            }

            return getDiscountPrice();
        }

        public Emoticon(int discount, int price) {
            this.discount = discount;
            this.price = price;
        }
    }

}
