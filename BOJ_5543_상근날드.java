package boj;

import java.util.Scanner;

public class BOJ_5543_상근날드 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int bmin = Integer.MAX_VALUE;
		int dmin = Integer.MAX_VALUE;
		
		for(int i=0; i<3; i++) {
			bmin = Math.min(bmin, sc.nextInt());
		}
		
		for(int i=0; i<2; i++) {
			dmin = Math.min(dmin, sc.nextInt());
		}
		
		System.out.println(bmin+dmin-50);
	}
}
