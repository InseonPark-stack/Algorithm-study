import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2638
public class Main_BJ_2638 {
	private static int N,M;
	private static int[][] map,visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new int[N][M];
		Queue<int[]> cheeze = new LinkedList<int[]>();
		Queue<int[]> deathCheeze = new LinkedList<int[]>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {				
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					cheeze.add(new int[] {i,j});
				}
			}
		}
		// ---------------------------------------------------------- 입력
		// 먼저 외부 공기있는 부분을 먼저 탐색 BFS
		// 치즈의 좌표를 탐색 외부 공기가 2면이상 닿아있는 부분은 0으로 바꿔주고
		// 다시 BFS돌아서 외부 공기 탐색 총 개수가 다 사라지면 끝 
		
		bfs(0,0); // 외부공기 체크
		
		int day = 1;
		while(true) {
			int run = cheeze.size(); // 큐의 길이가 바뀌니까 이것도 바뀌나? 
			for (int k = 0; k < run; k++) { // 큐 사이즈 변동때문에 제대로된 결과 값이 안나옴
				int[] val = cheeze.poll();
				int x = val[0];
				int y = val[1];
				int count = 0;
				for (int i = 0; i < dx.length; i++) { // 상하좌우 2변이 닿아있는지 탐색
					int mx = x + dx[i];
					int my = y + dy[i];
					if(visited[mx][my]!=-1) continue;
					count++;
					if(count == 2) {
						break;
					}
				}
				if(count >= 2) { // 외부공기랑 내부공기를 연결하기 위해 큐를 하나 더씀
					map[x][y] = 0;
					deathCheeze.add(new int[] {x,y});
				} else {
					cheeze.add(new int[] {x,y});
				}				
			}
			if(cheeze.isEmpty()) break; // 비어있으면 다끝났어
			while(!deathCheeze.isEmpty()) { // 외부공기다시 체크하기
				int[] val = deathCheeze.poll();
				if(visited[val[0]][val[1]] != -1) bfs(val[0],val[1]);
			}
			day++;
		}
		System.out.println(day);
	}// end main

	public static int[] dx = {-1,1,0,0};
	public static int[] dy = {0,0,-1,1};
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		visited[x][y] = -1;
		while(!q.isEmpty()) {
			int[] val = q.poll();
			x = val[0];
			y = val[1];
			for (int i = 0; i < dx.length; i++) {
				int mx = x + dx[i];
				int my = y + dy[i];
				if(mx<0||mx>N-1||my<0||my>M-1||visited[mx][my]==-1||map[mx][my]==1) continue;
				q.add(new int[] {mx,my});
				visited[mx][my] = -1;
			}			
		}
	}// end bfs
}
