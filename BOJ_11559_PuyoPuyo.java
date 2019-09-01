package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_11559_PuyoPuyo {
	//상, 좌, 우
	static int[] dx = {1, 0,  0};
	static int[] dy = {0, 1, -1};
	
	//왼쪽 위, 오른쪽 위
	static int[] sdx = { 1, 1,  0, 1, 1, 0};
	static int[] sdy = {-1, 0, -1, 0, 1, 1};
	
	static int N, M, LIMIT;
	static int result;
	static ArrayList<Coord> del;
	static boolean flag = true;
	
	static class Coord{
		int x;
		int y;
		Coord(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = 12;
		M = 6;
		char[][] map = new char[N+1][M+1];
		boolean flag = true;
		for(int i=0; i<N; i++) {
			String in = sc.next();
			for(int j=0; j<M; j++) {
				map[N-1-i][j] = in.charAt(j);
				if(flag) {
					LIMIT = N-i;
					flag = false;
				}
			}
		}
		
		solve(map);
		
		System.out.println(result);
	}
	
	static void solve(char[][] map) {
		int tmp = 999;
		while(flag) {
//			if(result == tmp)
//				break;
			tmp = result;
			flag = false;
			
			slide(map);
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] != '.') {
						boom(map, map[i][j], i, j);
					}
				}
			}
			slide(map);
			if(flag) {
				result++;
			}
		}
	}
	
	static void fckform(char[][] map, char type, int x, int y) {
		int cnt = 1;
		ArrayList<Coord> tmp = new ArrayList<>();
		if(check(x+1, y) && map[x+1][y] == type) {
			tmp.add(new Coord(x+1, y));
			cnt++;
		}
		if(check(x-1, y) && map[x-1][y] == type) {
			tmp.add(new Coord(x-1, y));
			cnt++;
		}
		if(check(x, y+1) && map[x][y+1] == type) {
			tmp.add(new Coord(x, y+1));
			cnt++;
		}
		if(check(x, y-1) && map[x][y-1] == type) {
			tmp.add(new Coord(x, y-1));
			cnt++;
		}
		if(cnt >= 4 && cnt != 1) {
			del.addAll(tmp);
		}
	}
	
	static void boom(char[][] map, char type, int x, int y) {
		//상
		int cnt = 1;
		del = new ArrayList<>();
		ArrayList<Coord> tmp = new ArrayList<>();
		del.add(new Coord(x, y));
		if(check(x+3, y) && map[x+3][y] == type) {
			int nx = x, ny = y;
			while(true) {
				nx += dx[0];
				ny += dy[0];
				if(check(nx, ny) && map[nx][ny] == type) {
					tmp.add(new Coord(nx, ny));
					cnt++;
				}
				else {
					break;
				}
			}
			if(cnt < 4)
				tmp.clear();
			else
				del.addAll(tmp);
		}
		if(check(x+3, y+1) && map[x+3][y+1] == type) {
			int nx = x, ny = y+1;
			while(true) {
				nx += dx[0];
				ny += dy[0];
				if(check(nx, ny) && map[nx][ny] == type) {
					tmp.add(new Coord(nx, ny));
					cnt++;
				}
				else {
					break;
				}
			}
			if(cnt < 4)
				tmp.clear();
			else
				del.addAll(tmp);
		}
		//우
		cnt = 1;
		if(check(x, y+3) && map[x][y+3] == type) {
			int nx = x, ny = y;
			while(true) {
				nx += dx[1];
				ny += dy[1];
				if(check(nx, ny) && map[nx][ny] == type) {
					tmp.add(new Coord(nx, ny));
					cnt++;
				}
				else {
					break;
				}
			}
			if(cnt < 4)
				tmp.clear();
			else
				del.addAll(tmp);
		}
		//좌
		cnt = 1;
		if(check(x, y-3) && map[x][y-3] == type) {
			int nx = x, ny = y;
			while(true) {
				nx += dx[2];
				ny += dy[2];
				if(check(nx, ny) && map[nx][ny] == type) {
					tmp.add(new Coord(nx, ny));
					cnt++;
				}
				else {
					break;
				}
			}
			if(cnt < 4)
				tmp.clear();
			else
				del.addAll(tmp);
		}
		//좌상
		cnt = 1;
		if(check(x+1, y-1) && map[x+1][y-1] == type) {
			int nx = x, ny = y;
			for(int i=0; i<3; i++) {
				nx = x + sdx[i];
				ny = y + sdy[i];
				if(check(nx, ny) && map[nx][ny] == type) {
					fckform(map, type, x, y);
					tmp.add(new Coord(nx, ny));
					cnt++;
				}
				else {
					break;
				}
			}
			if(cnt < 4)
				tmp.clear();
			else
				del.addAll(tmp);
		}
		//우상
		cnt = 1;
		if(check(x+1, y+1) && map[x+1][y+1] == type) {
			int nx = x, ny = y;
			for(int i=3; i<6; i++) {
				nx = x + sdx[i];
				ny = y + sdy[i];
				if(check(nx, ny) && map[nx][ny] == type) {
					tmp.add(new Coord(nx, ny));
					cnt++;
				}
				else {
					break;
				}
			}
			if(cnt < 4)
				tmp.clear();
			else
				del.addAll(tmp);
		}
		
		fckform(map, type, x, y);
//		fckform(map, type, x, y+1);
//		fckform(map, type, x+1, y+1);
//		fckform(map, type, x+1, y);
			
		if(del.size() != 1) {
			flag = true;
		}
		else {
			del.clear();
		}
		
		for(Coord d: del) {
			map[d.x][d.y] = '.'; 
		}
		
		for(int i=N-1; i>=0; i--) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println("--------------------------");
		
	}
	
	static void slide(char[][] map) {
		for(int i=0; i<M; i++) {
			int idx = 0;
			ArrayList<Character> block = new ArrayList<>();
			
			for(int j=0; j<N; j++) {
				if(map[j][i] != '.') {
					block.add(map[j][i]);
					map[j][i] = '.';
				}
			}
			
			for(int j=0; j<block.size(); j++) {
				map[j][i] = block.get(j);
			}
		}
	}
	
	static boolean check(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}
}
