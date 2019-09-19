package boj;

import java.util.Scanner;

public class BOJ_5596_시험점수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		int sum2 = 0;
		for(int i=0; i<4; i++)
			sum += sc.nextInt();
		for(int i=0; i<4; i++)
			sum2 += sc.nextInt();
		
		System.out.println(Math.max(sum, sum2));
	}
}
