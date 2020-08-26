import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1600
public class Main_BJ_1600 {
	private static int W,H,K;
	private static int[][] map;
	private static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine()); // 말처럼 이동 가능한 횟수 <=30
		
		st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken()); // W < 200 가로 길이
		H = Integer.parseInt(st.nextToken()); // H < 200 세로 길이
		
		map = new int[H][W];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// ----------------------------------------------------- 입력
		// 말처럼 이동 가능할 때는 장애물도 뛰어 넘을 수 있고 8방향으로 갈 수 있음
		// 진행 했을 때 최솟 값을 찾아라 도착점에 도착할 수 없으면 -1
		visited = new boolean[31][H][W]; // 말처럼 이동횟수만큼 3차원 배열 생성
		int min = bfs(0,0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
		
	}// end main
	public static int[] dx = {-1,1,0,0,-2,-1,-2,-1, 1, 2, 2, 1};
	public static int[] dy = {0,0,-1,1,-1,-2, 1, 2, 2, 1,-1,-2};
	public static int bfs(int x, int y) {
		int min = Integer.MAX_VALUE;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y,0,0});
		for (int i = 0; i < 31; i++) {
			visited[i][x][y] = true; // 전부다 첫번째는 방문 체크
		}
		while(!q.isEmpty()) {
			int[] val = q.poll();
			x = val[0];
			y = val[1];
			int day = val[2];
			int cnt = val[3];
			if(x == H-1 && y == W-1) {
				if(min > day) {
					min = day;
					break;
				}
			}
//			if(cnt >= K) { // 말처럼 이동할 횟수가 없으면
//				for (int i = 0; i <= 3; i++) {
//					int mx = x + dx[i];
//					int my = y + dy[i];
//					if(mx < 0 || mx >= H || my < 0 || my >= W || visited[cnt][mx][my]) continue;
//					if(map[mx][my] == 0) { // 장애물도 못가고
//						visited[cnt][mx][my] = true; // 방문체크
//						q.offer(new int[] {mx,my,day+1,cnt});
//					}
//				}
//			} else { // 회수가 남았으면
				for (int i = 0; i < dx.length; i++) {
					int mx = x + dx[i];
					int my = y + dy[i];
					if(mx < 0 || mx >= H || my < 0 || my >= W || visited[cnt][mx][my]) continue;
					if(i <= 3 && map[mx][my] == 0) { // 인접한곳 갈때는 장애물 못감
						visited[cnt][mx][my] = true; // 방문체크
						q.offer(new int[] {mx,my,day+1,cnt});
					}
				}
				if(cnt+1 <= K) {
					for (int i = 0; i < dx.length; i++) {
						int mx = x + dx[i];
						int my = y + dy[i];
						if(mx < 0 || mx >= H || my < 0 || my >= W || visited[cnt+1][mx][my]) continue;
						if(i > 3 && map[mx][my] == 0) { // 말처럼 이동
							visited[cnt+1][mx][my] = true; // 방문체크
							q.offer(new int[] {mx,my,day+1,cnt+1});
						}
					}
				}
			}
//		}
		return min;
	}
}
