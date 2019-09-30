package boj;

import java.util.Scanner;

public class BOJ_17471_게리맨더링_DFS {
	static int N;
	static int[] p;
	static int[][] adj;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		p = new int[N];
		adj = new int[N][N];
		for(int i=0; i<N; i++) {
			p[i] = sc.nextInt();
		}
		
		for(int i=0; i<N; i++) {
			int n = sc.nextInt();
			for(int j=0; j<n; j++) {
				int a = sc.nextInt()-1;
				adj[i][a] = 1;
				adj[a][i] = 1;
			}
		}
		powerSet(new boolean[N], 0);
		if(MIN == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(MIN);
	}
	
	static void dfs(boolean[] arr, int idx, boolean[] visited, int type) {
		visited[idx] = true;
		for(int i=0; i<N; i++) {
			if(type == 1 && arr[i] == true && visited[i] == false && adj[idx][i] == 1) {
				visited[i] = true;
				dfs(arr, i, visited, 1);
			}
			if(type == 2 && arr[i] == false && visited[i] == false && adj[idx][i] == 1) {
				visited[i] = true;
				dfs(arr, i, visited, 2);
			}
		}
	}
	
	static int MIN = Integer.MAX_VALUE;
	
	static void powerSet(boolean[] arr, int idx) {
		if(arr.length == idx) {
			boolean[] visited = new boolean[N];
			boolean[] visited2 = new boolean[N];
			boolean f1 = true;
			boolean f2 = true;
			for(int i=0; i<N; i++) {
				if(f1 && arr[i] == true && visited[i] == false) {
					dfs(arr, i, visited, 1);
					f1 = false;
				}
				if(f2 && arr[i] == false && visited2[i] == false) {
					dfs(arr, i, visited2, 2);
					f2 = false;
				}
			}
			boolean flag = true;
			int sum1 = 0;
			int sum2 = 0;
			for(int i=0; i<N; i++) {
				if(arr[i] != visited[i]) {
					flag = false;
					break;
				}
				if(visited[i] == visited2[i]) {
					flag = false;
					break;
				}
			}
			if(flag) {
				for(int i=0; i<N; i++) {
					if(arr[i])
						sum1 += p[i];
					else
						sum2 += p[i];
				}
				if(sum1 != 0 && sum2 != 0)
					MIN = Math.min(MIN, Math.abs(sum1-sum2));
			}
			return;
		}
		
		arr[idx] = true;
		powerSet(arr, idx+1);
		arr[idx] = false;
		powerSet(arr, idx+1);
	}
}
