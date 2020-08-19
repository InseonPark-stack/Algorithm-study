import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3190
public class Main_BJ_3190 {
	private static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 보드의 크기
		int K = Integer.parseInt(br.readLine()); // 사과의 개수
		
		int headX = 0;
		int headY = 0;
		map = new int[N][N];
		map[headX][headY] = 0;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 사과의 좌표 행
			int y = Integer.parseInt(st.nextToken()); // 사과의 좌표 열
			
			map[x-1][y-1] = -1; // 사과 위치 표시
		}
		
		int L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 정보
		
		char[] com = new char[10001];
		
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken()); // 몇초 뒤에 그 전까지는 직진
 			char C = st.nextToken().charAt(0); // L 좌회전 D 우회전
 			com[X] = C;
		}
		int[] dx = {0,-1,0,1};
		int[] dy = {-1,0,1,0};
		int time = 0; // 시간
		int k = 2; // 오른쪽 방향 음수랑 4이상 커졌을때를 생각
		int dis = 1; // 뱀 길이
		while(true) {
			// 방향 전환하는지? 
			if(com[time] == 'L') {
				k--;
			} else if (com[time] == 'D'){
				k++;
			}
			int dk = (k % 4) < 0? ((k % 4) + 4) : (k % 4);
			
			headX += dx[dk];
			headY += dy[dk];
			
			time++;
			// 진행방향으로 머리는 밀어넣음 벽이나 자기자신이면 사망
			if(headX < 0 || headX > N-1 || headY < 0 || headY > N-1 || map[headX][headY] >= 1) {
				break;
			}
			if(map[headX][headY] == -1) {
				map[headX][headY] = time;
				dis++;
			} else {
				map[headX][headY] = time;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(map[i][j] == time-dis) {
							map[i][j] = 0;
						}
					}
				}
			}
			// 사과 있으면 사과 없어지고 꼬리가 멈춤
			// 사과 없으면 꼬리를 땡김
			int j = 0;
		}
		System.out.println(time);
	}// end main
}
