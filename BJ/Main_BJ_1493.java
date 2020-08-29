import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1493
public class Main_BJ_1493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int length = Integer.parseInt(st.nextToken()); // <= 10^6 가로
		int width = Integer.parseInt(st.nextToken()); // <= 10^6 세로
		int height = Integer.parseInt(st.nextToken()); // <= 10^6 높이
		
		int n = Integer.parseInt(br.readLine()); // <= 20 큐브의 종류
		
		int[] cube = new int[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()," ");
			cube[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		} // 인덱스 : 큐브 변 . 값 : 개수
		
		long before = 0; // 이전에 사용한 큐브의 개수들
		long ans = 0;
		for (int i = n-1; i >=0; i--) { // 반복문을 돌 때마다, 박스를 2^i 2^i 2^i로 분할해서 생각한다.
			// 현재 박스는 before보다 분할을 1번더 시행했으므로 brfore의 개수는 2^3만큼 늘려주어야한다.
			// 예를들어 , 4 * 4 * 4 분할 단계에서 before = 1이었다고 하자.
			// 그러면 , 현재는 2*2*2 분할이 되므로  brfore = 8이 된다.
			before <<= 3;
			
			// 박스를 2^i * 2^i * 2^i 만큼 분할해주고, 전에 박스를 채웠던 큐브의 개수(befor)만큼 빼준다.
			// 만약 분할이 불가능하다면 0이 나올 것이다.
			long possibleCube = (long) (length>>i) * (width>>i) * (height>>i) - before; // 분할된 박스에 큐브에 들어갈 수 있는 최대 값
			long newCube = Math.min((long) cube[i], possibleCube); // 새롭게 박스에 들어온 큐브 // 들어갈 수 있는 만큼 넣기
			
			before += newCube; // 새롭게 추가된 큐브는 다음 반복문에서 과거가 되므로 before에 넣어준다.
			ans += newCube;
		}
		
		if(before == (long) length * width * height) { // 계산한 before가 실제 계산한 부피와 같다면 출력
			System.out.println(ans);
		}else {
			System.out.println(-1);
		}
	}// end main

}
