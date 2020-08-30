import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2630
public class Main_BJ_2630 {
	private static int N, whiteCnt, blueCnt;
	private static char[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = st.nextToken().charAt(0);
			}
		}
		//---------------------------------------------------------입력
		
		division(N,0,0);
		
		System.out.println(whiteCnt);
		System.out.println(blueCnt);
	}// end main

	public static void division(int n, int x, int y) {
		if(n==0) return;
		char temp = arr[x][y];
		boolean check = true;
		for (int i = x; i < x+n; i++) {
			for (int j = y; j < y+n; j++) {
				if(temp != arr[i][j]) {
					check = false;
					break;
				}
			} 			
		}
		if(check) {
			if(temp == '0') whiteCnt++;
			if(temp == '1') blueCnt++;
		} else {
			int nn = n/2;
			division(nn,x,y);
			division(nn,x+nn,y);
			division(nn,x,y+nn);
			division(nn,x+nn,y+nn);
		}
	}// end division
}
