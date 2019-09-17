package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class _BOJ_16973_직사각형탈출 {
	static class Coord{
		int x;
		int y;
		int cnt;
		Coord(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	static int N, M, H, W, sx, sy, ex, ey;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int MIN = Integer.MAX_VALUE;
	static ArrayList<Coord> walls;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		walls = new ArrayList<>();
		
		int[][] map = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken())-1;
		W = Integer.parseInt(st.nextToken())-1;
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		ex = Integer.parseInt(st.nextToken());
		ey = Integer.parseInt(st.nextToken());
		
		boolean[][] visited = new boolean[N+1][M+1];
		visited[sx][sy] = true;
		MIN = Integer.MAX_VALUE;
		
		Queue<Coord> queue = new LinkedList<>();
		solve(queue, map, visited);
		
		if(MIN == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(MIN);
		}
	}
	
	static void solve(Queue<Coord> queue, int[][] map, boolean[][] visited) {
		queue.add(new Coord(sx, sy, 0));
		visited[sx][sy] = true;
		while(!queue.isEmpty()) {
			Coord q = queue.poll();
			if(q.x == ex && q.y == ey) {
				MIN = Math.min(MIN, q.cnt);
				break;
			}
			for(int i=0; i<4; i++) {
				int nx = q.x + dx[i];
				int ny = q.y + dy[i];
				if(check(nx, ny) && visited[nx][ny] == false && possible(map, nx, ny, i) && map[nx][ny] == 0) {
					visited[nx][ny] = true;
					queue.add(new Coord(nx, ny, q.cnt+1));
				}
			}
		}
	}
	
	static int tx, ty;
	
	static boolean possible(int[][] map, int x, int y, int d) {
		//우 하 좌 상
		switch(d) {
		case 1:
			for(int i=0; i<H; i++) {
				if(map[x+i][y+W] == 1) {
					return false;
				}
			}
			break;
		case 2:
			for(int i=0; i<W; i++) {
				if(map[x+H][y+i] == 1) {
					return false;
				}
			}
			break;
		case 3:
			for(int i=0; i<H; i++) {
				if(map[x+i][y] == 1) {
					return false;
				}
			}
			break;
		case 4:
			for(int i=0; i<W; i++) {
				if(map[x][y+i] == 1) {
					return false;
				}
			}
			break;
		default:
			break;
		}
		return true;
	}
	
	static boolean check(int x, int y) {
		return x>=1 && y>=1 && x+H<=N && y+W<=M; 
	}
}