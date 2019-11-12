package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _BOJ_1647_도시분할계획 {
	static class Vertex{
		int start;
		int end;
		int val;
		Vertex(int start, int end, int val){
			this.start = start;
			this.end = end;
			this.val = val;
		}
	}
	static int N, M;
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N+1];
		Vertex[] vertex = new Vertex[M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			vertex[i] = new Vertex(a, b, c);
		}
		makeSet(N);
		for(int i=0; i<M; i++) {
			if(findSet(vertex[i].start) != findSet(vertex[i].end)) {
				union(vertex[i].start, vertex[i].end);
			}
		}
		
		for(int i=1; i<=N; i++) {
			System.out.println(parents[i]);
		}
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
		if(px != py) {
			if(px < py)
				parents[py] = px;
			else
				parents[px] = py;
		}
	}
}
