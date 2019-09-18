package boj;

import java.util.Scanner;

public class BOJ_5063_TGN {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		for(int i=0; i<N; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			
			if( a < b - c) {
				System.out.println("advertise");
			}
			else if( a == b - c ) {
				System.out.println("does not matter");
			}
			else {
				System.out.println("do not advertise");
			}
		}
	}
}
