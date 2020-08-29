import java.util.Scanner;

// https://www.acmicpc.net/problem/9663
// 백트래킹 복습
public class Main_BJ_9663  {
	private static int[] map;
	private static int N,cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N+1];
		
		nQueen(0);
		
		System.out.println(cnt);
		sc.close();
	}// end main
	
	public static void nQueen(int x) {		
		if(check(x)) {
			if(x == N) cnt++;
			else {
				for (int i = 1; i <= N; i++) { // map의 인덱스가 행 값이 열 번호 이다.
					map[x+1] = i;
					nQueen(x+1);
				}
			}
		}
	}// end nQueen

	public static boolean check(int x) {
		int k = 1;
		while(k < x) {
			if(map[x] == map[k] || (Math.abs(map[x] - map[k]) == x-k)) // k값을 증가시키면서 열 번호가 같은지 확인하고 절대값으로 차를 봐서 대각선을 확인
				return false;
			k++;
		}
		return true;
	}// end check
}
