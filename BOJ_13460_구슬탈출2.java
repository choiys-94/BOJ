package boj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_13460_구슬탈출2 {
	static class Coord{
		int bx;
		int by;
		int rx;
		int ry;
		Coord(){
			
		}
		Coord(int rx, int ry, int bx, int by){
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
		}
	}
	static int N, M;
	static boolean[][][][] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		int bx = 0, by = 0, rx = 0, ry = 0;
		char[][] map = new char[N][M];
		Coord rc = null, bc = null;
		visited = new boolean[N][M][N][M];
		for(int i=0; i<N; i++) {
			map[i] = sc.next().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'B') {
					bx = i;
					by = j;
				}
				else if(map[i][j] == 'R') {
					rx = i;
					ry = j;
				}
			}
		}
		
		visited[rx][ry][bx][by] = true;
		solve(map, 0, new Coord(rx, ry, bx, by));
		
		if(MIN == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(MIN);
	}
	
	static void solve(char[][] map, int idx, Coord st) {
		if(idx == 10) {
			return;
		}
		char[][] tmp = new char[N][M];
		
		for(int j=0; j<N; j++) {
			System.arraycopy(map[j], 0, tmp[j], 0, map[j].length);
		}
		for(int i=0; i<4; i++) {
			Coord next = move(map, i, st);
//			for (int j = 0; j < N; j++) {
//				System.out.println(Arrays.toString(map[j]));
//			}
//			System.out.println();
			if(next == null) {
				for(int j=0; j<N; j++) {
					System.arraycopy(tmp[j], 0, map[j], 0, map[j].length);
				}
				continue;
			}
			else if(next.rx == -1) {
				MIN = Math.min(MIN, idx+1);
				return;
			}
			
			if(visited[next.rx][next.ry][next.bx][next.by] == false) {
				visited[next.rx][next.ry][next.bx][next.by] = true;
//				for(int j=0; j<N; j++) {
//					System.out.println(Arrays.toString(map[j]));
//				}
//				System.out.println();
				Coord tmp_next = new Coord(next.rx, next.ry, next.bx, next.by);
				solve(map, idx+1, tmp_next);
				visited[next.rx][next.ry][next.bx][next.by] = false;
			}
			for(int j=0; j<N; j++) {
				System.arraycopy(tmp[j], 0, map[j], 0, map[j].length);
			}
		}
	}
	
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	static boolean rClear = false;
	static boolean bClear = false;
	static int result = 0;
	static int MIN = Integer.MAX_VALUE;
	
	static Coord move(char[][] tmp, int dir, Coord coord) {
		
		int srx = coord.rx;
		int sry = coord.ry;
		int sbx = coord.bx;
		int sby = coord.by;
		int nrx = srx;
		int nry = sry;
		int nbx = sbx;
		int nby = sby;
		boolean isR = false;
		boolean isB = false;
		boolean cR = false;
		boolean cB = false;
		boolean clearR = false;
		boolean clearB = false;
		Coord next = null;
		// R굴리기
		
		while(true) {
			nrx += dx[dir];
			nry += dy[dir];
			if(tmp[nrx][nry] == 'B') {
				isB = true;
			}
			else if(tmp[nrx][nry] == '#') {
				nrx -= dx[dir];
				nry -= dy[dir];
				break;
			}
			else if(tmp[nrx][nry] == 'O') {
				clearR = true;
			}
		}
		
		while(true) {
			nbx += dx[dir];
			nby += dy[dir];
			if(tmp[nbx][nby] == 'R') {
				isR = true;
			}
			else if(tmp[nbx][nby] == '#') {
				nbx -= dx[dir];
				nby -= dy[dir];
				tmp[sbx][sby] = '.';
				tmp[srx][sry] = '.';
				if(isR) {
					tmp[nbx][nby] = 'R';
					tmp[nbx-dx[dir]][nby-dy[dir]] = 'B';
					next = new Coord(nbx, nby, nbx-dx[dir], nby-dy[dir]);
				}
				else if(isB) {
					tmp[nbx][nby] = 'B';
					tmp[nbx-dx[dir]][nby-dy[dir]] = 'R';
					next = new Coord(nbx-dx[dir], nby-dy[dir], nbx, nby);
				}
				else {
					tmp[nbx][nby] = 'B';
					tmp[nrx][nry] = 'R';
					next = new Coord(nrx, nry, nbx, nby);
				}
				break;
			}
			else if(tmp[nbx][nby] == 'O') {
				clearB = true;
			}
		}
		if(clearR && !clearB) {
			return new Coord(-1, -1, -1, -1);
		}
		else if(!clearR && !clearB) {
			return next;
		}
		else {
			return null;
		}
	}
}
