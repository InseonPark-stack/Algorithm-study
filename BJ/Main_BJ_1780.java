import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1780
public class Main_BJ_1780 {
	private static int[][] arr;
	private static int minusCnt, zeroCnt, oneCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//------------------------------------------------------------입력
		
		division(N,0,0);		
		
		System.out.println(minusCnt);
		System.out.println(zeroCnt);
		System.out.println(oneCnt);
	}// end main

	// 9분할
	private static void division(int n, int x, int y) {  // x,y 시작점
		// 검사
		if(n == 0) {
			return;
		}
		int temp = arr[x][y];
		boolean check = true;
ex:		for (int i = x; i < x+n; i++) {
			for (int j = y; j < y+n; j++) {
				if(arr[i][j] != temp) {
					check = false;
					break ex;
				}
			}
		}
		if(check) {
			if(temp == -1) minusCnt++;
			else if(temp == 0) zeroCnt++;
			else if(temp == 1) oneCnt++;
		} else { // 9분할 시작
			int NN = n/3;
			division(NN,x,y);
			division(NN,x+NN,y);
			division(NN,x+NN+NN,y);
			division(NN,x,y+NN);
			division(NN,x+NN,y+NN);
			division(NN,x+NN+NN,y+NN);
			division(NN,x,y+NN+NN);
			division(NN,x+NN,y+NN+NN);
			division(NN,x+NN+NN,y+NN+NN);
		}
		
	}// end division
}
