import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_5607 {
	private static int MOD;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			MOD = 1234567891;
			long[] fac = new long[N+1];
			fac[0] = 1;
			for (int i = 1; i <= N; i++) {
				fac[i] = fac[i-1] * i % MOD;
			}
			
			long bottom = (fac[R] * fac[N-R]) % MOD;
			long reBottom = fermat(bottom, MOD - 2);
			
			System.out.println("#"+testcase+" "+(fac[N] * reBottom) % MOD);
		}
	}

	private static long fermat(long n, int x) {
		if (x == 0) return 1;
		long tmp = fermat(n, x / 2);
		long ret = (tmp * tmp) % MOD;
		if(x % 2 == 0) return ret;
		else return (ret * n) % MOD;
	}
}
