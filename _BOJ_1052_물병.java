package boj;

import java.util.Scanner;

public class _BOJ_1052_물병 {
	static int N, K;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 가진 물병 수
		K = sc.nextInt(); // 들고 가야하는 최대 물병 개수
		
		long token = 1;
		int cnt = 0;
		boolean flag = false;
		while(true) {
			if((N+1)/2 <= K) {
				break;
			}
			if(N%2 == 1) {
				N = (N+1)/2;
				cnt += token;
			}
			else
				N /= 2;
			token *= 2;
		}
		if(flag)
			System.out.println(-1);
		else
			System.out.println(cnt);
	}
}
