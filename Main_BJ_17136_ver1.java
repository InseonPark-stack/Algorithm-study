import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17136
// 틀린거
public class Main_BJ_17136_ver1 {
	private static final int N = 10;
	private static char[][] arr;
	private static int cnt;
	private static ArrayList<int[]> list;
	private static int[] changeArr;
	private static int min, oneCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = st.nextToken().charAt(0);
				if(arr[i][j] == '1') oneCnt++;
			}
		}		
		//-----------------------------------------------입력
		// 5 * 5가 들어갈수 있으면 들어가게하고 0으로 바꾼다 
		// 색종이는 5개가 최대다
		// 먼저 돌아서 5가 들어갈수 있는 좌표 넣어놓고 4가들어갈수 있는 좌표 넣어주고 3이 들어갈수 잇는 좌표 넣어주고 2 ,1  반복
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(arr[i][j] == '1') {
					for (int size = 1; size <= 5; size++) {
						if(i+size > N || j+size > N) continue;
						if(check(i,j,size)) {
							list.add(new int[] {i,j,size});
						}
					}
				}
			}
		}
		
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[2] - o1[2]; // size 대로 정렬
			}
		});
		min = Integer.MAX_VALUE;
		changeArr = new int[6];
		changeArr[0] = list.size();
		if(!list.isEmpty()) {
			int changeIndex = list.get(0)[2]; // 맥스 사이즈
			int now = list.get(0)[2]+1; // 현재 사이즈를 저장할 변수
			int index = 0;
			for (int[] val : list) { // 사이즈가 어느 인덱스에서 바뀌는지
				if(now-1 == val[2]) {
					changeArr[changeIndex--] = index;
					now = val[2];
				}
				index++;
			}// changeArr의 5번째 값은 0 4번째 값은 4크기의 색종이 시작점이 있는 인덱스 ....
		} else {
			min = 0;
		}
		for (int i = 0; i < list.size(); i++) {
			req(i,0);			
		}
		
		System.out.println(min == Integer.MAX_VALUE? -1 : min);
	}// end main

	// 
	public static void req(int index,int nowCnt) { // 색종이 크기,현재크기의 색종이사용개수
		if(list.size() == index) { // 기저조건 (다 썼거나, 1인거 5개 썼을때
			if(oneCnt == 0) {
				if(cnt < min) {
					min = cnt;
				}
			}
			return;
		}
		if(min <= cnt) {
			return;
		}
		int[] val = list.get(index);
		int x = val[0];
		int y = val[1];
		int nowSize = val[2];
		if(index == changeArr[nowSize]) { // 다음 색종이로 넘어왔으면 현재 크기 색종이 개수 초기화
			nowCnt = 0;
		}
		if(check(x,y,nowSize)) { // 가능한지? 불가능한지?
			change(x,y,nowSize); // 색종이 추가로 바꿈
			cnt++; // 색종이 개수 추가
			nowCnt++; // 현재 색종이 개수 추가
			oneCnt -= nowSize * nowSize;
			if(nowCnt < 5) { // 개수가 여유가 될때만
				req(index+1,nowCnt++); // 다음 색종이 추가
			} else if(nowCnt == 5) { // 여유가 안되면 다음 사이즈로 강제로 넘어가기 위해 인덱스를 수정
				req(changeArr[nowSize-1],0);
			}
			nowCnt--;
			cnt--;
			oneCnt += nowSize * nowSize;
			rollback(x,y,nowSize);
		} else { 
			req(index+1,nowCnt); // 다음 색종이 추가
			req(changeArr[nowSize-1],0);
		}
	}// end req

	public static void rollback(int x, int y, int size) { // 0으로 다시 바꾸기
		for (int i = x; i < x+size; i++) {
			for (int j = y; j < y+size; j++) {
				arr[i][j] = '1';
			}			
		}
	} // end rollback

	public static void change(int x, int y, int size) { // 1로 바꾸기
		for (int i = x; i < x+size; i++) {
			for (int j = y; j < y+size; j++) {
				arr[i][j] = '0';
			}			
		}
	}// end change

	public static boolean check(int x, int y, int size) {
		for (int i = x; i < x+size; i++) {
			for (int j = y; j < y+size; j++) {
				if(arr[i][j] != '1') {
					return false;
				}
			}
		}
		return true; // 크기만큼 체크해서 가능하면 true 리턴
	}// end check
}
