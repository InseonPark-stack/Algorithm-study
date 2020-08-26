import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1006&sca=99&sfl=wr_subject&stx=%EC%98%A4%EB%AA%A9
public class Main_JO_1733 {
	final static int N = 19; // 맵 크기
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());				
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// ------------------------------------------------------------------ 입력
		// 1이나 2일 경우 BFS -> day 리턴 5면 종료 
		
		int day = 0;
		
ex:		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				if(map[i][j] != 0) {
					if((day = bfs(i,j,1)) == 5) {
						System.out.println(map[i][j]);
						System.out.println((i+1) + " " + (j+1));
						break ex;
					}
				}
			}
		}
		if(day != 5) { // 다돌았는데도 5가 아니라면
			System.out.println("0");
		}
	}// end main
	public static int[] dx = {-1,-1,-1, 0, 1, 1, 1, 0};
	public static int[] dy = {-1, 0, 1,-1, 1, 0,-1, 1};
	
	public static int bfs(int i, int j, int day) {
		int lastDay = 0;
		for (int a = 0; a < dx.length; a++) {
			int mx = i+dx[a];
			int my = j+dy[a];
			if(mx < 0 || mx >= N || my < 0 || my >= N) continue;
			if(map[mx][my] == map[i][j]) {
				int tempDay = 0;
				if(a >= 4) { // 반대방향 체크
					tempDay = dfs(i,j,day,a-4);
				} else {
					tempDay = dfs(i,j,day,a+4);					
				}
				lastDay = dfs(mx,my,tempDay+1,a);
			}
			if(lastDay == 5) break;
		}
		return lastDay;
	}// end bfs

	public static int dfs(int x, int y, int day, int a) {	
		int mx = x + dx[a];
		int my = y + dy[a];
		if(mx < 0 || mx >= N || my < 0 || my >= N || map[mx][my] != map[x][y]) {
			return day;
		} else {
			return day = dfs(mx,my,day+1,a);
		}
	}// end dfs
}
