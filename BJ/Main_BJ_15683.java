package ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 감시 
// 좌, 우 ,상 ,하 검색하는 메서드 만들고 숫자에 따라서 실행
// 좌 우 상 하 보고 다음꺼 좌 우 상 하 보고 다음꺼 좌 우 상 하 재귀호출하면될듯?
public class Main_BJ_15683 {
	private static int N;
	private static int M;
	private static int min = Integer.MAX_VALUE; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		String s = br.readLine();
		
		N = s.charAt(0) - '0'; //1<=8
		M = s.charAt(2) - '0'; //1<=8
		
		
		int[][] map = new int[N][M];
		int[][] q = new int[8][3]; //q라쓰고 스택처럼쓰기
		int top = 0;
		
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(2*j) - '0';
				if(map[i][j] != 0 && map[i][j] != 6) {
					q[top++] = new int[] {i,j,map[i][j]};
				}
			}
		}
		
		cctvCheck(q, top, map);	
		
		System.out.println(min);
	}// end main
	

	public static void cctvCheck(int[][] stack, int tt, int[][] moveMap) {
		if(tt == 0) {
			int count = 0;
			for (int i = 0; i < moveMap.length; i++) {
				for (int j = 0; j < moveMap[i].length; j++) {
					if(moveMap[i][j] == 0) {
						count++;
					}
				}
			}
			if(min > count) {
				min = count;
			}
			return;
		}
		else {
			int[] val = stack[--tt];
			int x = val[0];
			int y = val[1];
			int cctv = val[2];
			int[][] mov_1 = new int[N][M];
			int[][] mov_2 = new int[N][M];
			int[][] mov_3 = new int[N][M];
			int[][] mov_4 = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					mov_1[i][j] = moveMap[i][j];
					mov_2[i][j] = moveMap[i][j];
					mov_3[i][j] = moveMap[i][j];
					mov_4[i][j] = moveMap[i][j];	
				}
			}
			int[][] temp;
			switch (cctv) {
			case 1:
				cctvCheck(stack,tt,left_move(x,y,mov_1)); // L
				cctvCheck(stack,tt,right_move(x,y,mov_2)); // R
				cctvCheck(stack,tt,top_move(x,y,mov_3)); // T
				cctvCheck(stack,tt,down_move(x,y,mov_4)); // D
				break;
			case 2:
				// L R
				temp = left_move(x, y, mov_1);
				cctvCheck(stack, tt, right_move(x,y,temp));
				// T D
				temp = top_move(x ,y , mov_2);
				cctvCheck(stack, tt, down_move(x,y,temp));
				break;
			case 3:
				//T R
				temp = top_move(x ,y ,mov_1); 
				cctvCheck(stack, tt, right_move(x,y,temp));				
				//R D
				temp = right_move(x ,y ,mov_2); 
				cctvCheck(stack, tt, down_move(x,y,temp));
				//D L
				temp = down_move(x ,y ,mov_3); 
				cctvCheck(stack, tt, left_move(x,y,temp));
				//L T
				temp = left_move(x ,y ,mov_4); 
				cctvCheck(stack, tt, top_move(x,y,temp));
				break;
			case 4:
				//L T R
				temp = left_move(x ,y ,mov_1); 
				temp = top_move(x ,y ,temp);
				cctvCheck(stack, tt, right_move(x,y,temp));
				//T R D
				temp = top_move(x ,y ,mov_2); 
				temp = right_move(x ,y ,temp);
				cctvCheck(stack, tt, down_move(x,y,temp));
				//R D L
				temp = right_move(x ,y ,mov_3); 
				temp = down_move(x ,y ,temp);
				cctvCheck(stack, tt, left_move(x,y,temp));
				//D L T
				temp = down_move(x ,y ,mov_4); 
				temp = left_move(x ,y ,temp);
				cctvCheck(stack, tt, top_move(x,y,temp));
				break;
			case 5:
				temp = down_move(x ,y ,mov_1);
				temp = left_move(x ,y ,temp);
				temp = top_move(x, y, temp);
				cctvCheck(stack, tt, right_move(x,y,temp));				
				break;
			}// end switch
		}
	}// end 재귀

	// left_move
	public static int[][] left_move(int row, int col, int[][] leftMap) {
		while(col >= 1) {
			int perse = leftMap[row][--col];
			if(perse == 6) break;
			if(perse == 0) {
				leftMap[row][col] = -1;
			}
		}
		return leftMap;
	}
	
	
	// right_move
	public static int[][] right_move(int row, int col, int[][] rightMap) {
		while(col < M-1) {
			int perse = rightMap[row][++col];
			if(perse == 6) break;
			if(perse == 0) {
				rightMap[row][col] = -1;
			}
		}
		return rightMap;
	}
	
	// top_move
	public static int[][] top_move(int row, int col, int[][] topMap) {
		while(row >= 1) {
			int perse = topMap[--row][col];
			if(perse == 6) break;
			if(perse == 0) {
				topMap[row][col] = -1;
			}
		}
		return topMap;
	}
	// down_move
	public static int[][] down_move(int row, int col, int[][] downMap) {
		while(row < N-1) {
			int perse = downMap[++row][col];
			if(perse == 6) break;
			if(perse == 0) {
				downMap[row][col] = -1;
			}
		}
		return downMap;
	}
	
}// end class
