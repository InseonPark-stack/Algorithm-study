import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17136
public class Main_BJ_17136_ver2 {
	private static char[][] arr; // 전체 저장 배열
	private static final int N = 10; // 배일의 크기
	private static int oneCnt, min; // 1의 개수 // 최소값
	private static int[] paperCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = st.nextToken().charAt(0);
				if(arr[i][j] == '1') oneCnt++;
			}
		}		
		//-----------------------------------------------입력
		
		paperCnt = new int [] {0,5,5,5,5,5}; // 가능한 색종의의 개수
		min = Integer.MAX_VALUE; // 최소값 초기화
		dfs(0,0,0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}// end main

	public static void dfs(int x, int y, int cnt) { // 좌표 , 붙인 색종이 개수
		if(x >= N-1 && y >= N) { // 종료조건 / 마지막 좌표에 도착했으면
			if(oneCnt == 0) { // 1을 다 지웠으면				
				if(min > cnt) min = cnt;
			}
			return;
		}
		if(min <= cnt) { // 같으면 볼것도 없지
			return;
		}
		if(y >= N) { // y가 10면은
			dfs(x+1,0,cnt);
			return;
		}
		if(arr[x][y] == '1') {
			for (int i = 5; i >= 1; i--) { // 크기가 5인 놈부터 넣기
				if(paperCnt[i] > 0 && check(x,y,i)) { // 색종이의 개수가 여유가 있는지? 유망한지?
					paperCnt[i]--;
					change(x,y,i,'0'); // 0으로바꾸기
					oneCnt -= i * i;
					dfs(x,y+1,cnt+1); // 다음 열로가서 확인
					oneCnt += i * i;
					change(x,y,i,'1'); // 다시 1로 바꾸기
					paperCnt[i]++;				
				}			
			}
		} else {
			dfs(x,y+1,cnt);
		}	
	}// end dfs

	private static void change(int x, int y, int size, char val) { // 좌표 사이즈 입력받고 맞는 값으로 바꾸기
		for (int i = x; i < x+size; i++) {
			for (int j = y; j < y+size; j++) {
				arr[i][j] = val;
			}
		}
	}// end change

	public static boolean check(int x, int y, int size) { // 이 좌표에 현재 크기의 색종이를 붙일 수 있는지를 판단하는 함수
		for (int i = x; i < x+size; i++) {
			for (int j = y; j < y+size; j++) {
				if(i >= N || j >= N) return false;
				if(arr[i][j] == '0') { // 하나라도 0이면 반환 뽈스
					return false;
				}
			}
		}
		return true;
	}
}// end class
