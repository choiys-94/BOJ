package boj;

import java.util.Scanner;

public class BOJ_10798_세로읽기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[][] in = new char[15][15];
		int max = Integer.MIN_VALUE;
		for(int i=0; i<5; i++) {
			in[i] = sc.next().toCharArray();
			max = Math.max(max, in[i].length);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<max; i++) {
			for(int j=0; j<5; j++) {
				try {
					sb.append(in[j][i]);
				} catch(Exception e) {
					
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}
