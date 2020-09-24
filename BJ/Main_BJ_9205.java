import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/9205
 * 맥주 마시면서 걸어가기
 */
public class Main_BJ_9205 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()); // <= 50
		for (int testcase = 0; testcase < T; testcase++) {
			int N = Integer.parseInt(br.readLine()); // <=100 편의점 개수 n
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			
			boolean[] visited = new boolean[N];
			Queue<int[]> q = new LinkedList<int[]>();
			q.offer(new int[] {startX, startY});
			boolean check = false;
			while(!q.isEmpty()) {
				int[] val = q.poll();
				int x = val[0];
				int y = val[1];
				if(Math.abs(endX - x) + Math.abs(endY - y) <= 1000) {
					check = true;
					break;				
				}
				for (int i = 0; i < arr.length; i++) {
					if(Math.abs(arr[i][0] - x) + Math.abs(arr[i][1] - y) <= 1000 && !visited[i]) {
						q.offer(new int[] {arr[i][0],arr[i][1]});
						visited[i] = true;
					}
				}
			}
			System.out.println(check ? "happy" : "sad");
		}
	}// end class
}
