package boj;

import java.util.Scanner;

public class _BOJ_1188_음식평론가 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double N = sc.nextInt();
		double K = sc.nextInt();
		
		double tmp = Math.round(((K/N)-1)*N);
		System.out.println((int)tmp);
	}
}
