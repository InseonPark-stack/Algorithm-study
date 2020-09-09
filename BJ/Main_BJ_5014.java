import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/5014
public class Main_BJ_5014 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int F = sc.nextInt(); // 총 몇층
		int S = sc.nextInt(); // 현재 내가 있는 층
		int G = sc.nextInt(); // 목표
		int U = sc.nextInt(); // 위로 몇층
		int D = sc.nextInt(); // 아래로 몇층
		
		int[] visited = new int[F+1];
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {S,0});
		while(!q.isEmpty()) {
			int[] val = q.poll();
			int floor = val[0];
			int day = val[1];
			if(floor == G) {
				System.out.println(day);
				System.exit(0);
			}
			int upFloor = floor + U;
			int downFloor = floor - D;			
			if(upFloor <= F && visited[upFloor] == 0) { // 한번도 안 들린 곳
				visited[upFloor] = day+1; // 걸린 횟수 넣기
				q.offer(new int[] {upFloor, day+1});
			}
			if(downFloor >= 1 && visited[downFloor] == 0) {
				visited[downFloor] = day+1;
				q.offer(new int[] {downFloor, day+1});
			}
		}
		System.out.println("use the stairs");
	}// end main
}// end class
