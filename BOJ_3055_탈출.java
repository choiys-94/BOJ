package boj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_3055_탈출 {
	static int N, M;
	static int str = -1, stc = -1, edr, edc;
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
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		int[][] map = new int[N][M];
		Queue<Pos> fire = new LinkedList<>();
		for(int i=0; i<N; i++) {
			String in = sc.next();
			for(int j=0; j<M; j++) {
				switch(in.charAt(j)) {
				case 'D':
					edr = i;
					edc = j;
					map[i][j] = -9;
					break;
				case 'S':
					str = i;
					stc = j;
					break;
				case 'X':
					map[i][j] = -1;
					break;
				case '*':
					map[i][j] = 1;
					fire.add(new Pos(i, j, 1));
					break;
				}
			}
		}
		
		spread(map, fire);
		bfs(map);
		if(result == 0)
			System.out.println("KAKTUS");
		else
			System.out.println(result);
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int result = 0;
	static void bfs(int[][] map) {
		Queue<Pos> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.add(new Pos(str, stc, 1));
		visited[str][stc] = true;
		
		out: while(!queue.isEmpty()) {
			Pos q = queue.poll();
			for(int i=0; i<dr.length; i++) {
				int nr = q.r + dr[i];
				int nc = q.c + dc[i];
				if(nr == edr && nc == edc) {
					result = q.time;
					break out;
				}
				if(check(nr, nc) && visited[nr][nc] == false && (map[nr][nc] > q.time+1 || map[nr][nc] == 0)) {
					visited[nr][nc] = true;
					queue.add(new Pos(nr, nc, q.time+1));
				}
			}
		}
	}
	
	static void spread(int[][] map, Queue<Pos> fire) {
		while(!fire.isEmpty()) {
			Pos f = fire.poll();
			
			for(int i=0; i<dr.length; i++) {
				int nr = f.r + dr[i];
				int nc = f.c + dc[i];
				if(check(nr, nc) && (map[nr][nc] != -9 && map[nr][nc] != -1 && map[nr][nc] == 0)) {
					map[nr][nc] = f.time + 1;
					fire.add(new Pos(nr, nc, f.time + 1));
				}
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
}
