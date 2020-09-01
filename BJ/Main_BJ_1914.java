import java.util.Scanner;

// https://www.acmicpc.net/problem/1914
public class Main_BJ_1914 {
	private static StringBuilder sb;
	private static int cnt;
	private static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		int N = sc.nextInt(); // 원판
		// 입력----------------------------------------------
		sb = new StringBuilder();
		arr = new int[100];
		if(N > 20) {
			doudou(N);
			System.exit(0);
		}
		hanoi(N,1,2,3);
		System.out.println(cnt);
		System.out.println(sb);
		sc.close();
	}// end main
	/** 2의 N승 출력하기 */
	public static void doudou(int n) {
		arr[0] = 2;
		int carry = 0; // 10이 넘어가면 올라갈 carry값
		for (int i = 0; i < n-1; i++) {
			if(i == 0) arr[i] *= 2;
			for (int j = 0; j < i; j++) {
				arr[j] *= 2;
				arr[j] += carry;
				if(arr[j] >= 10) {
					carry = 1;
					arr[j] -= 10;
				}
				else carry = 0;
			}
		}
		arr[0] -= 1;
		int start = 99;
		for (int i = 99; i > -1; i--) {
			if(arr[start] == 0) {
				start--;
				continue;
			}
			System.out.print(arr[i]);
		}
	}// end doudou
	/** N개의 원판을 A에서 C로 이동시킨다. B를 이용하여*/
	public static void hanoi(int N, int A, int B, int C) { // 원판 개수, 기둥번호
		if(N == 1) { // 종료조건
			cnt++;
			sb.append(A).append(' ').append(C).append('\n');
			return;
		}
		cnt++;
		hanoi(N-1,A,C,B); // A기둥의 n-1개 원판을 B로 옮기는데 C를 이용한다
		sb.append(A).append(' ').append(C).append('\n'); // A를 C로 이동했으니 출력
		hanoi(N-1,B,A,C); // B기둥의 n-1개 원판을 C로 옮기는데 A를 이용한다.
	}// end hanoi
}// end class
