package ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_16234 {
	
	private static int[] parents,sum;
	private static int[][] map;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(st.nextToken()); // 1<= <=50 배열크기
		int L = Integer.parseInt(st.nextToken()); // L 이상 1<= <=100
		int R = Integer.parseInt(st.nextToken()); // R 이하 1<= <=100
		
		map = new int[n][n];
		parents = new int[n*n];
		sum = new int[n*n];
		
		for (int i = 0; i < n; i++) { // 입력
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sum[n*i+j] = map[i][j];
			}
		}
		for (int i = 0; i < n*n; i++) { // 할때마다 초기화
			parents[i] = -1;	// 대표자 하나만 가진 집합 생성		
		}
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = { 0, 0,-1, 1};

		int result = 0;
//		같은 연합끼리 같은 번호를 부여하고 같은 값으로 나누기
		while(true) {
			boolean bbbb = false;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int k = 0; k < dx.length; k++) {
						int x = i + dx[k];
						int y = j + dy[k];
						if(x < 0 || y < 0 || x >= n || y >= n) continue;
						int sub = map[x][y] - map[i][j];
						sub = sub < 0 ? -sub : sub;
						if(L <= sub && sub <= R) { // 주변 값과의 차이가 L과 R사이냐
							union((n*i+j),(n*x+y));// union 사이이면 합쳐라
							bbbb = true;
						}
					}
				}
			}
			if(!bbbb) break; // 합집합을 한번도 안했으면 나가기
			
			result++;
			
			for (int k = 0; k < n*n; k++) { // 계산
				int perse = findSet(k);	
				map[k/n][k%n] = sum[perse] / (parents[perse]*(-1));
			}
			
			for (int k = 0; k < n*n; k++) { // 초기화
				sum[k] = map[k/n][k%n];
				parents[k] = -1;
			}
		}
		
		System.out.println(result);
		
	}// end main
	
	/** 두 집합을 합치기 */
	public static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) { // 같은 그룹이면
			return;
		} 
		// 최종 부모들을 찾았을 때, 가리키는 값이 더 큰놈한테 합병, 그리고 최종 부모 index에 덧셈하여 합병
		if(parents[aRoot] < parents[bRoot]) { 
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
			sum[aRoot] += sum[bRoot];
		} else {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
			sum[bRoot] += sum[aRoot];
		}		
	}// end union	

	/** 대표자 찾기 */
	public static int findSet(int a) {
		while(parents[a] >= 0) {
			a = parents[a]; // 최상의 부모를 찾아가는 과정
		}
		return a;
	}//end findSet
}
