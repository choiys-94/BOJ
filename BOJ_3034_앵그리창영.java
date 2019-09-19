package boj;

import java.util.Scanner;

public class BOJ_3034_앵그리창영 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int W = sc.nextInt();
		int H = sc.nextInt();
		double D = Math.sqrt((Math.pow(W, 2) + Math.pow(H, 2)));
		
		for(int i=0; i<N; i++) {
			int tmp = sc.nextInt();
			if(tmp <= D)
				System.out.println("DA");
			else
				System.out.println("NE");
		}
	}
}
