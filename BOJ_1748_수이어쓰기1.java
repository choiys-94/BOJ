package boj;

import java.util.Scanner;

public class BOJ_1748_수이어쓰기1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr = {0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
		//				1			2		3		4		5		6	7	8	9
		
		long sum = 0;
		if(N < 10) {
			System.out.println(N);
			System.exit(0);
		}
		for(int i=9; i>=1; i--) {
			if(N % arr[i] != N) {
				for(int j=0; j<i-1; j++) {
					sum += 9*Math.pow(10, j)*(j+1);
				}
				N -= Math.pow(10, i-1);
				sum += (N+1)*i;
				break;
			}
		}
		
		System.out.println(sum);
	}
}
