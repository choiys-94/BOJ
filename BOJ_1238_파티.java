package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1238_파티 {
	static final int INF = 987654321;
	static int N, M, X;
	static int[] dist;
	static int[] dist2;
	static int[][] adj;
	static int[][] adj2;
	static boolean[] check;
	static boolean[] check2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 학생 수  
		M = Integer.parseInt(st.nextToken()); // 도로 개수
		X = Integer.parseInt(st.nextToken()); // 목적지(출발지)
		
		adj = new int[N+1][N+1];	// 인접행렬
		adj2 = new int[N+1][N+1];	// 인접행렬2(오는길)
		dist = new int[N+1];		// 최단 거리
		dist2 = new int[N+1];		// 최단 거리2(오는길)
		check = new boolean[N+1];	// 방문 여부 체크
		check2 = new boolean[N+1];	// 방문 여부 체크2(오는길)
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a][b] = c;
			adj2[b][a] = c;
		}

		Arrays.fill(dist, INF);
		dist[X] = 0;
		
		int cnt = 0;
		int idx = X;
		while(cnt < N) {
			check[idx] = true;
			for(int i=1; i<=N; i++) {
				if(adj[idx][i] == 0)
					continue;
				dist[i] = Math.min(dist[i], dist[idx] + adj[idx][i]);
			}
			
			int minVal = INF;
			
			for(int i=1; i<=N; i++) {
				if(check[i])
					continue;
				if(dist[i] <= minVal) {
					minVal = dist[i];
					idx = i;
				}
			}
			cnt++;
		}
		
		Arrays.fill(dist2, INF);
		dist2[X] = 0;
		
		cnt = 0;
		idx = X;
		while(cnt < N) {
			check2[idx] = true;
			for(int i=1; i<=N; i++) {
				if(adj2[idx][i] == 0)
					continue;
				dist2[i] = Math.min(dist2[i], dist2[idx] + adj2[idx][i]);
			}
			
			int minVal = INF;
			
			for(int i=1; i<=N; i++) {
				if(check2[i])
					continue;
				if(dist2[i] <= minVal) {
					minVal = dist2[i];
					idx = i;
				}
			}
			cnt++;
		}
		
		int res = Integer.MIN_VALUE;
		
		for(int i=1; i<=N; i++) {
			if(dist[i] == INF || dist2[i] == INF)
				continue;
			if(res < dist[i] + dist2[i]) {
				res = dist[i] + dist2[i];
			}
		}

		System.out.println(res);
	}
}
