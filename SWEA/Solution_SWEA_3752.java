package hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWHPkqBqAEsDFAUn&

// 모든 경우의 수가 최대 10000개 0 ~ 10000점 사이
// 입력받을 때 마다 hashset에서 하나씩 꺼내서 값을 더해주면서 넣어주기

// hashset 650ms
// array 180ms
public class Solution_SWEA_3752 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트케이스
		
//		HashSet<Integer> set = new HashSet<Integer>(); // hashset 선언		
		
		for (int testcase = 1; testcase <= T; testcase++) {
			int N = Integer.parseInt(br.readLine()); // 입력받는 자연수의 개수
			
			st = new StringTokenizer(br.readLine()); // 자연수들
			
			boolean[] set = new boolean[10001];
			set[0] = true;
//			set.clear(); // set 초기화
//			set.add(0); // 0은 무조건 포함이니까 추가
			
			for (int i = 0; i < N; i++) {
				int temp = Integer.parseInt(st.nextToken()); // 현재 값
				
//				HashSet<Integer> copy_set = (HashSet<Integer>) set.clone(); 
//				
//				Iterator<Integer> iter = copy_set.iterator(); // 반복자
//				while(iter.hasNext()) {					
//					set.add(temp + iter.next()); // 현재 값과 set안에 값들은 더해서 set에 저장
//				}
				
				for (int j = 10000; j >= 0; j--) { // 정방향으로하면 계속 초기화됨 / 입력받는게 양수라 역순으로 해야함
					if(set[j] == true) {
						set[j+temp] = true;
					}
				}
			}
			int cnt = 0;
			for (int i = 0; i < set.length; i++) {
				if(set[i] == true) {
					cnt++;
				}
			}
			sb.append("#").append(testcase).append(" ").append(cnt).append('\n');
		} // end for testcase
		System.out.println(sb);
	} // end main
}
