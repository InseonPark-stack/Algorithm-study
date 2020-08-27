import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWqUzj0arpkDFARG&categoryId=AWqUzj0arpkDFARG&categoryType=CODE
public class Solution_SWEA_7699 {
	private static int R,C,MAX;
	private static char[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken()); // 행 <= 20
			C = Integer.parseInt(st.nextToken()); // 열 <= 20
			MAX = 0;
			
			map = new char[R][];
			
			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] -= 'A';
				}
			}
			char[] alph = new char[1000];
			dfs(0,0,alph,0);
			System.out.println("#"+testcase+" "+MAX);
		}// end for testcase
		
	}// end main

	public static int[] dr = {-1, 1, 0, 0};
	public static int[] dc = { 0, 0,-1, 1};
	
	public static void dfs(int r, int c, char[] pre,int alphIndex) {		
		pre[alphIndex++] = map[r][c]; 
		for (int i = 0; i < dc.length; i++) {
			int mr = r+dr[i];
			int mc = c+dc[i];
			if(mr < 0 || mr >= R || mc < 0 || mc >= C || check(map[mr][mc],pre,alphIndex)) continue;			
			dfs(mr,mc,pre,alphIndex);			
		}		
		if(MAX < alphIndex) MAX = alphIndex;
		if(MAX == 26) return;
	}// end dfs

	public static boolean check(char d, char[] pre, int alphIndex) {
		for (int i = 0; i < alphIndex; i++) {
			if(d == pre[i]) return true;
		}
		return false;
	}// end check
}
