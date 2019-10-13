package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573_빙산 {
	static class Pos{
		int r;
		int c;
		int h;
		Pos(int r, int c, int h){
			this.r = r;
			this.c = c;
			this.h = h;
		}
	}
	static int N, M;
	static int[][] map;
	static ArrayList<Pos> ice;
	static ArrayList<Pos> tice;
	static boolean[][] visited;
	static int bunch;
	static int year;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		ice = new ArrayList<>();
		tice = new ArrayList<>();
		visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) {
					ice.add(new Pos(i, j, map[i][j]));
				}
			}
		}
		
		
		while(bunch < 2) {
			bunch = 0;
			
			for(int i=0; i<N; i++) {
				Arrays.fill(visited[i], false);
			}
			if(ice.isEmpty()) {
				break;
			}
			for(Pos p: ice) {
				if(visited[p.r][p.c])
					continue;
				bunch++;
				Queue<Pos> queue = new LinkedList<>();
				queue.add(new Pos(p.r, p.c, p.h));
				visited[p.r][p.c] = true;
				while(!queue.isEmpty()) {
					Pos q = queue.poll();
					for(int d=0; d<4; d++) {
						int nr = q.r + dr[d];
						int nc = q.c + dc[d];
						if(check(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0) {
							visited[nr][nc] = true;
							queue.add(new Pos(nr, nc, map[nr][nc]));
						}
					}
				}
			}
			
			if(bunch >= 2) {
				break;
			}
			year++;
			for(Pos p: ice) {
				int cnt = 0;
				for(int d=0; d<4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					if(check(nr, nc) && map[nr][nc] == 0) {
						cnt++;
					}
				}
				if(cnt > 0) {
					p.h -= cnt;
				}
				if(p.h > 0) 
					tice.add(new Pos(p.r, p.c, p.h));
			}
			for(Pos p: ice) {
				if(p.h <= 0) {
					map[p.r][p.c] = 0; 
				}
				else
					map[p.r][p.c] = p.h; 
			}
			ice.clear();
			ice.addAll(tice);
			tice.clear();
//			for(int i=0; i<N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
		}
		if(ice.isEmpty())
			System.out.println(0);
		else
			System.out.println(year);
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
