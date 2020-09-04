import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/12865
public class Main_BJ_12865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 물품의 수
		int K = Integer.parseInt(st.nextToken()); // 무게 제한
		
		int[][] item = new int[N+1][2];
		int[][] memo = new int[N+1][K+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			item[i][0] = Integer.parseInt(st.nextToken()); // 무게
			item[i][1] = Integer.parseInt(st.nextToken()); // 가치
		}
		
		for (int i = 1; i <= N; i++) {
			int weight = item[i][0], val = item[i][1];
			for (int j = 0; j <= K; j++) {
				if(j < item[i][0]) memo[i][j] = memo[i-1][j];
				else memo[i][j] = Math.max(memo[i-1][j], memo[i-1][j-weight]+val);
			}
		}
		System.out.println(memo[N][K]);
	}// end main
}
