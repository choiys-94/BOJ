package boj;

import java.util.Scanner;

public class BOJ_17144_미세먼지안녕 {
	static class Coord{
		int x;
		int y;
		Coord(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static int N, M, T;
	static Coord airCleaner = null;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();	// 행
		M = sc.nextInt();	// 열
		T = sc.nextInt();	// 시간
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == -1 && airCleaner == null) {
					airCleaner = new Coord(i, j);
				}
			}
		}
		
		for(int i=0; i<T; i++) {
			spread(map);
			clean(map, 1);
			clean(map, 2);
		}
		
		int result = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] > 0)
					result += map[i][j];
			}
		}
		
		System.out.println(result);
	}
	
	static int dx[] = {0, -1, 0, 1};
	static int dy[] = {1, 0, -1, 0};
	static int dx2[] = {0, 1, 0, -1};
	static int dy2[] = {1, 0, -1, 0};
	
	static void spread(int[][] map) {
		int[][] tmp = new int[N][M];
		tmp[airCleaner.x][airCleaner.y] = -1;
		tmp[airCleaner.x+1][airCleaner.y] = -1; 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] > 0) {
					int cnt = 0;
					for(int k=0; k<4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if(check(nx, ny) && map[nx][ny] != -1) {
							tmp[nx][ny] += map[i][j]/5;
							cnt++;
						}
					}
					tmp[i][j] += map[i][j] - (map[i][j]/5)*cnt;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			System.arraycopy(tmp[i], 0, map[i], 0, tmp[i].length);
		}
	}
	
	static void clean(int[][] map, int type) {
		int dir = 0;
		int prev = -1;
		if(type == 1) {
			int nx = airCleaner.x + dx[dir];
			int ny = airCleaner.y + dy[dir];
			prev = map[nx][ny];
			map[nx][ny] = 0;
			while(true) {
				nx += dx[dir];
				ny += dy[dir];
				if(!check(nx, ny)) {
					nx -= dx[dir];
					ny -= dy[dir];
					dir++;
					continue;
				}
				if(check(nx, ny) && map[nx][ny] == -1) {
					break;
				}
				int t = map[nx][ny];
				map[nx][ny] = prev;
				prev = t;
			}
		}
		else {
			int nx = airCleaner.x + 1 + dx2[dir];
			int ny = airCleaner.y + dy2[dir];
			prev = map[nx][ny];
			map[nx][ny] = 0;
			while(true) {
				nx += dx2[dir];
				ny += dy2[dir];
				if(!check(nx, ny)) {
					nx -= dx2[dir];
					ny -= dy2[dir];
					dir++;
					continue;
				}
				if(check(nx, ny) && map[nx][ny] == -1) {
					break;
				}
				int t = map[nx][ny];
				map[nx][ny] = prev;
				prev = t;
			}
		}
	}
	
	static boolean check(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}
}
