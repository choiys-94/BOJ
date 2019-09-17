package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_15683_감시 {
	static class Coord{
		int x;
		int y;
		int type;
		int dir;
		Coord(int x, int y, int type, int dir){
			this.x = x;
			this.y = y;
			this.type = type;
			this.dir = dir;
		}
	}
	static int N, M;
	static int left;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		left = N*M;
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] != 0) {
					left--;
					if(map[i][j] != 6) {
						tvs.add(new Coord(i, j, map[i][j], 0));
					}
				}
			}
		}
		
		solve(map, 0);
		if(result == Integer.MAX_VALUE)
			System.out.println(0);
		else
			System.out.println(result);
	}
	
	static ArrayList<Coord> tvs = new ArrayList<>();
	static int result = Integer.MAX_VALUE;
	static ArrayList<Coord> order = new ArrayList<>();
	static boolean[][] visited;
	
	static void solve(int[][] map, int idx) {
		if(idx == tvs.size()) {
			int[][] tmp = new int[N][M];
			visited = new boolean[N][M];
			for(int i=0; i<N; i++) {
				System.arraycopy(map[i], 0, tmp[i], 0, map[i].length);
			}
			
			int tLeft = left;
			for(Coord c: order) {
				visited[c.x][c.y] = true; 
			}
			for(Coord c: order) {
				switch(c.type) {
				case 1:
					rot1(map, c.dir, c.x, c.y);
					break;
				case 2:
					rot2(map, c.dir, c.x, c.y);
					break;
				case 3:
					rot3(map, c.dir, c.x, c.y);
					break;
				case 4:
					rot4(map, c.dir, c.x, c.y);
					break;
				case 5:
					rot5(map, c.dir, c.x, c.y);
					break;
				}
			}
			result = Math.min(result, left);
			left = tLeft;
			for(int i=0; i<N; i++) {
				System.arraycopy(tmp[i], 0, map[i], 0, map[i].length);
			}
			return;
		}

		if(!tvs.isEmpty()) {
			Coord tv = tvs.get(idx);
			switch(tv.type) {
			case 1:
				for(int j=0; j<4; j++) {
					int nx = tv.x + dx1[j];
					int ny = tv.y + dy1[j];
					if(check(nx, ny) && map[nx][ny] == 6)
						continue;
					order.add(new Coord(tv.x, tv.y, tv.type, j));
					solve(map, idx+1);
					order.remove(order.size()-1);
				}
				break;
			case 2:
				for(int j=0; j<2; j++) {
					order.add(new Coord(tv.x, tv.y, tv.type, j));
					solve(map, idx+1);
					order.remove(order.size()-1);
				}
				break;
			case 3:
				for(int j=0; j<4; j++) {
					order.add(new Coord(tv.x, tv.y, tv.type, j));
					solve(map, idx+1);
					order.remove(order.size()-1);
				}
				break;
			case 4:
				for(int j=0; j<4; j++) {
					order.add(new Coord(tv.x, tv.y, tv.type, j));
					solve(map, idx+1);
					order.remove(order.size()-1);
				}
				break;
			case 5:
				order.add(new Coord(tv.x, tv.y, tv.type, 0));
				solve(map, idx+1);
				order.remove(order.size()-1);
				break;
			}
		}
	}
	
	// 우 하 좌 상
	static int dx1[] = {0, 1, 0, -1};
	static int dy1[] = {1, 0, -1, 0};
	
	//우 좌    하 상
	static int dx2[] = {0, 0, 1, -1};
	static int dy2[] = {1, -1, 0, 0};
	
	//우 하    하 좌    좌 상    상 우
	static int dx3[] = {0, 1, 1, 0, 0, -1, -1, 0};
	static int dy3[] = {1, 0, 0, -1, -1, 0, 0, 1};
	
	//우 하 좌    하 좌 상    좌 상 우   상 우 하
	static int dx4[] = {0, 1, 0, 1, 0, -1, 0, -1, 0, -1, 0, 1};
	static int dy4[] = {1, 0, -1, 0, -1, 0, -1, 0, 1, 0, 1, 0};
	
	
	
	static void rot1(int[][] map, int dir, int x, int y) {
		int nx = x;
		int ny = y;
		while(true) {
			nx += dx1[dir];
			ny += dy1[dir];
			if(check(nx, ny) && map[nx][ny] != 6) {
				if(visited[nx][ny] == false) {
					left--;
					visited[nx][ny] = true;
				}
			}
			else {
				break;
			}
		}
	}
	
	static void rot2(int[][] map, int dir, int x, int y) {
		
		for(int i=dir*2; i<2+dir*2; i++) {
			int nx = x;
			int ny = y;
			while(true) {
				nx += dx2[i];
				ny += dy2[i];
				if(check(nx, ny) && map[nx][ny] != 6) {
					if(visited[nx][ny] == false) {
						left--;
						visited[nx][ny] = true;
					}
				}
				else {
					break;
				}
			}
		}
	}
	
	static void rot3(int[][] map, int dir, int x, int y) {
		for(int i=dir*2; i<2 + dir*2; i++) {
			int nx = x;
			int ny = y;
			while(true) {
				nx += dx3[i];
				ny += dy3[i];
				if(check(nx, ny) && map[nx][ny] != 6) {
					if(visited[nx][ny] == false) {
						left--;
						visited[nx][ny] = true;
					}
				}
				else {
					break;
				}
			}
		}
	}
	
	static void rot4(int[][] map, int dir, int x, int y) {
		for(int i=dir*3; i<3 + dir*3; i++) {
			int nx = x;
			int ny = y;
			while(true) {
				nx += dx4[i];
				ny += dy4[i];
				if(check(nx, ny) && map[nx][ny] != 6) {
					if(visited[nx][ny] == false) {
						left--;
						visited[nx][ny] = true;
					}
				}
				else {
					break;
				}
			}
		}
	}
	
	static void rot5(int[][] map, int dir, int x, int y) {
		for(int i=0; i<4; i++) {
			int nx = x;
			int ny = y;
			while(true) {
				nx += dx1[i];
				ny += dy1[i];
				if(check(nx, ny) && map[nx][ny] != 6) {
					if(visited[nx][ny] == false) {
						left--;
						visited[nx][ny] = true;
					}
				}
				else {
					break;
				}
			}
		}
	}
	
	static boolean check(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}
}
