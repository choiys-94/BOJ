package boj;

import java.util.Scanner;

public class BOJ_2445_별찍기8 {
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		for(int i=0; i<N; i++) {
			for(int j=0; j<=i; j++) {
				System.out.print("*");
			}
			for(int j=0; j<(N-i-1)*2; j++) {
				System.out.print(" ");
			}
			for(int j=0; j<=i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		for(int i=1; i<N; i++) {
			for(int j=0; j<N-i; j++) {
				System.out.print("*");
			}
			for(int j=0; j<i*2; j++) {
				System.out.print(" ");
			}
			for(int j=0; j<N-i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
