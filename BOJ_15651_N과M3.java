package boj;

import java.util.Scanner;

public class BOJ_15651_N과M3 {
	static int N, M;
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		sb = new StringBuilder();
		comb(new int[M], 1, 0);
		System.out.println(sb.toString());
	}
	
	static void comb(int[] sel, int idx, int k) {
		if(k == sel.length) {
			for(int i=0; i<M; i++) {
				sb.append(sel[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		if(idx == N+1) {
			return;
		}
		
		sel[k] = idx;
		comb(sel, 1, k+1);
		comb(sel, idx+1, k);
	}
}