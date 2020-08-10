import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14889 {
	private static int[][] intArray;
	private static int N;
	private static int[] number;
	private static int[][] comArray;
	private static int[] temp;
	private static int comIndex;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // <= 20 N은 짝수
		
		intArray = new int[N+1][N+1];
		comArray = new int[200000][N/2]; // 조합가지수
		comIndex = 0;
		number = new int[N+1];
		temp = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			number[i] = i;
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 1; j <= N; j++) {
				intArray[i][j] = Integer.parseInt(st.nextToken());
			}			
		}
		
		combination(0,1);
		int min = 10000;
		
		// 조합 첫번째와 마지막 인덱스가 세트로 이루어져있음
		for (int j = comIndex-1 ,i = 0; i < comIndex/2 && j >= comIndex/2 ; i++, j--) {
				int statStart = 0;
				int statLink = 0;
				for (int k = 0; k < comArray[i].length; k++) { // 여기도 조합을 짜야하는데 숫자가 안크니까 반복문으로
					for (int o = k+1; o < comArray[i].length; o++) {
						int startX = comArray[i][k];
						int startY = comArray[i][o];
						statStart += (intArray[startX][startY] + intArray[startY][startX]);
					}
				}
				
				for (int k = 0; k < comArray[j].length; k++) {
					for (int o = k+1; o < comArray[j].length; o++) {
						int linkX = comArray[j][k];
						int linkY = comArray[j][o];
						statLink += (intArray[linkX][linkY] + intArray[linkY][linkX]);
					}
				}				
				if(min > abs(statStart - statLink)) {
					min = abs(statStart - statLink);
				}
			}
		
		System.out.println(min);
	}// end main

	public static int abs(int i) {
		return i < 0 ? -i : i;
	}

	private static void combination(int cnt, int start) { // 전체 조합 구하고 반나눠서 스타트팀 링크팀
		if(cnt == N/2){
			System.arraycopy(temp, 0, comArray[comIndex++], 0, N/2);			
			return;
		}
		for (int i = start; i < N+1; i++) {
			temp[cnt] = number[i];
			combination(cnt + 1, i + 1);
		}
	}// end combination
}
