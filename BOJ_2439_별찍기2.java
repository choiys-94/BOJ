package boj;

import java.util.Scanner;

public class BOJ_2439_별찍기2 {
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N-i-1; j++) {
				System.out.print(" ");
			}
			for(int j=0; j<=i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
