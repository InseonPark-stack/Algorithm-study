import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2580
public class Main_BJ_2580 {
	private static int[][] map;
	private static ArrayList<int[]> list;
	private static final int N = 9;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[N][N];
		list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) list.add(new int[] {i,j});
			}
		}
		
		// ------------------------------------------------------------- 입력
		// 스도쿠 빈 칸 채우기
		
		sudoku(0);
	}// end main

	public static void sudoku(int index) {
		if(index == list.size()) {
			for (int v = 0; v < N; v++) {
				for (int c = 0; c < N; c++) {				
					System.out.print(map[v][c] + " ");
				}			
				System.out.println();
			}
			System.exit(0);
		}
		
		int[] val = list.get(index);
		int x = val[0];
		int y = val[1];
		for (int i = 1; i <= N; i++) {
			if(check(x,y,i)) {
				map[x][y] = i;
				sudoku(index+1);
				map[x][y] = 0;
			}
		}
	}// end sudoku

	public static boolean check(int x, int y, int q) {
		for (int i = 0; i < N; i++) {
			if(map[x][i] == q) return false;
		}
		for (int i = 0; i < N; i++) {
			if(map[i][y] == q) return false;			
		}
		int threeX = (x / 3) * 3;
		int threeY = (y / 3) * 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(map[threeX+i][threeY+j] == q) return false;
			}
		}
		return true;
	}

}
