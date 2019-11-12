package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206_벽부수고이동하기 {
	static class Pos{
		int r, c, time, isBreak;
		Pos(int r, int c, int time, int isBreak){
			this.r = r;
			this.c = c;
			this.time = time;
			this.isBreak = isBreak;
		}
	}
	static int N, M;
	static int[][] map, memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		memo = new int[N][M];
		for(int i=0; i<N; i++) {
			String in = br.readLine().trim();
			Arrays.fill(memo[i], Integer.MAX_VALUE);
			for(int j=0; j<M; j++) {
				map[i][j] = in.charAt(j)-'0';
			}
		}
		
		int ans = 987654321;
		Queue<Pos> queue = new LinkedList<>();
		Queue<Pos> block = new LinkedList<>();
		block.add(new Pos(0, 0, 1, 0));
		while(!block.isEmpty()) {
			Pos b = block.poll();
			queue.clear();
			queue.add(new Pos(b.r, b.c, b.time, b.isBreak));
			while(!queue.isEmpty()) {
				Pos q = queue.poll();
				if(q.r == N-1 && q.c == M-1) {
					ans = Math.min(ans, q.time);
					break;
				}
				
				for(int d=0; d<4; d++) {
					int nr = q.r + dr[d];
					int nc = q.c + dc[d];
					if(!check(nr, nc))
						continue;
					if(memo[nr][nc] <= q.time+1)
						continue;
					if(map[nr][nc] == 1) {
						if(q.isBreak == 1)
							continue;
						block.add(new Pos(nr, nc, q.time+1, 1));
						continue;
					}
					queue.add(new Pos(nr, nc, q.time+1, q.isBreak));
					memo[nr][nc] = q.time+1;
				}
			}
		}
		ans = (ans == 987654321) ? -1 : ans;
		System.out.println(ans);
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
