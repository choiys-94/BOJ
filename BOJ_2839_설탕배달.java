package boj;

import java.util.Scanner;

public class BOJ_2839_설탕배달 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int cnt = 0;
		int min = 987654321;
		for(int i=0; i<=N/5; i++) {
			int sum = N;
			sum -= 5*i;
			if(sum == 0) {
				min = Math.min(min, i);
			}
			else if(sum % 3 == 0) {
				min = Math.min(min, i + sum/3);
			}
		}
		
		if(min == 987654321) {
			System.out.println(-1);
		}
		else {
			System.out.println(min);
		}
	}
}
