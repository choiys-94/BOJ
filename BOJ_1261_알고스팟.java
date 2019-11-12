package boj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1261_알고스팟 {
	static class Pos{
		int r, c, cnt;
		Pos(int r, int c, int cnt){
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static int N, M;
	static int[][] map;
	static int[][] visited;
	static int[][] memo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		map = new int[N+1][M+1];
		memo = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			String in = sc.next();
			Arrays.fill(memo[i], 987654321);
			for(int j=1; j<=M; j++) {
				map[i][j] = in.charAt(j-1)-'0';
			}
		}
		
		Queue<Pos> block = new LinkedList<>();
		Queue<Pos> queue = new LinkedList<>();
		memo[1][1] = 0;
		block.add(new Pos(1, 1, 0));
		out: while(!block.isEmpty()) {
			Pos p = block.poll();
			queue.add(new Pos(p.r, p.c, p.cnt));
			while(!queue.isEmpty()) {
				Pos q = queue.poll();
				if(q.r == N && q.c == M)
					break out;
				
				for(int d=0; d<4; d++) {
					int nr = q.r + dr[d];
					int nc = q.c + dc[d];
					if(!check(nr, nc))
						continue;
					if(memo[nr][nc] <= p.cnt)
						continue;
					if(map[nr][nc] == 1) {
						if(memo[nr][nc] <= p.cnt+1)
							continue;
						memo[nr][nc] = p.cnt+1;
						block.add(new Pos(nr, nc, p.cnt+1));
						continue;
					}
					memo[nr][nc] = p.cnt;
					queue.add(new Pos(nr, nc, p.cnt));
				}
			}
		}
		System.out.println(memo[N][M]);
	}
	
	static boolean check(int r, int c) {
		return r>=1 && c>=1 && r<=N && c<=M;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
