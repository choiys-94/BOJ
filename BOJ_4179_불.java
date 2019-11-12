package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179_불 {
	static class Pos{
		int r;
		int c;
		int time;
		Pos(int r, int c, int time){
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int N, M;
	static int[][] map;
	static int str, stc;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		Queue<Pos> fire = new LinkedList<>();
		for(int i=0; i<N; i++) {
			String in = br.readLine();
			for(int j=0; j<M; j++) {
				char ch = in.charAt(j);
				if(ch == '#') {
					map[i][j] = -1;
				}
				else if(ch == 'J') {
					str = i;
					stc = j;
				}
				else if(ch == 'F') {
					map[i][j] = 1;
					fire.add(new Pos(i, j, 1));
				}
			}
		}
		
		while(!fire.isEmpty()) {
			Pos q = fire.poll();
			
			for(int d=0; d<4; d++) {
				int nr = q.r + dr[d];
				int nc = q.c + dc[d];
				
				if(!check(nr, nc))
					continue;
				if(map[nr][nc] != 0)
					continue;
				map[nr][nc] = q.time+1;
				fire.add(new Pos(nr, nc, q.time+1));
			}
		}
		
		ans = Integer.MAX_VALUE;
		Queue<Pos> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		visited[str][stc] = true;
		queue.add(new Pos(str, stc, 1));
		while(!queue.isEmpty()) {
			Pos q = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = q.r + dr[d];
				int nc = q.c + dc[d];
				if(!check(nr, nc)) {
					ans = Math.min(ans, q.time);
					continue;
				}
				if(visited[nr][nc])
					continue;
				if(map[nr][nc] != 0 && (map[nr][nc] == -1 || map[nr][nc] <= q.time+1))
					continue;
				visited[nr][nc] = true;
				queue.add(new Pos(nr, nc, q.time+1));
			}
		}
		
		if(ans == Integer.MAX_VALUE)
			System.out.println("IMPOSSIBLE");
		else
			System.out.println(ans);
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
