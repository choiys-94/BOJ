package boj;

import java.util.Scanner;

public class BOJ_2909_캔디구매 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double N = sc.nextInt();
		double K = Math.pow(10, sc.nextInt());
		
		N /= K;
		N = Math.round(N);
		
		System.out.println((int)(N*K));
	}
}
