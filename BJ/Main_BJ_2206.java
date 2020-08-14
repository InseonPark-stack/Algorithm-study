package ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2206

public class Main_BJ_2206 {
	
	static int dis, N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken()); // 1<= N <= 1000 가로
		M = Integer.parseInt(st.nextToken()); // 1<= M <= 1000 세로
		
		int[][] map = new int[N][M];
		int[][] visit = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j)-'0';
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
		dis = Integer.MAX_VALUE;
		
		bfs(map,visit, 0, 0);
		
		System.out.println(dis == Integer.MAX_VALUE ? -1 : dis);
	}//end main
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = { 0, 0,-1, 1};
	
	//bfs
	public static void bfs(int[][] map, int[][] visit, int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		int day = 1;
		q.offer(new int[] {i, j, 0, day});
		visit[i][j] = 0;		
		while(!q.isEmpty()) {
			int[] val = q.poll();
			int x = val[0];
			int y = val[1];			
			int check = val[2]; // 벽 뚫었는지 안 뚫었는지
			day = val[3];
			if(x == map.length - 1 && y == map[0].length - 1) {
				dis = day;
				break;			
			}
			for (int k = 0; k < dx.length; k++) {
				int mx = x + dx[k];
				int my = y + dy[k];
				if(mx < 0 || mx >= map.length || my < 0 || my >= map[0].length) continue;
				if(visit[mx][my] <= check) continue;
				if(map[mx][my] == 0) {
					visit[mx][my] = check;
					q.offer(new int[] {mx,my,check,day+1});//다음경로 넣기						
				}else{
					if(check == 0) {
						visit[mx][my] = check+1;
						q.offer(new int[] {mx,my,check+1,day+1});//다음경로 넣기, 벽뚫은거 체크		
					}
				}				
			}			
		}
	}// end bfs
}// end class
