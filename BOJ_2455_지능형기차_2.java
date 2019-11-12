package boj;

import java.util.Scanner;

public class BOJ_2455_지능형기차_2 {
	static int ans = Integer.MIN_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		for(int i=0; i<4; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			sum += -a + b;
			ans = Math.max(ans, sum);
		}
		
		System.out.println(ans);
	}
}
