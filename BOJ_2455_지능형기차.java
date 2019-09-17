package boj;

import java.util.Scanner;

public class BOJ_2455_지능형기차 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = 4;
		int sum = 0;
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			sum -= a;
			sum += b;
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
	}
}
