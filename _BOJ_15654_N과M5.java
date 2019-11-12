package boj;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class _BOJ_15654_N과M5 {
	static int N, M;
	static StringBuilder sb;
	static HashSet<Integer> hs;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		sb = new StringBuilder();
		hs = new HashSet<>();
		comb(arr, new int[M], 0, 0);
		System.out.println(sb.toString());
	}
	
	static void comb(int[] arr, int[] sel, int idx, int k) {
		if(k == sel.length) {
			StringBuilder token = new StringBuilder();
			hs.clear();
			int size = -1;
			for(int i=0; i<M; i++) {
				hs.add(sel[i]);
				if(size == hs.size())
					return;
				size = hs.size();
				token.append(sel[i]).append(" ");
			}
			sb.append(token).append("\n");
			return;
		}
		if(idx == N) {
			return;
		}
		
		sel[k] = arr[idx];
		comb(arr, sel, 0, k+1);
		comb(arr, sel, idx+1, k);
	}
}