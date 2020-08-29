import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1759
public class Main_BJ_1759 {

	private static StringBuilder sb;
	private static char[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		int L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		arr = new char[C];
		char[] result = new char[L];
		boolean[] visited = new boolean[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		// -------------------------------------------------------입력
		Arrays.sort(arr);
		
		comb(result, visited, L, C, 0);
		
		System.out.println(sb);
	}// end main

	public static void comb(char[] result, boolean[] visited, int r, int n, int cnt) {// n개 중에서 r개 뽑기
		if(cnt == r) {
			if(check(result)) {
				for (int i = 0; i < result.length; i++) {
					sb.append(result[i]);
				}
				sb.append('\n');
			}
			return;
		}
		for (int i = cnt; i < n; i++) {
			if(!visited[i]) {				
				result[cnt] = arr[i];
				for (int j = 0; j <= i; j++) { // 미리 정렬해놓고 더 큰 인덱스가오면 다 방문 표시
					visited[j] = true;
				}
				comb(result,visited,r,n,cnt+1);
				for (int j = 0; j <= i; j++) { // 다시 방문 표시 되돌리기
					visited[j] = false;
				}
			}
		}
	}// end comb

	public static boolean check(char[] result) {
		int vow = 0;
		int con = 0;
		for (int i = 0; i < result.length; i++) {
			// 한개의 모음과 두개의 자음
			char tempValue = result[i];
			if(tempValue == 'a' || tempValue == 'e' || tempValue == 'i' || tempValue == 'o' || tempValue == 'u') {
				vow++;
			} else {
				con++;
			}
			if(vow >= 1 && con >= 2) {
				return true;
			}
		}
		return false;
	}// end check

}
