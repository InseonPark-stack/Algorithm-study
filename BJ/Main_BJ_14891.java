import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14891
public class Main_BJ_14891 {
	private static int[][] topni;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		topni = new int[4][8];
		
		// 12시방향부터 순서대로 주어지고 N극은 0 S극은 1
		for (int i = 0; i < topni.length; i++) {
			String s = br.readLine();
			for (int j = 0; j < topni[i].length; j++) {
				topni[i][j] = s.charAt(j)-'0';
			}
		}
		int K = Integer.parseInt(br.readLine()); // 명령 회수 <= 100
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int movingTopniNum = Integer.parseInt(st.nextToken()); // 이동시킬 톱니번호
			int direction = Integer.parseInt(st.nextToken()); // 방향
			
			// ------------------------------------------------------- 입력
			// 동시에 체크를 하고 움직일거 따로 움직이면 되는듯
			// 0-2 == 1-6 / 1-2 == 2-6 / 2-2 == 3-6
			//	1이면 1->2->3->4
			//	2이면 2->1->3->4
			//	3이면 3->4->2->1
			//	4이면 4->3->2->1
			boolean[] check = new boolean[4];
			switch (movingTopniNum) {
			case 1:	
				check[0] = true;
				if(topni[0][2] != topni[1][6]) { // 다르면 회전
					check[1] = true;
				}
				if(topni[1][2] != topni[2][6]) {
					check[2] = true;
				}
				if(topni[2][2] != topni[3][6]) {
					check[3] = true;
				}
				if(check[0]) change(0,direction);
				if(check[1]) change(1,-direction);
				if(check[2]&&check[1]) change(2,direction);
				if(check[1]&&check[3]&&check[2]) change(3,-direction);
				break;
			case 2:
				check[1] = true;
				if(topni[0][2] != topni[1][6]) { // 다르면 회전
					check[0] = true;
				}
				if(topni[1][2] != topni[2][6]) {
					check[2] = true;
				}
				if(topni[2][2] != topni[3][6]) {
					check[3] = true;
				}
				if(check[0]) change(0,-direction);
				if(check[1]) change(1,direction);
				if(check[2]) change(2,-direction);
				if(check[2]&&check[3]) change(3,direction);
				break;
			case 3:
				check[2] = true;
				if(topni[0][2] != topni[1][6]) { // 다르면 회전
					check[0] = true;
				}
				if(topni[1][2] != topni[2][6]) {
					check[1] = true;
				}
				if(topni[2][2] != topni[3][6]) {
					check[3] = true;
				}
				if(check[1]&&check[0]) change(0,direction);
				if(check[1]) change(1,-direction);
				if(check[2]) change(2,direction);
				if(check[3]) change(3,-direction);
				break;
			case 4:
				check[3] = true;
				if(topni[0][2] != topni[1][6]) { // 다르면 회전
					check[0] = true;
				}
				if(topni[1][2] != topni[2][6]) {
					check[1] = true;
				}
				if(topni[2][2] != topni[3][6]) {
					check[2] = true;
				}
				if(check[2]&&check[1]&&check[0]) change(0,-direction);
				if(check[2]&&check[1]) change(1,direction);
				if(check[2]) change(2,-direction);
				if(check[3]) change(3,direction);
				break;				
			}			
		} // end for
		int result = 0;
		if(topni[0][0] == 1) result++;  
		if(topni[1][0] == 1) result+= 2;  
		if(topni[2][0] == 1) result+= 4;  
		if(topni[3][0] == 1) result+= 8;  
		System.out.println(result);
	}// end main

	public static void change(int i, int direction) {
		if(direction == 1) {
			int temp = topni[i][7];
			System.arraycopy(topni[i], 0, topni[i], 1, topni[i].length-1);
			topni[i][0] = temp;
		} else {
			int temp = topni[i][0];
			System.arraycopy(topni[i], 1, topni[i], 0, topni[i].length-1);
			topni[i][7] = temp;
		}
	}// end change
}// end class
