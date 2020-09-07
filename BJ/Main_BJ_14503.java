import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14503
public class Main_BJ_14503 {
	private static int[][] map;
	private static int N,M;

	public static void main(String[] args) throws IOException {
		// 현재 위치를 청소한다
		// 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
		// a. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번 부터 진행한다.
		// b. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
		// c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
		// d. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
		// 로봇 청소기는 이미 청소되어 있는 칸을 또 청소하지 않으며, 벽을 통과할 수 없S다.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 세로 <= 50
		M = Integer.parseInt(st.nextToken()); // 가로 <= 50
		
		st = new StringTokenizer(br.readLine());
		int robotX = Integer.parseInt(st.nextToken()); 
		int robotY = Integer.parseInt(st.nextToken()); 
		int distance = Integer.parseInt(st.nextToken()); // 0 북쪽 1 동쪽 2남쪽 3서쪽 
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 벽이 1 빈칸 0
			}
		}
		
		// ---------------------------------------------------- 입력
		int[] dx = {0,-1,0,1}; // 북쪽이면 서쪽을 탐색 동쪽이면 북쪽을 탐색
		int[] dy = {-1,0,1,0}; // 남쪽이면 동쪽을 탐색 서쪽이면 남쪽을 탐색
		
		int can = 0;
		int count = 0;
		while(true) {
			if(map[robotX][robotY] == 0) { 
				map[robotX][robotY] = -1; // 청소했다는 표시
				can++; // 최종 출력 더해주기
			}
			// 왼쪽 방향 부터 탐색			
			int leftX = robotX + dx[distance];
			int leftY = robotY + dy[distance];
			if(leftX >= 0 && leftX <= N-1 && leftY >= 0 && leftY <= M-1 && map[leftX][leftY] == 0) {
				if(distance == 0) distance += 4; // 북쪽을 바라보고 있으면 4를 더한 후에 빼주기
				distance--;
				robotX = leftX;
				robotY = leftY; // 위치 바까주기
				count = 0; // 초기화 시켜주기
			} else {
				// 회전만하고 다음 탐색
				if(distance == 0) distance += 4; // 북쪽을 바라보고 있으면 4를 더한 후에 빼주기
				distance--;
				count++; // 4만큼 쌓이면 c조건 만족
			}
			if(count >= 4) { // 못가는 경우
				// 방향 유지한채로 한 칸 후진
				int backDis = distance - 1;
				if(backDis == -1) backDis += 4;
				int backX = robotX + dx[backDis];
				int backY = robotY + dy[backDis];
				if(backX < 0 || backX > N-1 || backY < 0 || backY > M-1 || map[backX][backY] == 1) {
					break; // 뒤로도 못가는 경우(d조건)
				} else {
					robotX = backX;
					robotY = backY;
					count = 0;
				}
			}
		}
		System.out.println(can);
	}// end main
}// end class
