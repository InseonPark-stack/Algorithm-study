import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_10026 {
	private static char[][] map,cMap;
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // <= 100	
		
		map = new char[N][N];
		cMap = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
				cMap[i][j] = s.charAt(j);
			}
		}
		
		int noCount = 0;
		// 적녹색약이 아닌 경우
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] != 'X') {
					bfs(i,j,map[i][j]);
					noCount++;
				}
			}
		}
		// 적녹색약인 경우
		int Count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(cMap[i][j] != 'X') {
					cbfs(i,j,cMap[i][j]);
					Count++;
				}
			}
		}
		System.out.println(noCount + " " + Count);
	}// end main

	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = { 0, 0,-1, 1};
	public static void bfs(int i, int j, char c) {
		Queue<int[]> q = new LinkedList<>();
		map[i][j] = 'X';
		q.offer(new int[] {i,j});
		while(!q.isEmpty()) {
			int[] val = q.poll();
			int x = val[0];
			int y = val[1];
			for (int k = 0; k < dx.length; k++) {
				int mx = x + dx[k];
				int my = y + dy[k];
				if(mx<0 || mx>=N || my<0 || my>=N) continue;
				if(map[mx][my] == c) {
					q.offer(new int[] {mx,my});
					map[mx][my] = 'X';
				}
			}
		}		
	}// end bfs
	public static void cbfs(int i, int j, char c) {
		Queue<int[]> q = new LinkedList<>();		
		cMap[i][j] = 'X';
		q.offer(new int[] {i,j});
		while(!q.isEmpty()) {
			int[] val = q.poll();
			int x = val[0];
			int y = val[1];
			for (int k = 0; k < dx.length; k++) {
				int mx = x + dx[k];
				int my = y + dy[k];
				if(mx<0 || mx>=N || my<0 || my>=N) continue;
				if(c == 'B') { // 블루이면 그대로 진행
					if(cMap[mx][my] == c) {
						q.offer(new int[] {mx,my});
						cMap[mx][my] = 'X';
					}
				}
				else { // 레드 그린이면 같이 묶기
					if(cMap[mx][my] == 'R' || cMap[mx][my] == 'G') {
						q.offer(new int[] {mx,my});
						cMap[mx][my] = 'X';
					}
				}
			}
		}		
	}// end bfs
}
