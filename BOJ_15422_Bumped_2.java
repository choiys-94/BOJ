package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15422_Bumped_2 {
	static class Vertex implements Comparable<Vertex>{
		int start;
		long val;
		Vertex(int start, long val){
			this.start = start;
			this.val = val;
		}
		@Override
		public int compareTo(Vertex o) {
			return Long.compare(this.val, o.val);
		}
		
	}
	static final long INF = Long.MAX_VALUE;
	static int N, M, F, S, T;
	static long[] dist;
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 도시 개수
		M = Integer.parseInt(st.nextToken());	// 간선 개수
		F = Integer.parseInt(st.nextToken());	// 무료 항공권 개수
		S = Integer.parseInt(st.nextToken());	// 시작점
		T = Integer.parseInt(st.nextToken());	// 도착점
		
		dist = new long[N];
		check = new boolean[N];
		ArrayList<Integer[]>[] vertex = new ArrayList[N];
		
		Arrays.fill(dist, INF);
		for(int i=0; i<N; i++) {
			vertex[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a =Integer.parseInt(st.nextToken());
			int b =Integer.parseInt(st.nextToken());
			int c =Integer.parseInt(st.nextToken());
			vertex[a].add(new Integer[] {b, c});
			vertex[b].add(new Integer[] {a, c});
		}
		
		ArrayList<Integer[]> free = new ArrayList<>();
		
		for(int i=0; i<F; i++) {
			st = new StringTokenizer(br.readLine());
			int a =Integer.parseInt(st.nextToken());
			int b =Integer.parseInt(st.nextToken());
			free.add(new Integer[] {a, b});
		}
		
		long ans = Long.MAX_VALUE;
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		dist[S] = 0;
		pq.add(new Vertex(S, 0));
		while(!pq.isEmpty()) {
			Vertex q = pq.poll();
			int idx = q.start;
			if(check[idx])
				continue;
			check[idx] = true;
			for(int i=0; i<vertex[idx].size(); i++) {
				int dst = vertex[idx].get(i)[0];
				int val = vertex[idx].get(i)[1];
				if(!check[dst]) {
					dist[dst] = Math.min(dist[dst], dist[idx] + val);
					pq.add(new Vertex(dst, dist[dst]));
				}
			}
		}
		ans = Math.min(dist[T], ans);
		
		for(Integer[] f: free) {
			Arrays.fill(dist, INF);
			Arrays.fill(check, false);
			dist[S] = 0;
			pq.clear();
			pq.add(new Vertex(S, 0));
			while(!pq.isEmpty()) {
				Vertex q = pq.poll();
				int idx = q.start;
				if(check[idx])
					continue;
				check[idx] = true;
				for(int i=0; i<vertex[idx].size(); i++) {
					int dst = vertex[idx].get(i)[0];
					int val = vertex[idx].get(i)[1];
					if(!check[dst]) {
						if(f[0] == idx) {
							dist[f[1]] = Math.min(dist[f[1]], dist[idx]);
							pq.add(new Vertex(f[1], dist[f[1]]));
						}
						dist[dst] = Math.min(dist[dst], dist[idx] + val);
						pq.add(new Vertex(dst, dist[dst]));
					}
				}
			}
			ans = Math.min(dist[T], ans);
		}
		
		System.out.println(ans);
	}
}
