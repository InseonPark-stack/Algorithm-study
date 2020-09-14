import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main_BJ_1655 {
	public static void main(String[] args) throws NumberFormatException, IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // <= 100,000
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder()); // 중간값부터 내림차순
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(); // 중간값부터 오름차순
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
						
			// 일단 제일 작은 수라 가정하고 maxHeap에 넣기 -> maxHeap중에서 가장 큰 값을 minHeap에 넘겨준 후 -> 중간값을 맞춰야되기 때문에 사이즈 비교를 한다.
			// -> minHeap이 커지는 경우밖에 없기때문에 중간 값을 유지하기 위해 둘의 크기를 같게 한다. -> 둘의 사이즈가 같다면 현재 들어온 수의 개수는 짝수이다. 
			// -> 짝수면 둘 값을 비교해서 작은 값을 넣으라하지만 항상 maxHeap의 top이 minHeap의 top보다 작거나같다. 그러므로 maxHeap의 top을 읽어오면 된다.
			maxHeap.add(num); // 맥스힙에 넣기
			minHeap.add(maxHeap.poll()); // 민힙에 맥스힙 중간값 넣기
			
			if(maxHeap.size() < minHeap.size()) {
				maxHeap.add(minHeap.poll());
			}
			
			sb.append(maxHeap.peek()).append('\n');
		}
		
		System.out.println(sb);
		
	}// end main
}
