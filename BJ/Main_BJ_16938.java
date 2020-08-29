import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16938
public class Main_BJ_16938 {
	private static int[] score;
	private static int L,R,X,count;
	private static int[] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken()); // 1<= N <= 15 : 문제의 개수
		L = Integer.parseInt(st.nextToken()); // 1<= L <= 10^9 : 난이도의 합 최소값
		R = Integer.parseInt(st.nextToken()); // 1<= R <= 10^9 : 난이도의 합 최대값
		X = Integer.parseInt(st.nextToken()); // 1<= X <= 10^6 : 가장 어려운 문제와 가장 쉬운 문제의 난이도 차 >= X
		
		score = new int[N];
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) {
			score[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 2; i <= N; i++) {
			int[] result = new int[i];
			comb(result,N, i, 0, 0); // N개 중에 i개 뽑기 
		}
		System.out.println(count);
	}// end main

	public static void comb(int[] result,int n, int r, int cnt, int start) { // n개중에 r개뽑기 , cnt : 현재뽑은 개수
		if(cnt == r) {
			if(check(result)) {
				count++;
			}
			return;
		}
		for (int i = start; i < n; i++) {
			result[cnt] = score[i];
			comb(result,n, r, cnt + 1,i +1);
			
		}
	}// end comb

	public static boolean check(int[] result) {
		int sum = 0;
		int max = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < result.length; i++) {
			int pro = result[i];
			sum += pro;
			if(max < pro) max = pro;
			if(min > pro) min = pro;			
		}
		if(L <= sum && sum <= R && (max-min) >= X) return true;
		return false;
	}
}
