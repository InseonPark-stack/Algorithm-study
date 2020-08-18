import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14499

// 주사위 윗면을 출력 
// 주사위는 구르면서 이동한 칸에 쓰여있는 수가 주사위의 바닥면에 복사가 된다.
public class Main_BJ_14499 {
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken()); // 세로크기
		int M = Integer.parseInt(st.nextToken()); // 가로크기
		int diceX = Integer.parseInt(st.nextToken()); // 주사위 초기 X값
		int diceY = Integer.parseInt(st.nextToken()); // 주사위 초기 Y값
		int K = Integer.parseInt(st.nextToken()); // 명령 수
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dice = new int[7]; // 윗면 1 아랫면 6 뒷면 5 앞면 2 왼쪽 4 오른쪽 3
		for (int i = 0; i < dice.length; i++) {
			dice[i] = 0;
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			switch (st.nextToken()) {
			case "1":
				int my = diceY + 1;
				if(my < 0 || my > M-1) break;
				diceY = my;
				int temp = dice[3];
				dice[3] = dice[1];
				dice[1] = dice[4];
				dice[4] = dice[6];
				dice[6] = temp;
				if(map[diceX][diceY] == 0) {
					map[diceX][diceY] = dice[6];
				} else {
					dice[6] = map[diceX][diceY];
					map[diceX][diceY] = 0;
				}
				sb.append(dice[1]).append('\n');				
				break;
			case "2":
				my = diceY - 1;
				if(my < 0 || my > M-1) break;
				diceY = my;
				temp = dice[3];
				dice[3] = dice[6];
				dice[6] = dice[4];
				dice[4] = dice[1];
				dice[1] = temp;	
				if(map[diceX][diceY] == 0) {
					map[diceX][diceY] = dice[6];
				} else {
					dice[6] = map[diceX][diceY];
					map[diceX][diceY] = 0;
				}
				sb.append(dice[1]).append('\n');	
				break;
			case "3":
				int mx = diceX - 1;
				if(mx < 0 || mx > N-1) break;
				diceX = mx;
				temp = dice[2];
				dice[2] = dice[1];
				dice[1] = dice[5];
				dice[5] = dice[6];
				dice[6] = temp;	
				if(map[diceX][diceY] == 0) {
					map[diceX][diceY] = dice[6];
				} else {
					dice[6] = map[diceX][diceY];
					map[diceX][diceY] = 0;
				}
				sb.append(dice[1]).append('\n');		
				break;
			case "4":
				mx = diceX + 1;
				if(mx < 0 || mx > N-1) break;
				diceX = mx;
				temp = dice[2];
				dice[2] = dice[6];
				dice[6] = dice[5];
				dice[5] = dice[1];
				dice[1] = temp;	
				if(map[diceX][diceY] == 0) {
					map[diceX][diceY] = dice[6];
				} else {
					dice[6] = map[diceX][diceY];
					map[diceX][diceY] = 0;
				}
				sb.append(dice[1]).append('\n');	
				break;
			}
		}	
		System.out.println(sb);
		/*	
		 * 원래	2
		 * 	4	1	3
		 * 		5
		 * 		6
		 *  	
		 * 남쪽	6
		 * 4	2	3
		 * 		1		
		 * 		5
		 * 
		 * 북쪽	1
		 * 4	5	3
		 * 		6
		 * 		2
		 * 
		 * 동쪽	2
		 * 	6	4	1
		 * 		5
		 * 		3
		 * 
		 * 서쪽	2
		 * 	1	3	6
		 * 		5
		 * 		4
		 * 
		 */
                  
	}// end main
}
