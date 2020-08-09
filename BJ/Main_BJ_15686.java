import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_15686 {
	public static char[][] map;
	private static int[] twoArray, oneArray;
	private static int[][] comArray;
	private static int N,M;
	private static int comIndex,twoIndex, oneIndex;
	private static int[] number;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 2<= <=50 배열 크기
		M = Integer.parseInt(st.nextToken()); // 1<= <=13 남길 치킨 집 개수
		
		map = new char[N][N];
		number = new int[13];
		twoArray = new int[13]; // 치킨집의 좌표를 저장
		oneArray = new int[2*N]; // 집의 좌표를 저장
		twoIndex = 0;
		oneIndex = 0;
		
		
		for (int i = 0; i < N; i++) { // 입력
			String s = br.readLine();
			for (int j = 0, index = 0; j < N; j++ , index += 2) {
				map[i][j] = s.charAt(index);
				if(map[i][j] == '1') {
					oneArray[oneIndex++] = i*N+j;
				}
				if(map[i][j] == '2') {
					twoArray[twoIndex++] = i*N+j;
				}
			}
		}
		
		comArray = new int[10000][M]; // 조합저장할 배열
		comIndex = 0;
		//twoIndex-1에서 M개를 뽑는 조합
		combination(0,0);
		
		int onemin;
		int min = Integer.MAX_VALUE;
		int sum;
		int dis = 0;
		for (int o = 0; o < comIndex; o++) {
			sum = 0;
			for (int i = 0; i < oneIndex; i++) {
				int onex = oneArray[i] / N; // 1의 좌표
				int oney = oneArray[i] % N;
				onemin = Integer.MAX_VALUE;
				for (int j = 0; j < M; j++) {
					int twox = comArray[o][j] / N; // 2의 좌표
					int twoy = comArray[o][j] % N;  
					dis = abs(onex-twox) + abs(oney-twoy);
					if(onemin > dis) { // 1과 2의 최소값을 저장
						onemin = dis;
					}
				}
				sum += onemin; // 거리 저장
			}
			if(min > sum) { // 조합별 최종 거리 비교
				min = sum;
			}
		}
		System.out.println(min);
	}// end main

	public static int abs(int i) {
		return i < 0 ? -i : i;
	}

	public static void combination(int cnt, int start) { // cnt : 뽑은 조합의 개수 , start : 시작점
		if(cnt == M) {
			System.arraycopy(number, 0, comArray[comIndex++], 0, M);
			return;
		}
		for (int i = start; i < twoIndex; i++) {
			number[cnt] = twoArray[i];
			combination(cnt + 1, i + 1);
		}
	}// end combination
}
