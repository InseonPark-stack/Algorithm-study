import java.util.Scanner;

// https://www.acmicpc.net/problem/1038
public class Main_BJ_1038 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int cnt = 0;
		long num = 0L;
		while(true) {
			if(num >= 9876543210L) {
				System.out.println("-1");
				sc.close();
				System.exit(0);
			}
			if(num == 987654322L) { // 987654321
				num = 9876543210L;
			}else if(num == 98765433L) { // 98765432
				num = 876543210L;
			}else if(num == 9876544L) { // 9876543
				num = 76543210L;
			}else if(num == 987655L) { // 987654 다음에 7654321
				num = 6543210L;
			}else if(num == 98766L) { // 98765 다음에 654321이 와야함
				num = 543210L;
			}else if(num == 9877L) {
				num = 43210L;
			}else if(num == 988L) {
				num = 3210L;
			}else if(num == 99L) {
				num = 210L;
			}
			long pre = -1L;
			boolean check = true;
			long quo = num;
			while(quo > 0) {				
				long remain = quo % 10;
				quo /= 10;
				if(pre < remain) {
					pre = remain;					
				}
				else {
					check = false;
					break;
				}
			}
			if(check) {
				if(cnt == N) {
					System.out.println(num);
					sc.close();
					System.exit(0);
				}		
				cnt++;
			}
			num++;
		}// end loop
	}// end main
}
