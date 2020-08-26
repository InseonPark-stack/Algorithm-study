import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2589
public class Main_BJ_2589 {
	private static char[][] map,mapc;
	private static int M,N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		mapc = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			map[i] = s.toCharArray();			
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int day = 0;
				if(map[i][j] == 'L') {
					for (int k = 0; k < N; k++) {
						mapc[k] = map[k].clone();
					}
					day = bfs(i,j,0);
				}
				if(max < day) max = day;
			}
		}
		System.out.println(max);
	}// end main

	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = { 0, 0,-1, 1};
	public static int bfs(int i, int j, int day) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i,j,day});
		mapc[i][j] = 0;		
		while(!q.isEmpty()) {
			int[] val = q.poll();
			int x = val[0];
			int y = val[1];
			day = val[2];
			for (int k = 0; k < dx.length; k++) {
				int mx = x + dx[k];
				int my = y + dy[k];
				if(mx < 0 || mx >= N || my < 0 || my >= M || mapc[mx][my] != 'L') continue;
				else {
					mapc[mx][my] = 0;
					q.offer(new int[] {mx,my,day+1});
				}
			}
		}
		return day;
	}
}
