import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14890
public class Main_BJ_14890 {
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 배열 크기
		int L = Integer.parseInt(st.nextToken()); // 경사로 길이
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// ------------------------------------------------------ 입력
		// 우선 높이 차가 무조건 1
		// 수가 작아지면 현재 위치에서 앞의 L만큼 검사
		// 수가 커지면 뒤의 L 만큼 검사
		// 겹치는 부분은 visited 배열을 두개 더 만들어서(가로,세로) 경사로 놓았는지 안 놓았는지 체크
		// 검사할때 항상 배열 범위 체크
		
		// 가로 검사
		int cnt = 0;
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			int nowValue = map[i][0];
			boolean check = true;
ex:			for (int j = 1; j < N; j++) {
				int nextValue = map[i][j];
				if(nowValue == nextValue) { // 같으면 체크같은거 안해도됨
					nowValue = nextValue; // 현재 값을 바꿔주고 다음거 진행
					continue;
				}
				if(nowValue-1 == nextValue) {// 값이 작아졌으면
					for (int k = j; k < j + L; k++) { // 앞의 L만큼 검사
						if(k >= N || nextValue != map[i][k]) { // 범위를 벗어난 경우 or 값이 다른 경우
							check = false;
							break ex;
						}
						// 앞의 L만큼 할때는 겹치는거 검사할 필요 없음						
					}// 다돌았는데 이상 없음
					for (int k = j; k < j + L; k++) { // 방문체크
						visited[i][k] = true; 
					}
					j += (L-1); // j 값 업데이트 해주고
					nowValue = map[i][j]; // 현재 값 마지막 확인한 밸류로 바꿔주기
				}else if(nowValue == nextValue-1) { // 값이 커졌으면					
					for (int k = j-1; k >= j - L; k--) { // 뒤의 L만큼 검사
						if(k < 0 || nowValue != map[i][k] || visited[i][k]) { // 범위를 벗어난 경우 or 다른값이 있거나 이미 경사로가 설치 되었으면
							check = false;
							break ex;
						}
					}// 다돌았는데 이상 없음
					for (int k = j-1; k >= j - L; k--) { // 방문체크(지워도이상없는지)
						visited[i][k] = true; 
					}
					nowValue = nextValue;
				}else {// 1 차이 이상 넘어가는 경우
					check = false;
					break;
				}
			}
			if(check) {
				cnt++;
			}
		}// end 가로검사
		
		// 세로 검사		
		visited = new boolean[N][N]; // 썻던거 초기화
		for (int i = 0; i < N; i++) {
			int nowValue = map[0][i];
			boolean check = true;
ex:			for (int j = 1; j < N; j++) {
				int nextValue = map[j][i];
				if(nowValue == nextValue) { // 같으면 체크같은거 안해도됨
					nowValue = nextValue; // 현재 값을 바꿔주고 다음거 진행
					continue;
				}
				if(nowValue-1 == nextValue) {// 값이 작아졌으면
					for (int k = j; k < j + L; k++) { // 앞의 L만큼 검사
						if(k >= N || nextValue != map[k][i]) { // 범위 초과 or 다르면 다음 행으로
							check = false;
							break ex;
						}
						// 앞의 L만큼 할때는 겹치는거 검사할 필요 없음						
					}// 다돌았는데 이상 없음
					for (int k = j; k < j + L; k++) { // 방문체크
						visited[k][i] = true; 
					}
					j += (L-1); // j 값 업데이트 해주고
					nowValue = map[j][i]; // 현재 값 마지막 확인한 밸류로 바꿔주기
				}else if(nowValue == nextValue-1) { // 값이 커졌으면
					for (int k = j-1; k >= j - L; k--) { // 뒤의 L만큼 검사
						if(k < 0 || nowValue != map[k][i] || visited[k][i]) { // 범위 초과 or 다른값이 있거나 이미 경사로가 설치 되었으면
							check = false;
							break ex;
						}
					}// 다돌았는데 이상 없음
					nowValue = nextValue;
				}else {// 1 차이 이상 넘어가는 경우
					check = false;
					break;
				}
			}
			if(check) {
				cnt++;			
			}
		}// end 세로검사
		System.out.println(cnt);
	}// end main
}// end class
