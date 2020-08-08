import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_BJ_4195 {
	
	private static int[] parents;
	private static int var = 0;
	private static HashMap<String, Integer> hm;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		hm = new HashMap<String, Integer>();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int testcase = 0; testcase < T; testcase++) {
			int F = Integer.parseInt(br.readLine()); // <= 100000 친구관계 입력
			
			parents = new int[200001]; // 친구 수 최대 범위
			
			for (int i = 0; i < parents.length; i++) {
				parents[i] = i; // 초기화
			}
			
			
			int key;
			int count;
			for (int rel = 0; rel < F; rel++) {
				st = new StringTokenizer(br.readLine()," ");
				String s1 = st.nextToken();
				String s2 = st.nextToken();
				key = union(s1,s2); // 합집합
				count = 0;
				for (int i = 0; i < var; i++) {
					if(key == find(parents[i])) { // 같은 집합인 개수 확인
						count++;
					}
				}
				sb.append(count).append('\n');
			}			
		}// end for testcase
		System.out.println(sb);
	}// end main
	
	/** 합집합 만들기 */
	public static int union(String s1, String s2) {
		int a , b;
		if(!hm.containsKey(s1)) { // 해쉬맵에 키가 없으면 value 넣어주기
			a = var;
			hm.put(s1, var++);			
		}
		else a = hm.get(s1); // 있으면 value 가져오기
		if(!hm.containsKey(s2)) {
			b = var;
			hm.put(s2, var++);			
		}else b = hm.get(s2);
		
		int aR = find(a);
		int bR = find(b);
		
		if(aR == bR) return aR;		
		else {
			parents[bR] = aR;
			return aR;
		}
		
	}// end union
	/** 부모찾아가는 과정 */
	public static int find(int a) {
		if(parents[a] == a) return a;
		else return parents[a] = find(parents[a]);
	}// end find
	
	
}
