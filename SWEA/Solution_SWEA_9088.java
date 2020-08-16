package hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW7Oktj6WMQDFAWY&categoryId=AW7Oktj6WMQDFAWY&categoryType=CODE

public class Solution_SWEA_9088 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 입력받는 다이아몬드 개수
			int K = Integer.parseInt(st.nextToken()); // 허용되는 다이아몬드 크기 차이
			
			int[] diaArray = new int[N];
			int max = 1; // 최대값
			int count = 1; // 연결된 다이아의 개수
			
			for (int i = 0; i < N; i++) {
				diaArray[i] = Integer.parseInt(br.readLine()); // 입력				
			}
			Arrays.sort(diaArray); // 정렬
			
ex:			for (int i = 0; i < diaArray.length; i++) {
				int temp = diaArray[i];
				count = 1;
				for (int j = i+1; j < diaArray.length; j++) {
					if(K >= (diaArray[j] - temp)) { // 차이가 K인것들
						count++;
						if(j == diaArray.length-1) { // 마지막까지 검색을 했다면 그 뒤에 i들은 검색할 필요가 없음
							if(max < count) { // 최대값 찾기
								max = count;
							}
							break ex;
						}
					} else { // 정렬이 된 상태니까 아닌 것들은 만나면 바로 빠져나오기
						break;
					}			
				}
				if(max < count) { // 최대값 찾기
					max = count;
				}
			}
			sb.append("#").append(testcase).append(" ").append(max).append('\n');
		}// end testcase for
		System.out.println(sb);
	}// end main

}
