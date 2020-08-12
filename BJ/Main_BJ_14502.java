package ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 조합 가지수 최대 37820
public class Main_BJ_14502 {
	private static int N,M;
	private static int[][] map, mapOrg;
	private static int[] twoArray;
	private static int[] loc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		mapOrg = new int[N][M]; // 전체 맵
		map = new int[N][M]; // 복사 맵
		twoArray = new int[10]; // BFS 돌릴 2 시작 좌표
		int[] zeroArray = new int[64]; // 조합검색할 좌표		
		int twoIndex = 0; // BFS 돌릴 2 시작 좌표 인덱스
		int zeroIndex = 0; // 조합검색 좌표의 인덱스
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				mapOrg[i][j] = Integer.parseInt(st.nextToken());
				if(mapOrg[i][j] == 0) {
					zeroArray[zeroIndex++] = i*M + j;
				}
				if(mapOrg[i][j] == 2) {
					twoArray[twoIndex++] = i*M + j;					
				}
			}
		}
		
		int max = 0;
		// 조합 만큼 반복
		loc = new int[3];
		for (int i = 0; i < zeroIndex-2; i++) {
			for (int j = i+1; j < zeroIndex-1; j++) {
				for (int k = j+1; k < zeroIndex; k++) {
					int count = 0;
					loc[0] = zeroArray[i];
					loc[1] = zeroArray[j];
					loc[2] = zeroArray[k];
					// 원본 배열 복사
					for (int t = 0; t < N; t++) {
						System.arraycopy(mapOrg[t], 0, map[t], 0, M);
					}
					// 복사한곳에 벽 입력
					map[loc[0] / M][loc[0] % M] = 1;
					map[loc[1] / M][loc[1] % M] = 1;
					map[loc[2] / M][loc[2] % M] = 1;
					for (int tw = 0; tw < twoIndex; tw++) {//2 시작 배열부터 꺼내서 bfs돌리기
						bfs(twoArray[tw] / M, twoArray[tw] % M);
					}
					
					// 다돌고 0 개수 체크 후 최대값 비교
					for (int x = 0; x < N; x++) {
						for (int y = 0; y < M; y++) {
							if(map[x][y] == 0) {
								count++;
							}
						}
					}
					if(max < count) max = count;
				}
			}
		}		
		
		System.out.println(max);
		
	}// end main
	
	// 상하좌우 이동 배열
	public static int[] dr = {-1, 1, 0, 0};
	public static int[] dc = { 0, 0,-1, 1};
	
	
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x ,y});
		while(!q.isEmpty()) {
			int[] val = q.poll();
			int r = val[0];
			int c = val[1];
			for (int i = 0; i < dr.length; i++) {
				int mr = r + dr[i];
				int mc = c + dc[i];
				if(mr < 0 || mr >= N || mc < 0 || mc >= M) continue;
				if(map[mr][mc] == 0) {
					q.offer(new int[] {mr, mc});
					map[mr][mc] = 2;
				}
			}
		}		
	}// end bfs
}
