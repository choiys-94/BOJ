package boj;

import java.util.Scanner;

public class _BOJ_3197_백조의호수3 {
	static int R, C, str, stc, edr, edc;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		for(int i=0; i<R; i++) {
			String in = sc.next();
			for(int j=0; j<C; j++) {
				switch(in.charAt(j)) {
				case 'L':
					
					break;
				}
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
}
