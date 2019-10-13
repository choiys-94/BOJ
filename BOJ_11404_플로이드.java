package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11404_플로이드 {
	static final int INF = 999999;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] adj = new int[N+1][N+1];
		int a = 0, b = 0, c = 0;
		
		for(int i=1; i<=N; i++) {
			Arrays.fill(adj[i], INF);
			adj[i][i] = 0;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adj[a][b] = Math.min(adj[a][b], c);
		}
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(adj[i][j] != 0)
						adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(adj[i][j] == INF)
					sb.append("0 ");
				else
					sb.append(adj[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
