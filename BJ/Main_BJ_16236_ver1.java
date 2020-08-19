package hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://www.acmicpc.net/problem/16236
// 아기 상어는 큰 물고기가 있는 칸은 갈 수 없다. 처음 크기는 2 작은 물고기만 먹을 수 있다, 같은 물고기는 지나갈수만 있다
// 먹을수 있는 물고기 없으면 엄마상어에게 도움을 요청, 먹을 수 있는 물고기가 한마리면 그 물고기를 먹는다.
// 1마리보다 많다면 가장 가까운 물고기를 먹으러 간다. 가장 위에 있는 물고기 에서 왼쪽우선 순으로
// 크기만큼의 물고기를 먹어야 크기가 커진다.

public class Main_BJ_16236 {
	private static int N;
	private static int[][] map;
	private static int pre;
	private static boolean[][] visited;
	private static int sharkX,sharkY;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 배열의 크기
		
		map = new int[N][N]; // 상태 저장 배열
		
		sharkX = 0;
		sharkY = 0; // 시작 좌표
		for (int i = 0; i < N; i++) { // 입력
			String s = br.readLine();
			for (int j = 0, index = 0; j < N; j++, index+=2) { 
				map[i][j] = s.charAt(index)-'0';
				if(map[i][j] == 9) {
					sharkX = i;
					sharkY = j;
				}
			}
		} // end 입력
	
		pre = 2; // 현재 상어의 크기
		int cnt = 0; // 먹은 물고기의 수
		int day = 0; // 걸리는 시간
		
		while(true) {
			List<Dis> list = new ArrayList<>(); // 저장할 배열
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] >= 1 && map[i][j] < pre && map[i][j] != 9) { // 상어의 크기가 10이상이 되면 자기자신을 bfs로 하기 때문에 항상 우선순위가 가장 높아짐 자기자신을 선택하는 경우는 뺴야함
						visited = new boolean[N][N]; // 방문확인 배열 초기화
						Dis val = bfs(i,j); // 아기 상어까지 최단 거리로 가는 bfs
						if(val == null) continue; // 막혀서 목적지에 도달 못했으면
						list.add(val);
					}
				}
			}
			if(list.isEmpty()) { // 막혀서 갈수 없거나 작은 크기의 물고기가 없거나 하면 엄마 상어 호출하고 종료
				System.out.println(day);
				break;
			}
			
			Collections.sort(list); // 우선순위로 정렬, day, x, y 오름차순
			
			// 이동
			Dis first = list.get(0); // 가장 우선순위가 높은놈
			map[sharkX][sharkY] = 0; // 원래 있던 위치 0으로 초기화
			sharkX = first.x; // 물고기 위치로 초기화
			sharkY = first.y; 
			map[sharkX][sharkY] = 9; // 물고기 위치에 상어 위치
			day += first.day; // 걸린 초 더하기
			cnt++; // 먹은 상어 수 더하기
			
			if(pre == cnt) {
				pre++; // 상어 크기 늘려주고
				cnt = 0; // 먹은 물고기 수 초기화
			}
		}// end loop
	}// end main
	public static int[] dx = {-1,1,0,0};
	public static int[] dy = {0,0,-1,1};
	
	public static Dis bfs(int i, int j) {
		Dis d = new Dis(i, j, 0);
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i, j, 0});
		visited[i][j] = true;
		while(!q.isEmpty()) {
			int[] val = q.poll();
			int x = val[0];
			int y = val[1];
			int day = val[2];
			if(x == sharkX && y == sharkY) { // 초기 위치에 도착했을 때
				d.day = day;
				return d;
			}
			for (int k = 0; k < dx.length; k++) {
				int mx = x + dx[k];
				int my = y + dy[k];
				if(mx < 0 | mx > N-1 | my < 0 | my > N-1) continue;
				if(visited[mx][my]) continue;
				if(map[mx][my] <= pre || map[mx][my] == 9) { // 이동할수 있어야 진행
					visited[mx][my] = true;
					q.offer(new int[] {mx,my,day+1});
				}
			}
		}
		return null; // 막혀서 도달 못했으면
	}

	static class Dis implements Comparable<Dis>{
		int x;
		int y;
		int day;
		public Dis() {}
		
		public Dis(int x, int y, int day) {
			this.x = x;
			this.y = y;
			this.day = day;
		}

		@Override
		public int compareTo(Dis o) {
			if(this.day == o.day) {
				if(this.x == o.x) {
					return this.y - o.y;
				}
				return this.x - o.x;
			}
			return this.day - o.day;
		}
	}
}
