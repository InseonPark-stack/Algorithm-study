

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_JO_1863 {
	private static int N;
	private static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken()); // 숫자 최댓값
		int m = Integer.parseInt(st.nextToken()); // 입력 받는 라인수
		parents = new int[N+1]; // 0 미포함
		
		for (int i = 1; i < N+1; i++) {
			parents[i] = i; // 대표자 하나만 가진 집합 생성
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken());
			union(a,b);
		}
		
		int count = 0;
		
		for (int i = 1; i < N+1; i++) {
			if(parents[i] == i) {
				count++;
			}
		}
		System.out.println(count);
	}// end main
	/** 두 집합을 합치기 */
	public static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) { // 같은 그룹이면
			return;
		} 
		parents[bRoot] = aRoot; // 두 그룹을 합치기 // a가 짱임		
	}// end union
	
	/** 대표자 찾기 */
	public static int findSet(int a) {
		if(parents[a] == a) { // 내가 대표자라면
			return a;
		} else {
			return parents[a] = findSet(parents[a]);
		}				
	}//end findSet
}
