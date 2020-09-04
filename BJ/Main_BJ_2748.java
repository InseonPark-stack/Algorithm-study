import java.util.Scanner;

// https://www.acmicpc.net/problem/2748
public class Main_BJ_2748 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] arr = new long[n+1];
		for (long i = 0; i <= n; i++) {
			if(i <= 1) arr[(int)i] = i;
			else {
				arr[(int)i] = arr[(int)i-1] + arr[(int)i-2];
			}
		}
		System.out.println(arr[n]);
		sc.close();
	}// end main
}
