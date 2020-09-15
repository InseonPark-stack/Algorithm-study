import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1956
public class Main_BJ_1956 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		int[][] map = new int[V][V];
		
		int INF = 20000;
		for (int i = 0; i < V; i++) { // 자기자신에게 가는경우는 0, 아닌 경우는 INF
			Arrays.fill(map[i], INF);			
			map[i][i] = 0;
		}		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int startPoint =  Integer.parseInt(st.nextToken()); // 시작정점
			int endPoint =  Integer.parseInt(st.nextToken()); // 도착정점
			int val = Integer.parseInt(st.nextToken()); // 가중치
			map[startPoint-1][endPoint-1] = val;
		}
		for (int through = 0; through < V; through++) { // 거쳐가는 노드
			for (int start = 0; start < V; start++) { // 출발 노드
				for (int end = 0; end < V; end++) { // 도착 노드
					if(map[start][through] + map[through][end] < map[start][end]) { // 거쳐서 돌아가는게 일직선으로 가는거보다 가중치가 작으면
						map[start][end] = map[start][through] + map[through][end];  // 그 값으로 교체해준다
					}
				}
			}
		}		
		int min = Integer.MAX_VALUE;
		for (int start = 0; start < V; start++) {
			for (int end = start+1; end < V; end++) {
				if(min > map[start][end] + map[end][start]) {
					min = map[start][end] + map[end][start];
				}
			}
		}
		System.out.println(min >= 20000? -1 : min);
	}// end main
}// end class
