import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1753
public class Main_BJ_1753 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수 <=20000
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수 <=300000
		
		int K = Integer.parseInt(br.readLine()); // 시작정점의 번호
		
		// 인접 리스트 구현 (시작번호부터 간선의 정보를 입력)
		ArrayList<ArrayList<int[]>> list = new ArrayList<>(); // 리스트안에 리스트
		for (int i = 0; i < V+1; i++) { // 0부터 리스트 시작이니까 정점의 개수 +1해서 초기화
			list.add(new ArrayList<int[]>());
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 시작정점
			int b = Integer.parseInt(st.nextToken()); // 도착정점
			int c = Integer.parseInt(st.nextToken()); // 가중치
			list.get(a).add(new int[] {b,c}); // 시작정점 인덱스에 도착정점과 가중치의 배열을 넣는다.
		}
		// ------------------------------------------ 입력
		final int INFINITY = Integer.MAX_VALUE;
		int[] distance = new int[V+1]; // 출발지에서 자신까지 오는 최단 거리
		boolean[] visited = new boolean[V+1]; // 처리한 정점 여부 관리
		
		PriorityQueue<Vertex> pQ = new PriorityQueue<>();
		
		Arrays.fill(distance, INFINITY);
		distance[K] = 0; // 항상 K부터 시작
		pQ.offer(new Vertex(K, distance[K]));
		
		Vertex current = null;
		
		while(!pQ.isEmpty()) {
			current = pQ.poll(); // 최단 정점을 고려하여 시작!
			
			if(visited[current.no]) continue; // 이미 방문했던 정점이면 그만 빼고 진행
			
			visited[current.no] = true;
			
			for (int[] temp : list.get(current.no)) {
				int step = temp[0]; // 도착 정점
				int stepVer = temp[1]; // 도착점을가는데 필요한 가중치
				if(!visited[step] && distance[step] > current.totalDistance + stepVer){ // 방문했던 점이 아니고 , 거리의 가중치가 작으면
					distance[step] = current.totalDistance + stepVer; // 교체하고
					pQ.offer(new Vertex(step,distance[step])); // 큐에 새로운 vertex를 넣는다.
				}
			}
		}
		
		for (int i = 1; i < V+1; i++) {
			if(distance[i] == INFINITY) sb.append("INF").append('\n');
			else sb.append(distance[i]).append('\n');
		}
		
		System.out.println(sb);
	}// end main
	
	static class Vertex implements Comparable<Vertex>{
		int no , totalDistance; // 출발지점에서 자신까지 오는 거리

		public Vertex(int no, int totalDistance) {
			this.no = no;
			this.totalDistance = totalDistance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.totalDistance - o.totalDistance; // 최소값부터 우선순위가 생김
		}
		
		
	}// end Vertex
}// end class