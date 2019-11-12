package boj;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1941_소문난칠공주 {
	static final int N = 5;
	static int[][] map;
	static int ans = 0;
	static HashMap<String, Integer> hm;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String in = sc.next();
			for(int j=0; j<N; j++) {
				if(in.charAt(j) == 'Y')
					map[i][j] = 0;
				else
					map[i][j] = 1;
			}
		}
		
		hm = new HashMap<>();
		solve(new boolean[N][N], 0, 0, new PriorityQueue<>());
		
		System.out.println(ans);
	}
	
	static void solve(boolean[][] visited, int total, int cnt, PriorityQueue<Pos> pq) {
		if(total == 7) {
			if(cnt >= 4) {
				String res = new String();
				while(!pq.isEmpty()) {
					Pos q = pq.poll();
					res += q.r;
					res += q.c;
				}
				if(!hm.containsKey(res)) {
					hm.put(res, 1);
					ans++;
				}
			}
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j])
					continue;
				if(total == 0)
					visited[i][j] = true;
				boolean flag = true;
				for(int d=0; d<4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(!check(nr, nc))
						continue;
					if(visited[nr][nc]) {
						flag = false;
						break;
					}
				}
				if(flag && total != 0) {
					continue;
				}

				visited[i][j] = true;
				PriorityQueue<Pos> tpq = new PriorityQueue<>(pq);
				tpq.add(new Pos(i, j));
				if(map[i][j] == 1)
					solve(visited, total+1, cnt+1, tpq);
				else
					solve(visited, total+1, cnt, tpq);
				visited[i][j] = false;
				if(total == 0)
					visited[i][j] = false;
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static class Pos implements Comparable<Pos>{
		int r, c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
		@Override
		public int compareTo(Pos o) {
			if(this.r == o.r)
				return this.c - o.c;
			return this.r - o.r;
		}
	}
}
