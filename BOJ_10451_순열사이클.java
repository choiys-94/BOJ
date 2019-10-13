package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10451_순열사이클 {
	static int N;
	static int[] adj;
	static int[] visited;
	static int result;
	static boolean flag;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			adj = new int[N+1];
			visited = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				int in = Integer.parseInt(st.nextToken());
				adj[i] = in;
			}
			result = 0;
			for(int i=1; i<=N; i++) {
				flag = false;
				dfs(i);
				if(flag)
					result++;
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	static void dfs(int idx) {
		if(visited[idx] >= 1) {
			return;
		}
		flag = true;
		visited[idx]++;
		dfs(adj[idx]);
	}
}
