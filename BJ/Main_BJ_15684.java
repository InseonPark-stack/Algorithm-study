import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15684

public class Main_BJ_15684 {
	private static int N, M, H;
	private static int[][] map;
	private static int[][] mapc;
	private static ArrayList<int[]> com;
	private static int R;
	private static int[][] mapr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		N = Integer.parseInt(st.nextToken()); // 세로 선의 개수
		M = Integer.parseInt(st.nextToken()); // 가로 선의 개수
		H = Integer.parseInt(st.nextToken()); // 세로선마다 가로선을 놓을 수 있는 위치의 개수
		
		N = 2*N-1;
		map = new int[H][N];
		mapc = new int[H][N];
		mapr = new int[H][N];
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j+=2) {
				map[i][j] = 1;
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 가로 선의 x좌표
			int b = Integer.parseInt(st.nextToken()); // 가로 선의 y좌표 b+1도 체킹
			map[a-1][2*b-1] = 2;			
		}	
		
		if(M == 0) {
			System.out.println("0");
			System.exit(0);
		}
		
		int[] array = new int[300];
		int arrayInx = 0;
		
		for (int i = 0; i < H; i++) {
			for (int j = 1; j < N; j+=2) {
				if(map[i][j] == 0) {
					if(j-2 < 0 && j+2 >= N) { // 둘다 범위를 벗어난 경우
						array[arrayInx++] = i*N+j;
					} else if((j-2 < 0 && map[i][j+2] == 0) || (j+2 >= N && map[i][j-2] == 0)) { // 둘 중 하나만 범위를 벗어남
						array[arrayInx++] = i*N+j;		
					} else if(j-2 >= 0 && j + 2 < N) { // 둘 다 범위 안에 존재
						if(map[i][j-2] == 0 && map[i][j+2] == 0) {
							array[arrayInx++] = i*N+j;
						}
					}
				}
			}			
		}
		int cnt = 0;
		for (int y = 0; y < N; y+=2) { // 하나도 추가 안했을 때
			for (int i = 0; i < H; i++) { // 맵 복사
				for (int j = 0; j < N; j++) {
					mapr[i][j] = map[i][j];
				}
			}
			if(bfs(0,y) != y) {
				cnt++;
			}
		}
		if(cnt == 0) { // 현재 안맞는 사다리의 개수가 0개면 0출력 종료 2개 이하면 사다리 1개부터 시작 4개 이하면 사다리 2부터시작 6개 이하면 사다리 3부터 시작 그 이상의 사다리 추가는 -1
			System.out.println("0");
			System.exit(0);
		} else if(cnt <= 2) {
			R = 1;
		} else if(cnt <= 4) {
			R = 2;
		} else if(cnt <= 6) {
			R = 3;
		} else {
			System.out.println("-1");
			System.exit(0);
		}
		com = new ArrayList<>();
		// 조합 구하기
		for (int q = R; q <= 3; q++) {
			com.clear();			
			R = q;
			int[] numbers = new int[R];		
			combination(array, numbers, 0, arrayInx, 0); // 일단 한개 뽑기				
			// 조합만큼 반복		
			// 체킹
			for (int[] val : com) {
				boolean check = true;
				for (int i = 0; i < H; i++) { // 맵 복사
					for (int j = 0; j < N; j++) {
						mapc[i][j] = map[i][j];
					}			
				}				
				boolean mapcheck = false;
				for (int i = 0; i < R; i++) { // 조합 배열 꺼내서 확인
					int r = val[i] / N;
					int c = val[i] % N;
					mapcheck =  false;
					if(c-2 < 0 && c+2 >= N) { // 둘다 범위를 벗어난 경우
						mapc[r][c] = 2;
						mapcheck = true;
					} else if((c-2 < 0 && map[r][c+2] == 0) || (c+2 >= N && map[r][c-2] == 0)) { // 둘 중 하나만 범위를 벗어남
						mapc[r][c] = 2;
						mapcheck = true;	
					} else if(c-2 >= 0 && c + 2 < N) { // 둘 다 범위 안에 존재
						if(map[r][c-2] == 0 && map[r][c+2] == 0) {
							mapc[r][c] = 2;
							mapcheck = true;
						}
					}
				}
				if(!mapcheck) continue;
				for (int y = 0; y < N; y+=2) {
					for (int i = 0; i < H; i++) { // 맵 복사
						for (int j = 0; j < N; j++) {
							mapr[i][j] = mapc[i][j];
						}			
					}
					if(bfs(0,y) != y) {
						check = false;
						break;
					}
				}
				if(check) {
					System.out.println(R);
					System.exit(0);
				}
			}			
		}
		System.out.println("-1");
	}// end main

	public static void combination(int[] array, int[] numbers, int start, int arrayInx, int r) {
		if(r == R) {
			int[] temp = new int[R];
			System.arraycopy(numbers, 0, temp, 0, R);
			com.add(temp);
			return;
		}
		
		for (int i = start; i < arrayInx; i++) {
			numbers[r] = array[i];
			combination(array,numbers,i+1,arrayInx,r+1);
		}
	}

	public static int[] dr = {0,0,1};
	public static int[] dc = {-1,1,0};
	
	public static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		int data = 0;
		q.offer(new int[] {x, y});
		mapr[x][y] = -1;
		while(!q.isEmpty()) {
			int[] val = q.poll();
			int r = val[0];
			int c = val[1];
			data = c;
			for (int i = 0; i < dr.length; i++) {
				int mr = r + dr[i];
				int mc = c + dc[i];
				if(mr < 0 || mr >= H || mc < 0 || mc >= N) continue;
				if(mapr[mr][mc] == 2) {
					mapr[mr][mc] = -1;
					q.offer(new int[] {mr, mc});
					break;					
				} else if(mapr[mr][mc] == 1) {
					mapr[mr][mc] = -1;
					q.offer(new int[] {mr, mc});
					break;
				}
			}
		}
		return data;
	}
}
