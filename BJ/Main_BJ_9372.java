package ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_9372 {
	private static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			
			for (int i = 1; i < N+1; i++) {
				parents[i] = i;
			}
			int count = 0;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				
				if(union(first,second)) {
					count++;
				}
			}
			System.out.println(count);
		}// end for testcase
	}// end main

	public static boolean union(int first, int second) {
		int aR = find(first);
		int bR = find(second);
		
		if(aR == bR) return false;
		else {
			parents[bR] = aR;
			return true;
		}
	}// end union

	public static int find(int a) {
		if (parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	
}
