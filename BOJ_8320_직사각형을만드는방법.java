package boj;

import java.util.Scanner;

public class BOJ_8320_직사각형을만드는방법 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int sum = 0;
		for(int i=2; i<=N; i++) {
			if(Math.sqrt(i) - (int)Math.sqrt(i) == 0) {
				sum += 1;
				for(int j=i+(int)Math.sqrt(i); j<=N; j+=Math.sqrt(i)) {
					sum += 1;
				}
			}
		}
		
		System.out.println(N + sum);
	}
}
