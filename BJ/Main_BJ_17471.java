import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/17471
public class Main_BJ_17471 {
	private static int[] arr;
	private static int N;
	private static ArrayList<int[]> list;
	private static int[][] map;
	public static int[] trr, ingusu;
	private static int min = Integer.MAX_VALUE;
	private static int[] parents;
	private static int numJohab;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		ingusu = new int[N];
		map = new int[N][N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			ingusu[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			for (int j = 0; j < k; j++) {
				map[i][Integer.parseInt(st.nextToken())-1] = 1;
			}
		}
		// -------------------------------------------------- 인접행렬 입력
		// 조합 nCr개 뽑는 방법
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i;
		}
		list = new ArrayList<>();
		numJohab = 0;
		if(N % 2 == 0) numJohab = N/2;
		if(N % 2 != 0) numJohab = N/2+1;
		for (int r = 1; r <= numJohab; r++) { // 1개부터 N/2개까지
			trr = new int[r];
			comb(N, r);			
		} // 가능한 부분 집합은 N/2개의 절반만 구하면 됨
		check(); // 체킹
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}// end main

	public static void check() {
		for (int[] temp : list) { // 조합꺼내와서 연결되어 있는지 확인
			int[] opp = new int[N-temp.length]; // 반대쪽 배열 만들기
			int index = 0;
			boolean[] zip = new boolean[N];
			for (int i = 0; i < temp.length; i++) {
				zip[temp[i]] = true; // true가 1번 false가 2번
			}
			for (int i = 0; i < N; i++) {
				if(!zip[i]) opp[index++] = i; // 다른쪽 배열 완성
			}			
			if(min < ingu(temp,opp)) continue; // 미리 최소값 비교해서 크면 뒤에 검사필요없이 다음 조합 확인
			
			if(bubun(temp) && bubun(opp)) { // 만족하면 값을 비교
				min = Math.min(min, ingu(temp,opp));
			}
		}
	}// end check

	public static int ingu(int[] temp, int[] opp) { // 두 집합의 인구수 차이 확인
		int sumT = 0, sumO = 0;
		for (int i = 0; i < temp.length; i++) {
			sumT += ingusu[temp[i]];
		}
		for (int i = 0; i < opp.length; i++) {
			sumO += ingusu[opp[i]];
		}
		return Math.abs(sumT - sumO);
	}// end ingu

	public static boolean bubun(int[] temp) {
//		make-set();
		parents = new int[N];
		for (int i = 0; i < temp.length; i++) {
			parents[temp[i]] = temp[i];
		} // 초기화
		if(temp.length == 1) return true; // 뽑은 부분집합이 1이면 
		for (int i = 0; i < temp.length; i++) {
			for (int j = i+1; j < temp.length; j++) {					
				if(map[temp[i]][temp[j]] == 1) {
					union(temp[i],temp[j]); // 두 개가 같은 집합이니까 합집합 만들기 
				}
			}
		}
		for (int i = 0; i < temp.length-1; i++) {
			if(find(temp[i]) != find(temp[i+1])) return false; // 시간터지면 여기서 터지는 거 다른게 하나라도있으면 바로 리턴
		}
		return true; // 무사히 돌았으면 트루
	}

	public static void union(int a, int b) { // 합집합
		int rA = find(a);
		int rB = find(b);
		
		if(rA == rB) return;
		parents[rA] = rB;
	}// end union

	public static int find(int a) { // 부모 찾기 메모이제이션 이용 (시간절약)
		if(a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	} // end find

	public static void comb(int n, int r) { // 조합생성
		if(r == 0) {
			int[] arr = trr.clone(); // 항상 arraylist에 넣을 때에는 새 배열에 복사한 후에 넣어야 정상적으로 들어감
			list.add(arr);			
		} else if(n < r) { // 조합의 범위 위배
			return;
		} else {
			if(r == numJohab && arr[n-1] != N-1) return; // N/2개에 앞자리가 다음 자리로 바뀌는 순간 중복된 조합이 생성되기 때문에 앞자리가 N-1이면 리턴
			trr[r-1] = arr[n-1];
			comb(n-1,r-1);
			comb(n-1,r);			
		}
	}
}// end class
