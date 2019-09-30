package boj;

import java.util.HashSet;
import java.util.Scanner;

public class BOJ_17471_게리맨더링 {
	static int N;
	static int[] parents;
	static int[] population;
	static int[][] adj;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();					// 구역 수 입력
		population = new int[N+1];			// 인구 수 할당
		parents = new int[N+1];				// 부모배열 할당
		adj = new int[N+1][N+1];	// 인접행렬 선언
		for(int i=1; i<=N; i++) {
			population[i] = sc.nextInt();	// 인구 수 입력
		}
		for(int i=1; i<=N; i++) {
			int n = sc.nextInt();			// 인접 구역 수
			for(int j=0; j<n; j++) {
				int a = sc.nextInt();		// 인접 구역 정보
				// 인접 배열 할당
				adj[i][a] = 1;	
				adj[a][i] = 1;
			}
		}
		
		powerSet(new boolean[N+1], 1);
		if(MIN == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(MIN);
		}
	}
	
	static int MIN = Integer.MAX_VALUE;
	
	static void powerSet(boolean[] arr, int idx) {
		if(idx == arr.length) {
			int sum1 = 0;
			int sum2 = 0;
			makeSet(N);
			for(int i=1; i<=N; i++) {
				for(int j=i+1; j<=N; j++) {
					// 한 그룹이고, 연결되어있다면?
					if(adj[i][j] == 1 && arr[i] == true && arr[j] == true) {
						union(i, j);
					}
					// 다른 한 그룹이고, 연결되어있다면?
					else if(adj[i][j] == 1 && arr[i] == false && arr[j] == false) {
						union(i, j);
					}
				}
			}
			HashSet<Integer> result = new HashSet<>();
			for(int i=1; i<=N; i++) {
				result.add(find(parents[i]));
				if(arr[i] == true) sum1 += population[i];
				else sum2 += population[i];
			}
			if(result.size() == 2) {
				MIN = Math.min(MIN, Math.abs(sum1-sum2));
			}
			return;
		}
		
		arr[idx] = true;
		powerSet(arr, idx+1);
		arr[idx] = false;
		powerSet(arr, idx+1);
	}
	
	static void makeSet(int n) {
		for(int i=1; i<=n; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int x) {
		if(x == parents[x])
			return x;
		parents[x] = find(parents[x]);
		return parents[x];
	}
	
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px != py)
			parents[py] = px;
	}
}
