package boj;

import java.util.Scanner;

public class BOJ_10707_수도요금 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();//A사 1리터당 수도요금
		int B = sc.nextInt();//B사 기본요금
		int C = sc.nextInt();//B사 상한량
		int D = sc.nextInt();//1리터당 추가요금
		int P = sc.nextInt();//사용량
		
		int a_sum = A*P;
		int b_sum = B;
		if(C < P) {
			b_sum += (P-C)*D;
		}
		
		System.out.println(Math.min(a_sum, b_sum));
	}
}
