import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2263
public class Main_BJ_2263 {
	private static int n;
	private static int[] inorder, postorder;
	private static StringBuilder sb;
	private static int[] pos;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		inorder = new int[n];
		postorder = new int[n];
		pos = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());			
		for (int i = 0; i < n; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			postorder[i] = Integer.parseInt(st.nextToken());
		}
		// -------------------------------------------------------------------- 입력
		for (int i = 0; i < n; i++) {
			pos[inorder[i]] = i;
			// 중위오더의 값은 pos 배열에서 i번재에 위치한다.
		}
		tree(0,n-1,0,n-1);
		
		System.out.println(sb);
	}// end main

	public static void tree(int startIn, int endIn, int startPost, int endPost) {
		if(endIn - startIn < 0 || endPost - startPost < 0) { // 종료조건 (start가 end보다 커지면)
			return;
		}
		int root = postorder[endPost]; // 포스트오더는 항상 루트가 마지막
		sb.append(root).append(' '); // 늘 루트 부터 보고 왼쪽 오른쪽 순서입니다.
		int index = pos[root]; // 인오더에 루트가지고 좌 우 분할을 하기 위해 인덱스를 찾는다
		int length = index - startIn;
		tree(startIn,index-1,startPost,startPost+length-1); // 왼쪽 분할
		tree(index+1,endIn,startPost+length,endPost-1); // 오른쪽 분할
	}// tree
}// end class
