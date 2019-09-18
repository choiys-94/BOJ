package boj;

import java.util.Scanner;

public class BOJ_5565_영수증 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int sum = 0;
		for(int i=0; i<9; i++) {
			sum += sc.nextInt();
		}
		
		System.out.println(N-sum);
	}
}
