import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeUtVakTMDFAVH&categoryId=AWIeUtVakTMDFAVH&categoryType=CODE
public class Solution_SWEA_4012 {
	private static int[][] map;
	private static int min;
	private static boolean exitIndex;	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int testcase = 1; testcase <= T; testcase++) {
			int N = Integer.parseInt(br.readLine()); // 개수
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			exitIndex = false;
			min = Integer.MAX_VALUE;
			int[] ls = new int[N];
			boolean[] check = new boolean[N];
			for (int i = 0; i < N; i++) {
				ls[i] = i;
			}
			combination(ls,check,N,N/2,0);
			sb.append('#').append(testcase).append(" ").append(min).append('\n');
		}
		System.out.println(sb);
	}// end main

	// 계산 회수 체크해서 조합 반토만 내서 계산
	public static void combination(int[] arr,boolean[] visited,int n, int r, int cnt) {
		if(!exitIndex) {
			if(r == 0) {
				check(visited,n);				
				return;
			}
			for (int i = cnt; i < n; i++) {
				visited[i] = true;
				combination(arr,visited,n,r-1,i+1);
				visited[i] = false;
			}
		}
	}// end combination

	public static void check(boolean[] visited, int R) {
		int[] firstCom = new int[R/2];
		int firstIndex = 0;
		int[] secondCom = new int[R/2];
		int secondIndex = 0;
		for (int i = 0; i < visited.length; i++) {
			if(visited[i] == true) {
				firstCom[firstIndex++] = i;
			}
			if(visited[i] == false) {
				secondCom[secondIndex++] = i;
			}
		}
		if(firstCom[0] >= 1) {
			exitIndex = true;
			return;
		}
		int firstSum = 0;
		int secondSum = 0;
		for (int i = 0; i < firstIndex; i++) {
			for (int j = i+1; j < firstIndex; j++) {
				int firstX = firstCom[i];
				int firstY = firstCom[j];
				firstSum += (map[firstX][firstY] + map[firstY][firstX]);
			}
		}
		for (int i = 0; i < secondIndex; i++) {
			for (int j = i+1; j < secondIndex; j++) {
				int secondX = secondCom[i];
				int secondY = secondCom[j];
				secondSum += (map[secondX][secondY] + map[secondY][secondX]);
			}
		}
		if(min > abs(firstSum - secondSum)) {
			min = abs(firstSum - secondSum);
		}
	}// end check
	private static int abs(int i) {		
		return i < 0 ? -i : i;
	}
}
