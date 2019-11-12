package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1726_로봇 {
	static class Pos{
		int r;
		int c;
		int time;
		int dir;
		Pos(int r, int c, int time, int dir){
			this.r = r;
			this.c = c;
			this.time = time;
			this.dir = dir;
		}
	}
	static final int INF = 987654321;
	static int N, M;
	static int[][] map;
	static int str, stc, std, edr, edc, edd;
	static int[][][][] visited;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 가로
		M = sc.nextInt(); // 세로
		map = new int[N+1][M+1];
		visited = new int[N+1][M+1][5][4];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				map[i][j] = sc.nextInt();
				for(int a=0; a<5; a++) {
					for(int b=0; b<4; b++) {
						visited[i][j][a][b] = INF;
					}
				}
			}
		}
		
		str = sc.nextInt(); // 시작 행
		stc = sc.nextInt(); // 시작 열
		std = sc.nextInt()-1; // 시작 방향
		if(std == 1)
			std = 2;
		else if(std == 2)
			std = 1;
		edr = sc.nextInt(); // 끝 행
		edc = sc.nextInt(); // 끝 열
		edd = sc.nextInt()-1; // 끝 방향
		if(edd == 1)
			edd = 2;
		else if(edd == 2)
			edd = 1;
		
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(str, stc, 0, std));
		queue.add(new Pos(str, stc, 1, (std+1)%4));
		queue.add(new Pos(str, stc, 2, (std+2)%4));
		queue.add(new Pos(str, stc, 1, (std+3)%4));
		
		for(int i=0; i<4; i++) {
			visited[str][stc][std][i] = 0;
			visited[str][stc][(std+1)%4][i] = 1;
			visited[str][stc][(std+2)%4][i] = 2;
			visited[str][stc][(std+3)%4][i] = 1;
		}
		while(!queue.isEmpty()) {
			Pos q = queue.poll();
			if(q.r == edr && q.c == edc) {
				if(q.dir != edd) {
					int nld = q.dir;
					int nrd = q.dir;
					for(int i=1; i<4; i++) {
						nld--;
						if(nld == -1)
							nld = 3;
						nrd++;
						if(nrd == 4)
							nrd = 0;
						if(nld == edd || nrd == edd) {
							q.time += i;
							break;
						}
					}
				}
				ans = Math.min(ans, q.time);
				continue;
			}
			
			// 한칸 ~ 세칸 가보기
			for(int i=1; i<4; i++) {
				int nr = q.r + dr[q.dir]*i;
				int nc = q.c + dc[q.dir]*i;
				if(!check(nr, nc))
					continue;
				if(visited[nr][nc][q.dir][i] <= q.time+1)
					continue;
				if(map[nr][nc] == 1)
					break;
				visited[nr][nc][q.dir][i] = q.time+1; 
				queue.add(new Pos(nr, nc, q.time+1, q.dir));
			}
			
			int nld = q.dir-1;
			if(nld == -1)
				nld = 3;
			
			for(int i=1; i<4; i++) {
				int nr = q.r + dr[nld]*i;
				int nc = q.c + dc[nld]*i;
				if(!check(nr, nc))
					continue;
				if(visited[nr][nc][nld][i] <= q.time+2)
					continue;
				if(map[nr][nc] == 1)
					break;
				visited[nr][nc][nld][i] = q.time+2;
				queue.add(new Pos(nr, nc, q.time+2, nld));
			}
			
			int nrd = q.dir+1;
			if(nrd == 4)
				nrd = 0;
			
			for(int i=1; i<4; i++) {
				int nr = q.r + dr[nrd]*i;
				int nc = q.c + dc[nrd]*i;
				if(!check(nr, nc))
					continue;
				if(visited[nr][nc][nrd][i] <= q.time+2)
					continue;
				if(map[nr][nc] == 1)
					break;
				visited[nr][nc][nrd][i] = q.time+2;
				queue.add(new Pos(nr, nc, q.time+2, nrd));
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean check(int r, int c) {
		return r>=1 && c>=1 && r<=N && c<=M;
	}
	static int dr[] = {0, 1, 0, -1};
	static int dc[] = {1, 0, -1, 0};
}
