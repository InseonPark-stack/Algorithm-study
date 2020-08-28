import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import com.sun.org.apache.bcel.internal.generic.SWAP;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWAe7XSKfUUDFAUw&categoryId=AWAe7XSKfUUDFAUw&categoryType=CODE
public class Solution_SWEA_3234 {
	private static int N,count;
	private static int[] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine()); // 무게추의 개수
			st = new StringTokenizer(br.readLine());
			map = new int[N];			
			for (int i = 0; i < N; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}
			// ---------------------------------------------------------------------- 입력
			// 오른쪽 저울 무게의 총합이 왼쪽 무게의 총합 보다는 커져선 아니된다.
			// 왼쪽 무게 >= 오른쪽 무게
			// 가지치기 -> 오른쪽이 커질때 마다 break;
			boolean[] visited = new boolean[N];
			count = 0;
			permutation(visited,0,0,0);
			System.out.println("#"+testcase+" "+count);
//			sb.append('#').append(testcase).append(" ").append(count).append("\n");
		} // end for testcase
	} // end main

	public static void permutation(boolean[] visited, int left, int right, int cnt) {
		if(cnt == map.length) {
			count++;
			return;
		}
//		for (int i = 0; i < N; i++) {
//			if(visited[i]) continue;
//			visited[i] = true;
////			int tempLeft = left + map[i];
////			if(tempLeft < right) {
////				visited[i] = false;
////				break;
////			}
//			permutation(visited, left+map[i], right, cnt + 1);
//			if(left >= right + map[i]) {
//				permutation(visited, left, right+map[i], cnt + 1);
//			}
////			int tempRight = right + map[i];
////			if(tempRight > left) {
////				visited[i] = false;
////				continue;
////			}
//			visited[i] = false;		
//		}
		for (int i = cnt; i < visited.length; i++) {
			swap(i,cnt);
			permutation(visited, left+map[cnt], right, cnt+1);
			if(left >= right + map[cnt]) {
				permutation(visited, left, right + map[cnt], cnt +1);
			}
			swap(i,cnt);
		}
	}

	public static void swap(int i, int cnt) {
		int temp = map[i];
		map[i] = map[cnt];
		map[cnt] = temp;
	} // end permutation
}
