package boj;

import java.util.Scanner;

public class BOJ_14501_퇴사_2 {
	static int N;
	static int[] day;
	static int[] price;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		day = new int[N+1];
		price = new int[N+1];
		for(int i=1; i<=N; i++) {
			day[i] = sc.nextInt();
			price[i] = sc.nextInt();
		}
		
		solve(1, 0);
		System.out.println(ans);
	}
	
	static void solve(int idx, int sum) {
		if(idx == N+1) {
			ans = Math.max(ans, sum);
			return;
		}
		if(idx > N+1) {
			return;
		}
		
		solve(idx + day[idx], sum + price[idx]);
		solve(idx + 1, sum);
	}
}
