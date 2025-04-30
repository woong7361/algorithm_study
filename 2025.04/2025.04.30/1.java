import java.util.*;

class Solution {
    public long solution(String expression) {
        long answer = -1;
        //  숫자들과 3가지의 연산문자만으로 이루어진 수식 (+ - *)
        // 연산자의 우선순위를 재정의하여 만들 수 있는 가장 큰 수를 제출
        // 계산된 결과가 음수라면 절댓값으로 반환하여 제출
        // 가장 큰수가 우승
        // 피연산자가 음수이지 않다.
        // 피연산자는 1000이하이다.

        // 결론 6가지 모두 시행하여 비교

        // ex. 100-200*300-500+20
        List<String> operations = List.of("*", "+", "-");

        ArrayList<String> expressionList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (operations.contains(String.valueOf(c))) {
                if (builder.length() > 0) {
                    expressionList.add(builder.toString());
                    builder = new StringBuilder();
                }

                expressionList.add(String.valueOf(c));
            } else {
                builder.append(c);
            }
        }
        expressionList.add(builder.toString());

        // 어떻게함? ...
        // operations 3개 combination으로 6가지 방법 모두 시도
        // 홀수번째 operand가
        // 만약 홀수번째 operand가 해당한다면 i-1, i+1 연산 진행 후 result에 추가
        // 홀수번째가 operand가 아니라면 그냥 i-1, i result에 추가
        // 마지막이 남는다면 그냥 추가

        List<List<String>> all = List.of(
                List.of("*", "+", "-"),
                List.of("*", "-", "+"),
                List.of("+", "*", "-"),
                List.of("+", "-", "*"),
                List.of("-", "*", "+"),
                List.of("-", "+", "*")
        );

        for (List<String> strings : all) {
            List<String> result = expressionList;
            for (String operation : strings) {
                result = makeExpression(operation, result);
            }
            answer = Math.max(answer, Math.abs(Long.parseLong(result.get(0))));
        }

        return answer;
    }


    public List<String> makeExpression(String operand, List<String> expressions) {
        ArrayList<String> result = new ArrayList<>();

        String prev = expressions.get(0);
        for (int i = 1; i < expressions.size(); i+=2) {
            if (expressions.get(i).equals(operand)) {
                prev = oper(prev, expressions.get(i), expressions.get(i+1));
            } else {
                result.add(prev);
                result.add(expressions.get(i));
                prev = expressions.get(i + 1);
            }
        }
        result.add(prev);

        return result;
    }

    public String oper(String prev, String operand, String next) {
        if (operand.equals("*")) {
            return String.valueOf(Long.parseLong(prev) * Long.parseLong(next));
        } else if (operand.equals("+")) {
            return String.valueOf(Long.parseLong(prev) + Long.parseLong(next));
        } else if (operand.equals("-")) {
            return String.valueOf(Long.parseLong(prev) - Long.parseLong(next));
        } else {
            throw new IllegalArgumentException("!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
}
