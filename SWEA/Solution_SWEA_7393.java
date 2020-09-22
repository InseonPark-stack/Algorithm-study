import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_SWEA_7393 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			int N = Integer.parseInt(br.readLine());
			
			long[][][] dp = new long[N+1][10][1024]; // i 자리수 j 끝나는 숫자 k 비트마스크
			
			long sum = 0;
			long MOD = 1000000000;
			
			int status;
			
			for (int i = 1; i < 10; i++) dp[1][i][1<<i]=1;
			
			for (int i = 2; i <= N; i++) {
				for (int j = 0; j <= 9; j++) {
					for (int k = 0; k < 1024; k++) {
						status = k | (1<<j);						
						if(j > 0) {
							dp[i][j][status] += dp[i-1][j-1][k] % MOD;
						}
						if(j < 9) {
							dp[i][j][status] += dp[i-1][j+1][k] % MOD;
						}
					}
				}
			}
			for (int i = 0; i < 10; i++) {
				System.out.println(dp[N][i][1023]% MOD);
				sum = (sum + dp[N][i][1023]) % MOD;
			}
			
			System.out.println("#"+testcase+" "+sum);
		}
	}
}
