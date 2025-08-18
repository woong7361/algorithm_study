// https://www.acmicpc.net/problem/1647
// 03 start


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    //길 정보 저장 클래스
    static class Node implements Comparable<Node>{
        int A_idx, B_idx, value;
        //A_idx, b_idx : 연결된 집 정보, value : 유지비
        public Node(int A_idx, int B_idx, int value) {
            this.A_idx = A_idx;
            this.B_idx = B_idx;
            this.value = value;
        }
        //유지비 기준 오름차순
        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
    static int[] parent;	//Union-Find에 사용할 배열
    static StringBuilder answer = new StringBuilder();
    static ArrayList<Node> road = new ArrayList<>();	//길 정보 저장 리스트
    public static void main(String[] args) throws IOException{
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];

        //사전 작업!
        //parint[] 배열 각 집으로 초기화
        for(int i=1;i<=N;i++)
            parent[i] = i;

        //길 정보 저장
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine()," ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            road.add(new Node(A,B,C));
        }
        //사전 작업
        //유지비 기준 길 오름차순 정렬
        Collections.sort(road);
        int sum = 0;	//최단 경로에 유지비 합
        int max = -1;	//최단 경로에 최소 유지비 길
        //크루스칼 알고리즘으로 탐색!
        for(Node n : road) {
            //연결 가능한지.
            if(find(n.A_idx) != find(n.B_idx)) {
                union(n.A_idx, n.B_idx);	//Union-Find : Union 합치기!
                sum += n.value;	//유지비 더하기
                max = Math.max(max, n.value);	//유지비 최대값이지 확인
            }
        }
        sum -= max;	//마을을 2개로 분리하기 위해서 최대 길 제거!
        bw.write(String.valueOf(sum));	//최소 유지비 BufferedWriter 저장
        bw.flush();
        bw.close();
        br.close();

    }
    //Union-Find : Find 담당 함수
    static int find(int a) {
        if(parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }
    //Union-Find : Union 담당 함수
    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        //parent[] 작은 값 기준으로 저장되도록 설정!
        if(pa <= pb)
            parent[pb] = parent[pa];
        else
            parent[pa] = parent[pb];
    }
}
