import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWsBQpPqMNMDFARG&categoryId=AWsBQpPqMNMDFARG&categoryType=CODE
public class Solution_SWEA_7793 {
	private static char[][] map;
	private static int yusinX,yusinY, N, M;
	private static int[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테케 수
		
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // <= 50
			M = Integer.parseInt(st.nextToken()); // <= 50
			
			map = new char[N][M];
			visited = new int[N][M];
			
			
			int suyunX = 0, suyunY = 0;
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);
					if(map[i][j] == 'S') {
						suyunX = i;
						suyunY = j;
					}
					if(map[i][j] == 'D') {
						yusinX = i;
						yusinY = j;
					}
				}
			}			
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {					
					visited[i][j] = Integer.MAX_VALUE;
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {					
					if(map[i][j] == '*') 
						bfsAnk(i,j,0);
				}
			}
			int day = bfs(suyunX,suyunY,0);
			System.out.println("#" + testcase + " " + (day == 0 ? "GAME OVER" : day));
		}
	}// end main

	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = { 0, 0,-1, 1};
	
	public static void bfsAnk(int i, int j, int day) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {i,j,0});
		visited[i][j] = 0;
		while(!q.isEmpty()) {
			int[] val = q.poll();
			int x = val[0];
			int y = val[1];
			day = val[2];
			for (int l = 0; l < dx.length; l++) {
				int mx = x + dx[l];
				int my = y + dy[l];
				if(mx < 0 || mx >= N || my < 0 || my >= M || map[mx][my] == 'D' || map[mx][my] == 'X') continue;
				if(day+1 < visited[mx][my] && map[mx][my] == '.') {
					visited[mx][my] = day+1;
					q.offer(new int[] {mx,my,day+1});
				}
			}
		}
	}

	
	private static int bfs(int i, int j, int day) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i,j,0});
		visited[i][j] = 0;
		while(!q.isEmpty()) {
			int[] val = q.poll();
			int x = val[0];
			int y = val[1];
			day = val[2];
			if(x == yusinX && y == yusinY) {
				return day;
			}
			for (int k = 0; k < dx.length; k++) {
				int mx = x + dx[k];
				int my = y + dy[k];
				if(mx < 0 || mx >= N || my < 0 || my >= M || map[mx][my] == 'X') continue;									
				if(day+1 < visited[mx][my]) {
					visited[mx][my] = 0;
					q.offer(new int[] {mx,my,day+1});
				}
			}
		}
		return 0;
	}// end bfs
}
