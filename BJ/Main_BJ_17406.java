import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17406

public class Main_BJ_17406 {
	private static int[] numbers;
	private static ArrayList<int[]> perm;
	private static int K;
	private static boolean[] isSelected;
	private static int[][] mapc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()) + 1; // 배열 x
		int M = Integer.parseInt(st.nextToken()) + 1; // 배열 y
		K = Integer.parseInt(st.nextToken()); // 입력 K
		
		int[][] map = new int[N][M];
		mapc = new int[N][M];
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());				
			}
		}
		
		ArrayList <int[]> arList = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			int[] sem = new int[3];
			st = new StringTokenizer(br.readLine());
			sem[0] = Integer.parseInt(st.nextToken()); // r
			sem[1] = Integer.parseInt(st.nextToken()); // c
			sem[2] = Integer.parseInt(st.nextToken()); // s
			arList.add(sem);			
		}
		
		numbers = new int[K];
		isSelected = new boolean[K];
		perm = new ArrayList<int[]>();
		// 조합만큼 반복		
		permutation(0);		
		// 회전
		int min = Integer.MAX_VALUE;
		for (int[] is : perm) { // is : 인덱스
			for (int i = 0; i < N; i++) { // 복사
				for (int j = 0; j < M; j++) {
					mapc[i][j] = map[i][j];		
				}
			}
			for (int a = 0; a < K; a++) {
				int[] val = arList.get(is[a]);
				int r = val[0];
				int c = val[1];
				int s = val[2];
				rounding(r,c,s);
				// 최소값 비교
			}
			int sum = 0;
			for (int i = 1; i < N; i++) {
				sum = 0;
				for (int j = 1; j < M; j++) {
					sum += mapc[i][j];
				}
				if(min > sum) min = sum;
			}
		}
		System.out.println(min);
	}// end main

	
	public static void rounding(int r, int c, int s) {
		for (int a = 1; a <= s; a++) {
			int i = r-a, j = c-a;
			int temp = mapc[i][j];
			int temp2 = -1;
			for (; j < c+a;) {
				j++;
				temp2 = mapc[i][j];
				mapc[i][j] = temp;
				temp = temp2;
			}
			for (; i < r+a;) {
				i++;
				temp2 = mapc[i][j];
				mapc[i][j] = temp;
				temp = temp2;
			}
			for (; j > c-a;) {
				j--;
				temp2 = mapc[i][j];
				mapc[i][j] = temp;
				temp = temp2;
			}
			for (; i > r-a;) {
				i--;
				temp2 = mapc[i][j];
				mapc[i][j] = temp;
				temp = temp2;
			}
		}
	}

	public static void permutation(int cnt) {
		if(cnt == K) {
			int[] temp = new int[K];
			for (int i = 0; i < K; i++) {
				temp[i] = numbers[i];
			}
			perm.add(temp);
		}
		for (int i = 0; i < K; i++) {
			if(isSelected[i]) continue;
			numbers[cnt] = i;
			isSelected[i] = true;
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}
}
