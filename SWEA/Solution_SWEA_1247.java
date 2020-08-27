import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD&categoryId=AV15OZ4qAPICFAYD&categoryType=CODE
public class Solution_SWEA_1247 {
	private static int[][] map;
	private static int comX,comY,homeX,homeY;
	private static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		
		for (int testcase = 1; testcase <= T; testcase++) {
			int N = Integer.parseInt(br.readLine()); // 고객의 수 2<= <=10
			map = new int[N][2];
			st = new StringTokenizer(br.readLine()," ");
			comX = Integer.parseInt(st.nextToken()); // 회사의 X 좌표
			comY = Integer.parseInt(st.nextToken()); // 회사의 Y 좌표
			homeX = Integer.parseInt(st.nextToken()); // 집 X 좌표
			homeY = Integer.parseInt(st.nextToken()); // 집 Y 좌표
			for (int i = 0; i < N; i++) {
				map[i][0] = Integer.parseInt(st.nextToken()); // 고객 X
				map[i][1] = Integer.parseInt(st.nextToken()); // 고객 Y
			}
			// ------------------------------------------------------- 입력
			// 순열 구하기 i의 인덱스 별로 순열 정렬 후
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = i;
			}
			min = Integer.MAX_VALUE;
			permutation(arr,0,N,N);
			System.out.println("#"+testcase+" "+min);
		}// end for testcase
	}// end main

	public static void permutation(int[] arr, int depth, int n, int r) {
		if(depth == r) {
			// 순열 하나 구했으면 회사부터 거리재기 시작
			distance(arr,r);
			return;
		}
		
		for (int i = depth; i < n; i++) {
			swap(arr,depth,i);
			permutation(arr,depth+1,n,r);
			swap(arr,depth,i);
		}
	}// end permutation

	public static void distance(int[] arr, int r) {
		// 거리재기
		int sum = 0;
		sum += abs(comX - map[arr[0]][0]) + abs(comY - map[arr[0]][1]);
		for (int i = 1; i < r; i++) {
			int x = map[arr[i]][0]; // 고객 X
			int y = map[arr[i]][1]; // 고객 Y
			int perX = map[arr[i-1]][0]; // 이전 고객 X
			int perY = map[arr[i-1]][1]; // 이전 고객 X
			sum += abs(x - perX) + abs(y - perY);
			if(min < sum) {
				return;
			}
		}
		sum += abs(homeX - map[arr[r-1]][0]) + abs(homeY - map[arr[r-1]][1]);
		if(min > sum) {
			min = sum;
		}
	}// end distance

	private static int abs(int i) {
		return i < 0 ? -i : i;
	}// end abs

	public static void swap(int[] arr, int depth, int i) {
		int temp = arr[depth];
		arr[depth] = arr[i];
		arr[i] = temp;
	}// end swap
}
