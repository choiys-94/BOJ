package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _BOJ_2423_전구를켜라 {
	static class Pos{
		int r, c, time;
		Pos(int r, int c, int time){
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int N, M;
	static int[][] map;
	static int[][] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		dr[0] = new int[] {-1, -1, 0, 0, 1, 1};
		dc[0] = new int[] {0, -1, -1, 1, 1, 0};
		dr[1] = new int[] {-1, -1, 0, 0, 1, 1};
		dc[1] = new int[] {0, 1, 1, -1, -1, 0};
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		memo = new int[N][M];
		int nosol = 0;
		for(int i=0; i<N; i++) {
			String in = br.readLine();
			Arrays.fill(memo[i], Integer.MAX_VALUE);
			for(int j=0; j<M; j++) {
				char ch = in.charAt(j);
				if(ch == '/') {
					if((i == 0 && j == 0) || (i == N-1 && j == M-1)) {
						nosol++;
					}
					map[i][j] = 2;
				}
				else {
					map[i][j] = 1;
				}
			}
		}
		
		if(nosol == 2) {
			System.out.println("NO SOLUTION");
			System.exit(0);
		}
		
		int ans = Integer.MAX_VALUE;
		
		Queue<Pos> rot = new LinkedList<>();
		Queue<Pos> queue = new LinkedList<>();
		rot.add(new Pos(0, 0, 0));
		out: while(!rot.isEmpty()) {
			Pos p = rot.poll();
			if(p.r == N-1 && p.c == M-1)
				continue;
			
			queue.clear();
			queue.add(new Pos(p.r, p.c, p.time));
			if(p.r != 0 && p.c != 0) {
				map[p.r][p.c] = (map[p.r][p.c] == 1) ? 2 : 1; 
			}
			while(!queue.isEmpty()) {
				Pos q = queue.poll();
				if(q.r == N-1 && q.c == M-1) {
					ans = Math.min(ans, q.time);
					break out;
				}
				for(int d=0; d<dr[0].length; d++) {
					int nr = q.r + dr[map[q.r][q.c]-1][d];
					int nc = q.c + dc[map[q.r][q.c]-1][d];
					if(!check(nr, nc))
						continue;
					if(memo[nr][nc] <= q.time)
						continue;
					if(map[q.r][q.c] == 1) {
						if(map[q.r][q.c] == map[nr][nc]) {
							if(q.r-1 == nr && q.c-1 == nc) {
								memo[nr][nc] = q.time;
								queue.add(new Pos(nr, nc, q.time));
							}
							else if(q.r+1 == nr && q.c+1 == nc) {
								memo[nr][nc] = q.time;
								queue.add(new Pos(nr, nc, q.time));
							}
							else {
								rot.add(new Pos(nr, nc, q.time+1));
							}
						}
						else {
							if(q.r-1 == nr && q.c-1 == nc) {
								rot.add(new Pos(nr, nc, q.time+1));
							}
							else if(q.r+1 == nr && q.c+1 == nc) {
								rot.add(new Pos(nr, nc, q.time+1));
							}
							else {
								memo[nr][nc] = q.time;
								queue.add(new Pos(nr, nc, q.time));
							}
						}
					}
					else if(map[q.r][q.c] == 2) {
						if(map[q.r][q.c] == map[nr][nc]) {
							if(q.r-1 == nr && q.c+1 == nc) {
								memo[nr][nc] = q.time;
								queue.add(new Pos(nr, nc, q.time));
							}
							else if(q.r+1 == nr && q.c-1 == nc) {
								memo[nr][nc] = q.time;
								queue.add(new Pos(nr, nc, q.time));
							}
							else {
								rot.add(new Pos(nr, nc, q.time+1));
							}
						}
						else {
							if(q.r-1 == nr && q.c+1 == nc) {
								rot.add(new Pos(nr, nc, q.time+1));
							}
							else if(q.r+1 == nr && q.c-1 == nc) {
								rot.add(new Pos(nr, nc, q.time+1));
							}
							else {
								memo[nr][nc] = q.time;
								queue.add(new Pos(nr, nc, q.time));
							}
						}
					}
				}
			}
			if(p.r != 0 && p.c != 0) {
				map[p.r][p.c] = (map[p.r][p.c] == 1) ? 2 : 1;
			}
		}
		System.out.println(ans);
	}
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[][] dr = new int[2][6];
	static int[][] dc = new int[2][6];
}
