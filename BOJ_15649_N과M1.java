package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_15649_N과M1 {
	static int N, M;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		visited = new boolean[N+1];
		comb(new int[M], 1, 0);
	}
	
	static void comb(int[] sel, int idx, int k) {
		if(k == sel.length) {
			Arrays.fill(visited, false);
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<sel.length; i++) {
				if(visited[sel[i]])
					return;
				visited[sel[i]] = true;
				sb.append(sel[i]).append(" ");
			}
			System.out.println(sb.toString());
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
