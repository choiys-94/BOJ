package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _BOJ_15422_Bumped {
	static final int INF = Integer.MAX_VALUE;
	static int N, M, F, S, T;
	static int[] dist;
	static int[][] adj;
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 도시 개수
		M = Integer.parseInt(st.nextToken());	// 간선 개수
		F = Integer.parseInt(st.nextToken());	// 무료 항공권 개수
		S = Integer.parseInt(st.nextToken());	// 시작점
		T = Integer.parseInt(st.nextToken());	// 도착점
		
		dist = new int[N];
		check = new boolean[N];
		adj = new int[N][N];

		Arrays.fill(dist, INF);
		for(int i=0; i<N; i++) {
			Arrays.fill(adj[i], -1);
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a =Integer.parseInt(st.nextToken());
			int b =Integer.parseInt(st.nextToken());
			int c =Integer.parseInt(st.nextToken());
			adj[a][b] = c;
			adj[b][a] = c;
		}
		
		for(int i=0; i<F; i++) {
			st = new StringTokenizer(br.readLine());
			int a =Integer.parseInt(st.nextToken());
			int b =Integer.parseInt(st.nextToken());
			adj[a][b] = 0;
		}
		
		int idx = S;
		int cnt = 0;
		dist[S] = 0;
		while(cnt < N) {
			check[idx] = true;
			for(int i=0; i<N; i++) {
				if(adj[idx][i] == -1)
					continue;
				dist[i] = Math.min(dist[i], adj[idx][i] + dist[idx]);
			}
			
			long minVal = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				if(check[i])
					continue;
				if(minVal >= dist[i]) {
					minVal = dist[i];
					idx = i;
				}
			}
			cnt++;
		}
		
		System.out.println(dist[T]);
	}
}
