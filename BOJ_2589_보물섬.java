package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589_보물섬 {
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
	static ArrayList<Pos> land;
	static int result = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		land = new ArrayList<>();
		for(int i=0; i<N; i++) {
			String in = br.readLine().trim();
			for(int j=0; j<M; j++) {
				char ch = in.charAt(j);
				if(ch == 'W') {
					map[i][j] = -1;
				}
				else if(ch == 'L') {
					map[i][j] = 0;
					land.add(new Pos(i, j, 0));
				}
			}
		}
		
		
//		boolean[] dup = new boolean[land.size()];
		for(int i=0; i<land.size(); i++) {
			boolean[][] visited = new boolean[N][M];
			Queue<Pos> queue = new LinkedList<>();
			Pos l = land.get(i);
//			dup[i] = true;
			queue.add(l);
			visited[l.r][l.c] = true;
			while(!queue.isEmpty()) {
				Pos q = queue.poll();
				boolean flag = true;
				for(int d=0; d<4; d++) {
					int nr = q.r + dr[d];
					int nc = q.c + dc[d];
					if(check(nr, nc) && map[nr][nc] == 0 && visited[nr][nc] == false) {
						flag = false;
						visited[nr][nc] = true;
						queue.add(new Pos(nr, nc, q.time + 1));
					}
				}
				if(flag) {
					result = Math.max(result, q.time);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(result);
		System.out.println(sb.toString());
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
