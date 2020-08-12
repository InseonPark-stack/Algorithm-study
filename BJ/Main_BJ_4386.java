package ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4386

public class Main_BJ_4386 {
	private static int n;
	private static int[] parents;
	private static double[][] doubleArray;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine()); // 별의 개수
		
		parents = new int[n];
		// make-set
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
		
		doubleArray = new double[n][2];
		int doubleIndex = 0; // 0번 부터 시작
		
		bul[] bx = new bul[n*(n-1)/2];
		int bxIndex = 0; // 0번부터 시작
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			double a = Double.parseDouble(st.nextToken()); 
			double b = Double.parseDouble(st.nextToken());
			
			doubleArray[doubleIndex][0] = a;
			doubleArray[doubleIndex++][1] = b;
			
			
			for (int j = 1; j < doubleIndex; j++) { // 1번부터 현재 입력받은거 전까지
				double x = doubleArray[j-1][0];
				double y = doubleArray[j-1][1];
				double dis = Math.sqrt((x - a) * (x - a) + (y - b) * (y - b));
				bx[bxIndex++] = new bul(i,j-1,dis);
			}
		}
		
		Arrays.sort(bx); // NUllpointer예외 자주 발생
		
		double result = 0.0;
		for (int i = 0; i < bxIndex; i++) {
			if(union(bx[i].first_jum, bx[i].last_jum)) {
				result += bx[i].dis;
			}
		}
		System.out.println(result);
		
	}// end main
	
	public static boolean union(int a, int b){
		int aR = find(a);
		int bR = find(b);
		if(aR == bR) return false;
		parents[bR] = aR;
		return true;
	}

	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static class bul implements Comparable<bul>{
		int first_jum;
		int last_jum;
		double dis;
		public bul() {}
		public bul(int first, int last, double dis) {
			this.first_jum = first;
			this.last_jum = last;
			this.dis = dis;
		}
		@Override
		public int compareTo(bul o) {
			return Double.compare(this.dis, o.dis);
		}
		@Override
		public String toString() {
			return "bul [first_jum=" + first_jum + ", last_jum=" + last_jum + ", dis=" + dis + "]";
		}
		
	}
}
