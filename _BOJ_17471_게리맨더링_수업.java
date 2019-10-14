package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _BOJ_17471_게리맨더링_수업 {
	static int N;
	static int[] parents;
	static int[] p;
	static int[][] adj;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		p = new int[N+1];
		adj = new int[N+1][N+1];
		parents = new int[N+1];
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N+1];
		for(int i=1; i<=N; i++) {
			arr[i] = i;
			p[i] = Integer.parseInt(st.nextToken()); 
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			for(int j=0; j<M; j++) {
				int in = Integer.parseInt(st.nextToken());
				adj[i][in] = 1;
				adj[in][i] = 1;
			}
		}
		
		powerSet(arr, new boolean[N+1], 1);
		if(result == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(result);
	}
	
	static int result = Integer.MAX_VALUE;
	
	static void powerSet(int[] arr, boolean[] sel, int idx) {
		if(idx == arr.length) {
			makeSet(N);
			int sum1 = 0;
			int sum2 = 0;
			for(int i=1; i<=N; i++) {
				if(sel[i])
					sum1 += p[i];
				else
					sum2 += p[i];
				for(int j=i+1; j<=N; j++) {
					if(adj[i][j] == 1 && sel[i] == true && sel[j] == true) {
						union(i, j);
					}
					else if(adj[i][j] == 1 && sel[i] == false && sel[j] == false) {
						union(i, j);
					}
				}
			}
			
			int cnt = 0;

			for(int i=1; i<=N; i++) {
				if(parents[i] == i)
					cnt++;
			}
			
			if(cnt == 2) {
				result = Math.min(result, Math.abs(sum1 - sum2));
			}
			
			return;
		}
		
		sel[idx] = true;
		powerSet(arr, sel, idx+1);
		sel[idx] = false;
		powerSet(arr, sel, idx+1);
	}
	
	static void makeSet(int n) {
		for(int i=1; i<=n; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int x) {
		if(x == parents[x])
			return x;
		return parents[x] = findSet(parents[x]);
	}
	
	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if(px != py)
			parents[py] = px;
	}
}