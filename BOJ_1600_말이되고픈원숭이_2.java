package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1600_말이되고픈원숭이_2 {
	static class Pos{
		int r;
		int c;
		int time;
		int remain;
		int left;
		Pos(int r, int c, int remain, int time){
			this.r = r;
			this.c = c;
			this.remain = remain;
			this.time = time;
			this.left = Math.abs(N-1 - this.r) + Math.abs(M-1 - this.c);
		}
	}
	
	static int K, N, M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		K = sc.nextInt();
		M = sc.nextInt();
		N = sc.nextInt();
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 0)
					map[i][j] = 987654321;
				if(map[i][j] == 1)
					map[i][j] = -1;
			}
		}
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(0, 0, K, 0));
		map[0][0] = 0;
		boolean[][][] visited = new boolean[N][M][K+1];
		for(int i=1; i<=K; i++)
			visited[0][0][i] = true;
		while(!queue.isEmpty()) {
			Pos q = queue.poll();
			if(q.r == N-1 && q.c == M-1) {
				ans = Math.min(ans, q.time);
				break;
			}
			if(map[q.r][q.c] < q.time)
				continue;
			
			if(q.remain > 0) {
				for(int d=0; d<ddr.length; d++) {
					int nr = q.r + ddr[d];
					int nc = q.c + ddc[d];
					if(!check(nr, nc))
						continue;
					if(visited[nr][nc][q.remain-1])
						continue;
					if(map[nr][nc] == -1)
						continue;
					map[nr][nc] = q.time+1;
					visited[nr][nc][q.remain-1] = true;
					queue.add(new Pos(nr, nc, q.remain-1, q.time+1));
				}
			}
			
			for(int d=0; d<dr.length; d++) {
				int nr = q.r + dr[d];
				int nc = q.c + dc[d];
				if(!check(nr, nc))
					continue;
				if(visited[nr][nc][q.remain])
					continue;
				if(map[nr][nc] == -1)
					continue;
				map[nr][nc] = q.time+1;
				visited[nr][nc][q.remain] = true;
				queue.add(new Pos(nr, nc, q.remain, q.time+1));
			}
		}
		
		ans = (ans == Integer.MAX_VALUE) ? -1 : ans;
		System.out.println(ans);
	}
	
	static int ans = Integer.MAX_VALUE;
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int[] ddr = {1, 2, 2, 1, -1, -2, -2, -1};
	static int[] ddc = {2, 1, -1, -2, -2, -1, 1, 2};
}