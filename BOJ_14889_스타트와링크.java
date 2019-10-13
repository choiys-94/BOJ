package boj;

import java.util.Scanner;

public class BOJ_14889_스타트와링크 {
	static int N;
	static int[][] adj;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		adj = new int[N][N];
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = i;
			for(int j=0; j<N; j++) {
				adj[i][j] = sc.nextInt();
			}
		}
		comb(arr, new int[N/2], 0, 0);
		System.out.println(min);
	}
	
	static int min = Integer.MAX_VALUE;
	
	static void comb(int[] arr, int[] sel, int idx, int k) {
		if(k == N/2) {
			boolean[] tmp = new boolean[N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N/2; j++) {
					if(i == sel[j])
						tmp[i] = true;
				}
			}
			int[] sel2 = new int[N/2];
			int ind = 0;
			for(int i=0; i<N; i++) {
				if(ind == N/2)
					break;
				if(tmp[i] == false) {
					sel2[ind] = i;
					ind++;
				}
			}
			int sum1 = 0;
			int sum2 = 0;
			for(int i=0; i<N/2; i++) {
				for(int j=0; j<N/2; j++) {
					sum1 += adj[sel[i]][sel[j]];
					sum2 += adj[sel2[i]][sel2[j]];
				}
			}
			
			min = Math.min(min, Math.abs(sum1 - sum2));
			return;
		}
		if(arr.length == idx) {
			return;
		}
		
		sel[k] = arr[idx];
		comb(arr, sel, idx+1, k+1);
		comb(arr, sel, idx+1, k);
	}
}
