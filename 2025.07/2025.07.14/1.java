// https://school.programmers.co.kr/learn/courses/30/lessons/150366?language=java
// 35 start


import java.util.*;

class Solution {
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();

        // 표편집 프로그램 50*50, 셀은 문자열값 & 병합 가능
        // "UPDATE r c value" -> (r, c) value로
        // "UPDATE value1 value2 -> value1 값을 가지고 있는 모든 셀을 value2로
        // "MERGE r1 c1 r2 c2" -> r1,c1 값으로 병합 (인접하지 않을 수 있음) (같은 셀일 경우 무시) (선택시 병합된 셀로 접근)
        // "UNMERGE r c" -> (r, c) 위치의 셀을 선택하여 해당 셀의 모든 병합을 해제, 병합되어 있던 모든 셀이 실행 초기 상태로
        // "PRINT r c" -> (r, c) 위치의 셀값을 출력 (비어있다면 EMPTY 값 출력)


        // 문제 설계
        // 병합이 excel의 병합이 아니다.
        // 표로 사용하는 것보단 Map으로 구분하는게 편할듯?

        // mergeMap?
        // map 만들면 class 정의해야 하니 array로 해결하자 -> but. array는 객체 못담아서 못함 -> cell 만들어야함, eqauls랑 hashcode도
        // value map?
        // merge된 value를 항상 바꾸어야하니까 귀찮음 -> pointer로 가지고 있어 하나만 바꾸어도 되도록

        HashMap<Cell, Set<Cell>> mergeMap = new HashMap<>();
        HashMap<Cell, String[]> valueMap = new HashMap<>();

        for (String command : commands) {
            if (command.startsWith("UPDATE")) {
                String[] split = command.split(" ");

                if (split.length == 4) {
                    Cell cell = new Cell(Integer.parseInt(split[1]), Integer.parseInt(split[2]));

                    String[] value = valueMap.getOrDefault(cell, new String[]{""});
                    value[0] = split[3];
                    valueMap.put(cell, value);

                }

                if (split.length == 3) {
                    String targetValue = split[1];
                    String updateValue = split[2];

                    for (Cell cell : valueMap.keySet()) {
                        String[] cellValue = valueMap.get(cell);
                        if (cellValue[0].equals(targetValue)) {
                            cellValue[0] = updateValue;
                        }
                    }
                }


            } else if (command.startsWith("MERGE")) {
                String[] split = command.split(" ");
                Cell origin = new Cell(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                Cell target = new Cell(Integer.parseInt(split[3]), Integer.parseInt(split[4]));

                Set<Cell> mergeCells = mergeMap.getOrDefault(origin, new HashSet<>(List.of(origin)));
                String[] value = valueMap.getOrDefault(origin, new String[]{""});

                if (mergeMap.containsKey(target)) {
                    // target 에 merge된 모든 값을 바꾸어야함
                    mergeCells.addAll(mergeMap.get(target));

                    for (Cell cell : mergeMap.get(target)) {
                        mergeMap.put(cell, mergeCells);
                        valueMap.put(cell, value);
                    }
                } else {
                    mergeCells.add(target);

                    mergeMap.put(target, mergeCells);
                    valueMap.put(target, value);
                }

                mergeMap.put(origin, mergeCells);
                valueMap.put(origin, value);
            } else if (command.startsWith("UNMERGE")) {
                String[] split = command.split(" ");
                Cell cell = new Cell(Integer.parseInt(split[1]), Integer.parseInt(split[2]));

                Set<Cell> mergeCells = mergeMap.getOrDefault(cell, new HashSet<>());

                for (Cell mergeCell : mergeCells) {
                    mergeMap.remove(mergeCell);

                    if (mergeCell.equals(cell)) {
                        continue;
                    }
                    valueMap.remove(mergeCell);
                }

            } else if (command.startsWith("PRINT")) {
                String[] split = command.split(" ");
                Cell cell = new Cell(Integer.parseInt(split[1]), Integer.parseInt(split[2]));

                String[] value = valueMap.getOrDefault(cell, new String[]{""});

                if (value[0].equals("")) {
                    answer.add("EMPTY");
                } else {
                    answer.add(value[0]);
                }
            } else {
                System.out.println("x");
                break;
            }
        }

        return answer.toArray(String[]::new);
    }

    public static class Cell{
        private int row;
        private int column;

        public Cell(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Cell cell = (Cell) object;
            return row == cell.row && column == cell.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }
}
