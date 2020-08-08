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
				parents[i] = -1; // 초기화
			}			
			
			int key;

			for (int rel = 0; rel < F; rel++) {
				st = new StringTokenizer(br.readLine()," ");
				String s1 = st.nextToken();
				String s2 = st.nextToken();
				key = -union(s1,s2); // 합집합				
				sb.append(key).append('\n');
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
				
		// 여기서 리턴되는 부모노드의 값들은 전부 음수이다. 부모 노드들은 자식 노드의 개수를 절대값으로 가지고 있다
		// 자식 노드의 개수가 적은 부모 노드를 자식 노드의 개수가 더 큰 부모 노드에 값을 더해 붙여 넣는다. 그 후 원래 부모노드였던 노드는 자식노드처럼 부모노드를 가르키게됨.
		// 리턴값은 둘 중 부모노드가 된 값을 리턴해주면 사이즈 계산이 완료됨.
		if(aR != bR) {
			if(parents[aR] < parents[bR]) { 
				parents[aR] += parents[bR];
				parents[bR] = aR;
			} else {
				parents[bR] += parents[aR];
				parents[aR] = bR;
			}
		}
		return parents[aR] < 0 ? parents[aR] : parents[bR];
	}// end union
	/** 부모찾아가는 과정 */
	public static int find(int a) {
		if(parents[a] < 0) return a; // 부모 노드의 값이 음수니까 최종 부모노드에 도달하면
		return parents[a] = find(parents[a]); 
	}// end find
	
	
}
