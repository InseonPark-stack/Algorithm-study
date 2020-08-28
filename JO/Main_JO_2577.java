import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1838&sca=99&sfl=wr_hit&stx=2577
public class Main_JO_2577 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int dish = Integer.parseInt(st.nextToken()); // 2<= <= 3,000,000  회전초밥 벨트에 놓인 접시의 수
		int susi = Integer.parseInt(st.nextToken()); // 2<= <= 3000 초밥의 가짓수
		int conDish = Integer.parseInt(st.nextToken()); // 2<= <= 3000 (<=dish) 연속해서 먹는 접시의 수
		int coupon = Integer.parseInt(st.nextToken()); // 1 <= <= susi 쿠폰 번호
		
		int[] arraySusi = new int[dish]; 
		for (int i = 0; i < dish; i++) {
			arraySusi[i] = Integer.parseInt(br.readLine());
		}
		// ------------------------------------------------------------------- 입력
		int maxsize = 0; // 최대 사이즈
		Queue<Integer> tempQueue = new LinkedList<Integer>(); // 뽑을 초밥 번호 저장 큐
		int[] dedupli = new int[susi+1];
		
		int size = 0; 
		for (int i = 0; i < conDish; i++) { // 일단 접시의 수 만큼 꺼내기
			tempQueue.offer(arraySusi[i]);
			if(++dedupli[arraySusi[i]] == 1) { // 배열인덱스에 맞는 번호 개수 추가
				size++;
			}
		}
		if(++dedupli[coupon] == 1) { // 더했는데 1이면 사이즈 추가
			size++;
		}	
		// 접시의 수 - 연속해서 고르는 접시의 수 뺀 만큼 반복
		for (int i = conDish , index = conDish; i < dish + conDish; i++, index++) {
			if(i == dish) index = 0; // 마지막 인덱스에 도달했을 때 i 값 초기화
			if(--dedupli[tempQueue.poll()] == 0) { // 배열인덱스에 맞는 번호 개수 빼기
				//뺏는데 0이면 size 줄이기
				size--;
			}
			tempQueue.offer(arraySusi[index]);
			if(++dedupli[arraySusi[index]] == 1) { // 배열인덱스에 맞는 번호 개수 추가
				// 아예 없다가 하나 더했을 때 1이면
				size++;
			}
			// -------------- 최대값 찾기
			if(size == conDish+1) {
				maxsize = size;
				break;
			}
			if(maxsize < size) {
				maxsize = size;
			}			
		}
		System.out.println(maxsize);		
	}// end main
}
