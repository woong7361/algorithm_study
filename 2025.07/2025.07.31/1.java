// https://www.acmicpc.net/problem/1991
// 37 start


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";

        int nodeCount = Integer.valueOf(br.readLine());

        // 1 <= node의 수 <= 26
        // 루트노드는 항상 A
        // 노드 왼쪽자식 오른쪽자식 순으로 주어진다.
        // 자식이 없는 경우 .으로 주어진다.

        HashMap<String, List<String>> treeGraph = new HashMap<>();
        for (int i = 0; i < nodeCount; i++) {
            String[] edge = br.readLine().split(" ");

            treeGraph.putIfAbsent(edge[0], new ArrayList<>());

            treeGraph.get(edge[0]).add(edge[1]);
            treeGraph.get(edge[0]).add(edge[2]);
        }

        StringBuilder prevResult = new StringBuilder();
        StringBuilder middleResult = new StringBuilder();
        StringBuilder afterResult = new StringBuilder();

        recursive("A", treeGraph, prevResult, middleResult, afterResult);

        System.out.println(prevResult);
        System.out.println(middleResult);
        System.out.println(afterResult);
    }

    private static void recursive(String node, HashMap<String, List<String>> treeGraph,
                                  StringBuilder prevResult, StringBuilder middleResult, StringBuilder afterResult) {
        if(node.equals(".")) return;

        prevResult.append(node);

        recursive(treeGraph.get(node).get(0), treeGraph, prevResult, middleResult, afterResult);
        middleResult.append(node);
        recursive(treeGraph.get(node).get(1), treeGraph, prevResult, middleResult, afterResult);

        afterResult.append(node);
    }
}
