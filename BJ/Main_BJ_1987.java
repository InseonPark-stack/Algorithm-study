import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1987
public class Main_BJ_1987 {
	private static int R,C;
	private static int[][] mapc;
	private static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); // X
		C = Integer.parseInt(st.nextToken()); // Y
		
		int[][] map = new int[R][C];
		mapc = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j) - 'A';
				mapc[i][j] = s.charAt(j) - 'A';
			}
		}
		// ------------------------------------------------------- 입력
		// 방문체크를 알바벳인덱스를 가진 배열을 만들어서 O(1)이 되게끔
		// BFS XXXXX -> DFS 백트랙
		boolean[] visited = new boolean[26]; // 알파벳개수만큼 방문배열 생성	
		visited[map[0][0]] = true;
		map[0][0] = -1;		
		dfs(0,0,map, visited,1);
		System.out.println(max);
	}// end main

	public static int[] dx = {-1,1,0,0};
	public static int[] dy = {0,0,-1,1};
	
	private static void dfs(int x, int y, int[][] map, boolean[] visited, int cnt) {
		if(max < cnt) {
			max  = cnt;
		}
		for (int i = 0; i < dx.length; i++) {
			int mx = x + dx[i];
			int my = y + dy[i];
			if(mx < 0 || mx >= R || my < 0 || my >= C || map[mx][my] == -1 || visited[map[mx][my]]) continue;
			visited[map[mx][my]] = true;
			map[mx][my] = -1;
			dfs(mx,my,map,visited,cnt+1);
			map[mx][my] = mapc[mx][my];
			visited[map[mx][my]] = false;	
		}		
	}// end dfs	
}// end class
