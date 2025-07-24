
// https://school.programmers.co.kr/learn/courses/30/lessons/118669
// 30 start


import java.util.*;

class Solution {
	List<Node>[] graph;
	Set<Integer> start, end;
	int[] dist;
	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 등산 코스 정하기
        // n개의 지점 , 1~n의 번호
        // 출/입구, 쉼터, 산봉우리 3개중 하나
        // 양방향 통행가능 등산로, 등산로별 소요시간이 다르다.
        // 쉼터 혹은 산봉우리까지 휴식없이 이동시간 = intensity
        // 출입구에서 출발하여 산봉우리중 한곳만 방문 후 출입구로 되돌아올 때  intensity 가 최소가 되도록 하는 코스는?
        // intensity가 최소가 되는 등산코스가 여러 개라면 그중 산봉우리의 번호가 가장 낮은 등산코스를 선택합니다.

        // 2 <= n<= 50,000
//        n - 1 ≤ paths의 길이 ≤ 200,000  [i, j, w], i/j: node, w: time
        // 1 ≤ gates의 길이 ≤ n
        // 1 ≤ summits의 길이 ≤ n
//        gates와 summits에 등장하지 않은 지점은 모두 쉼터

        // 문제 설계
        // 출입구가 여러개
        // 출입구 -> 산봉우리 -> 출입구 (intensity가 최소가 되도록)
        // A 출입구에서 시작 -> 다른 출입구 만나면 X, 정상까지 ㄱㄱ (최소값으로) 우선순위 큐 다익스트라 (같은 값은 해야함!)
        // 가장 최소값으로 정상찍고 다른 출입구까지 가면 갱신

		graph = new List[n + 1];
		dist = new int[n + 1];
		for(int i = 0; i <= n; ++i)
			graph[i] = new ArrayList();
		start = new HashSet(); // 출발지
		end = new HashSet(); // 도착지

		for(int i : gates)
			start.add(i);
		for(int i : summits)
			end.add(i);

		for(int[] data : paths){
			if(!start.contains(data[1]) && !end.contains(data[0]))
				graph[data[0]].add(new Node(data[0], data[1], data[2]));
			if(!start.contains(data[0]) && !end.contains(data[1]))
				graph[data[1]].add(new Node(data[1], data[0], data[2]));
		}


		int[] answer = new int[2];
		search(answer);

		return answer;
	}

	public void search(int[] answer){
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue();
		for(int i : start){
			for(Node node : graph[i])
				pq.add(node);
		}

        answer[0] = Integer.MAX_VALUE;
        int max = Integer.MAX_VALUE;
		while(!pq.isEmpty()){
			Node cur = pq.poll();
            if(cur.weight > max)
                break;
			dist[cur.to] = cur.weight;

			if(end.contains(cur.to)){
				// 정상에 도착함. 최대값을 찾자.
				for(int value : dist){
					if(value>max && value != Integer.MAX_VALUE || max == Integer.MAX_VALUE)
						max = value;
				}
				answer[0] = answer[0]<cur.to?answer[0]:cur.to;
                //answer[0] =Math.min(answer[0],cur.to);
				answer[1] = max;
			}

			for(int i = 0; i < graph[cur.to].size(); ++i){
				Node next = graph[cur.to].get(i);

				// 다음지점으로 가본적이 없는 경우.
				if(dist[next.to] == Integer.MAX_VALUE) {
					pq.add(next);
				}
			}
		}
	}

	class Node implements Comparable<Node>{
	int from;
	int to;
	int weight;
	Node(int a, int b, int c){
		from = a;
		to = b;
		weight = c;
	}

	public int compareTo(Node o){
		if(this.weight == o.weight)
			return this.to - o.to;
		return this.weight - o.weight;
	}
}
}
