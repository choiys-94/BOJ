package boj;

import java.util.Scanner;

public class BOJ_5585_거스름돈 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = 1000 - sc.nextInt();
		int cnt = 0;
		
		cnt += N/500;
		N %= 500;
		
		cnt += N/100;
		N %= 100;
		
		cnt += N/50;
		N %= 50;
		
		cnt += N/10;
		N %= 10;
		
		cnt += N/5;
		N %= 5;
		
		cnt += N/1;
		
		System.out.println(cnt);
	}
}
