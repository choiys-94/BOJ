package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1922_네트워크연결 {
	static class Node implements Comparable<Node>{
		int start;
		int end;
		int val;
		Node(int start, int end, int val){
			this.start = start;
			this.end = end;
			this.val = val;
		}
		@Override
		public int compareTo(Node o) {
			return this.val - o.val;
		}
	}
	static int N, M;
	static int[][] adj;
	static int[] parents;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine()); // 노드 수 (1 ≤ N ≤ 1000)
		M = Integer.parseInt(br.readLine()); // 간선 수 (1 ≤ M ≤ 100,000)
		parents = new int[N+1];
		Node[] adj = new Node[M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[i] = new Node(a, b, c);
		}
		Arrays.sort(adj);
		makeSet(N);
		long sum = 0;
		for(int i=0; i<M; i++) {
			if(findSet(adj[i].start) != findSet(adj[i].end)) {
				union(adj[i].start, adj[i].end);
				sum += adj[i].val;
			}
		}
		System.out.println(sum);
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
