import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2174
public class Main_BJ_2174 {
	private static int[][] map;
	private static int A,B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken()); // 가로
		B = Integer.parseInt(st.nextToken()); // 세로
		
		map = new int[A+1][B+1]; // 맵을 상하로 뒤집어깐다생각하면  위아래만 반대로 생각 하면 됨
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 로봇의 수
		int M = Integer.parseInt(st.nextToken()); // 명령의 수		
		
		Point[] p = new Point[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			p[i] = new Point(i+1,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),st.nextToken().charAt(0));
			map[p[i].x][p[i].y] = i+1;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int robotNum = Integer.parseInt(st.nextToken());
			char offer = st.nextToken().charAt(0);
			int run = Integer.parseInt(st.nextToken());
			switch (offer) {
			case 'L':
 				for (int j = 0; j < run; j++) {
					Point temp = p[robotNum-1];					
					command(temp,'L');
				}
				break;
			case 'R':
				for (int j = 0; j < run; j++) {
					Point temp = p[robotNum-1];					
					command(temp,'R');
				}
				break;
			case 'F':
				for (int j = 0; j < run; j++) {
					Point temp = p[robotNum-1];					
					command(temp,'F');
				}
				break;
			}			
		}
		System.out.println("OK");
	}// end main

	// L : 우 -> 상 , 상 -> 좌 , 좌 -> 하 , 하 -> 우
	// R : 우 -> 하, 하 -> 좌, 좌 -> 상 , 상 -> 우
	private static void command(Point temp, char com) {
		if(com == 'L')
			switch (temp.dir) {
			case 'E':
				temp.dir = 'N';
				break;
			case 'N':
				temp.dir = 'W';
				break;
			case 'W':
				temp.dir = 'S';
				break;
			case 'S':
				temp.dir = 'E';
				break;
			}
		else if(com == 'R') {
			switch (temp.dir) {
			case 'E':
				temp.dir = 'S';
				break;
			case 'S':
				temp.dir = 'W';
				break;
			case 'W':
				temp.dir = 'N';
				break;
			case 'N':
				temp.dir = 'E';
				break;
			}
		} else {
			switch (temp.dir) {
			case 'E':
				moving(temp,0);				
				break;
			case 'S':
				moving(temp,1);
				break;
			case 'W':
				moving(temp,2);
				break;
			case 'N':
				moving(temp,3);
				break;
			}
		}		
	}
	public static int[] dx = {1,0,-1,0};
	public static int[] dy = {0,-1,0,1};
	public static void moving(Point temp, int i) {
		map[temp.x][temp.y] = 0; 
		temp.x += dx[i];				
		temp.y += dy[i];				
		if(1> temp.x || temp.x > A || 1 >temp.y || temp.y > B)  {
			System.out.println("Robot " + temp.num + " crashes into the wall");
			System.exit(0);
		} else if(map[temp.x][temp.y] != 0){
			System.out.println("Robot " + temp.num + " crashes into robot " + map[temp.x][temp.y]);
			System.exit(0);
		}
		map[temp.x][temp.y] = temp.num;
	}// end moving

}// end class
class Point{
	int num;
	int x;
	int y;
	char dir;
	public Point(int num, int x, int y, char dir) {
		this.num = num;
		this.x = x;
		this.y = y;
		this.dir = dir;
	}	
	
}