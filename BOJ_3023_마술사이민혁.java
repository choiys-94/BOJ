package boj;

import java.util.Scanner;

public class BOJ_3023_마술사이민혁 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int R = sc.nextInt();
		int C = sc.nextInt();
		char[][] result = new char[R*2][C*2];
		for(int i=0; i<R; i++) {
			String _in = sc.next();
			for(int j=0; j<C; j++) {
				char in = _in.charAt(j);
				result[i][j] = in;
				result[R*2-i-1][j] = in;
				result[R*2-i-1][C*2-j-1] = in;
				result[i][C*2-j-1] = in;
			}
		}
		int A = sc.nextInt()-1;
		int B = sc.nextInt()-1;
		if(result[A][B] == '.')
			result[A][B] = '#';
		else
			result[A][B] = '.';
		
		for(int i=0; i<R*2; i++) {
			for(int j=0; j<C*2; j++) {
				System.out.print(result[i][j]);
			}
			System.out.println();
		}
	}
}
