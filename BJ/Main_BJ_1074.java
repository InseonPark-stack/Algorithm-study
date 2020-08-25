import java.util.Scanner;

// https://www.acmicpc.net/problem/1074

public class Main_BJ_1074 {
	public static int row, col;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int size = 1 << N; // 2^N
		row = sc.nextInt();
		col = sc.nextInt();
		
		square(0, 0, 0, size);
		
		sc.close();
		
	}
	public static int[] dx = {0, 0, 1, 1};
	public static int[] dy = {0, 1, 0, 1};
	
	public static void square(int x, int y, int count, int size) {
        if(x > row || x+size <= row || y > col || y+size <= col) return;
        
		if(size == 2) {
			for(int i = 0; i < 4; i++) {
				int mx = x + dx[i];
				int my = y + dy[i];
				if(mx == row && my == col) System.out.println(count + i);
			}
			return;
		}
		int newsize = size/2;
		
		// 4분할
		square(x,y,count,newsize); // 0,4,8,12
		square(x,y+newsize,count+(newsize*newsize),newsize); // 1,5,9,13
		square(x+newsize,y,count+(newsize*newsize*2),newsize); // 2,6,10,14
		square(x+newsize,y+newsize,count+(newsize*newsize*3),newsize); //3,7,11,15
	}
}
