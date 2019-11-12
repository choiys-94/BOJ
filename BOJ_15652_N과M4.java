package boj;

import java.util.Scanner;

public class BOJ_15652_N과M4 {
	static int N, M;
	static StringBuilder sb;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		sb = new StringBuilder();
		visited = new boolean[N+1];
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
		comb(sel, idx, k+1);
		comb(sel, idx+1, k);
	}
}