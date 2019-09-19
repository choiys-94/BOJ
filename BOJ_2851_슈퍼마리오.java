package boj;

import java.util.Scanner;

public class BOJ_2851_슈퍼마리오 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		for(int i=0; i<10; i++) {
			int tmp = sc.nextInt();
			sum += tmp;
			if(sum == 100)
				break;
			else if(sum > 100) {
				if(sum - 100 == 100 - (sum - tmp)) {
					break;
				}
				if(sum - 100 > 100 - (sum - tmp)) {
					sum -= tmp;
					break;
				}
				break;
			}
		}
		
		System.out.println(sum);
	}
}
