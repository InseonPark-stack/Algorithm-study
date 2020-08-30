import java.util.Scanner;

// https://www.acmicpc.net/problem/10830
public class Main_BJ_10830 {
	private static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 배열 크기
		long B = sc.nextLong(); // 제곱 하는 횟수
		
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		arr = division(arr,B);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j]%1000 + " ");
			}
			System.out.println();
		}
	}// end main

	public static int[][] division(int[][] arr, long b) {
		if(b == 1) {// 1이면
			return arr;
		} else if(b % 2 == 0) {// 짝수 이면 다음 2분할하고 두개 제곱해주기
			int[][] temp = division(arr,b/2);
			return square(temp,temp);
		} else { // 홀수이면 하나 빼서 짝수로 맞추고 두개 제곱해주기
			return square(division(arr,b-1),arr);
		}
		
	}// end division

	private static int[][] square(int[][] tempA, int[][] tempB) {
		int[][] result = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int sum = 0;
				for (int k = 0; k < N; k++) {
					sum += tempA[i][k] * tempB[k][j]; // 행과 열의 곱으로 반복 돌면 가능;
				}
				result[i][j] = sum%1000;
			}
		}
		return result;
	}// return square
}
