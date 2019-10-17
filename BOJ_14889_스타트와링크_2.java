package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_14889_스타트와링크_2 {
	static int N;
	static int[][] adj;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		adj = new int[N+1][N+1];
		int arr[] = new int[N+1];
		for(int i=1; i<=N; i++) {
			arr[i] = i;
			for(int j=1; j<=N; j++) {
				adj[i][j] = sc.nextInt();
			}
		}
		comb(arr, new Integer[N/2], 1, 0);
		
		System.out.println(ans);
	}
	
	static void comb(int[] arr, Integer[] sel, int idx, int k) {
		if(k == sel.length) {
			boolean[] team = new boolean[N+1];
			ArrayList<Integer> start = new ArrayList<>(Arrays.asList(sel));
			ArrayList<Integer> link = new ArrayList<>();
			for(int i=0; i<sel.length; i++) {
				team[sel[i]] = true; 
			}
			for(int i=1; i<=N; i++) {
				if(!team[i]) {
					link.add(i);
				}
			}
			
			int sSum = 0;
			int lSum = 0;
			for(int i=0; i<sel.length; i++) {
				for(int j=0; j<sel.length; j++) {
					if(i==j) continue;
					sSum += adj[start.get(i)][start.get(j)];
					lSum += adj[link.get(i)][link.get(j)];
				}
			}
			ans = Math.min(ans, Math.abs(sSum - lSum));
			return;
		}
		if(idx == N+1) {
			return;
		}
		sel[k] = idx;
		comb(arr, sel, idx+1, k+1);
		comb(arr, sel, idx+1, k);
	}
}
