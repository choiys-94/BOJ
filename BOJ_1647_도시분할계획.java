package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647_도시분할계획 {
	static class Vertex implements Comparable<Vertex>{
		int start;
		int end;
		int val;
		Vertex(int start, int end, int val){
			this.start = start;
			this.end = end;
			this.val = val;
		}
		@Override
		public int compareTo(Vertex o) {
			return this.val - o.val;
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
		PriorityQueue<Vertex> vertex = new PriorityQueue<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			vertex.add(new Vertex(a, b, c));
		}
		makeSet(N);
		
		ArrayList<Integer> m = new ArrayList<>();
		long sum = 0;
		while(!vertex.isEmpty()) {
			Vertex v = vertex.poll();
			if(findSet(v.start) != findSet(v.end)) {
				union(v.start, v.end);
				m.add(v.val);
				sum += v.val;
			}
		}
		
		Collections.sort(m);
		
		System.out.println(sum - m.get(m.size()-1));
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
//			if(px < py)
				parents[py] = px;
//			else
//				parents[px] = py;
		}
	}
}
