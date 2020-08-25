import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1992

public class Main_BJ_1992 {
	private static char[][] map;
	private static int N;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine()); // 배열크기
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		square(0,0,N);
		
		System.out.println(sb);
	}

	public static void square(int x, int y, int size) {
		boolean check = true;
		if(size >= 2) {
			char temp = map[x][y];
			for (int i = x; i < x+size; i++) {
				for (int j = y; j < y+size; j++) {
					if(map[i][j] != temp) {
						check = false;
					}				
				}
			}
		}
		
		if(check) {
			if(map[x][y] == '0') {
				sb.append('0');
			}
			else sb.append('1');
			return;
		} else {
			if(size == 1) return;
			sb.append('(');
			int newsize = size/2;
			square(x,y,newsize);
			square(x,y+newsize,newsize);	
			square(x+newsize,y,newsize);			
			square(x+newsize,y+newsize,newsize);
			sb.append(')');
		}
	}
}
