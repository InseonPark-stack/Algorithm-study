import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1965
 * 상자넣기
 */
public class Main_BJ_1965 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());	
		int[] arr = new int[N];
		int[] LIS = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int size = 0;
		for (int i = 0; i < N; i++) {
			int temp = Arrays.binarySearch(LIS, 0, size, arr[i]);
			
			temp = Math.abs(temp)-1;
			LIS[temp] = arr[i];
			System.out.println(Arrays.toString(LIS));
			if(temp == size) {
				++size;
			}
//			LIS[i] = 1;
//			for (int j = 0; j < i; j++) {				
//				if(arr[j] < arr[i] && LIS[i] < 1 + LIS[j]) {
//					LIS[i] = 1 + LIS[j];
//				}
//			}
		}
//		Arrays.sort(LIS);		
//		System.out.println(LIS[N-1]);
		System.out.println(size);
	} // end main
}
