package boj;

import java.util.Scanner;

public class BOJ_1789_수들의합 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long N = sc.nextLong();
		
		long idx = 1L;
		while(true) {
			long sum = (long)(idx)*(idx+1);
			if(sum > N*2) {
				break;
			}
			idx++;
		}
		idx--;
		System.out.println(idx);
	}
}
