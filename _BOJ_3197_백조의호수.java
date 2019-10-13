package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _BOJ_3197_백조의호수 {
	static int R, C, str, stc, edr, edc;
	static class Pos{
		int r;
		int c;
		int time;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		int[][] map = new int[R][C];
		boolean flag = false;
		Queue<Pos> water = new LinkedList<>();
		for(int i=0; i<R; i++) {
			String in = sc.next();
			for(int j=0; j<C; j++) {
				switch(in.charAt(j)) {
				case '.':
					map[i][j] = 0;
					water.add(new Pos(i, j));
					break;
				case 'X':
					map[i][j] = 1;
					break;
				case 'L':
					water.add(new Pos(i, j));
					if(flag) {
						edr = i;
						edc = j;
						map[i][j] = 4; 
						break;
					}
					str = i;
					stc = j;
					flag = true;
					map[i][j] = 3;
					break;
				}
			}
		}
		
		Queue<Pos> ice = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		while(!water.isEmpty()) {
			Pos q = water.poll();
			for(int i=0; i<dr.length; i++) {
				int nr = q.r + dr[i];
				int nc = q.c + dc[i];
				if(check(nr, nc) && map[nr][nc] == 1 && visited[nr][nc] == false) {
					visited[nr][nc] = true;
					Pos tmp = new Pos(nr, nc);
					tmp.time = 0;
					ice.add(tmp);
				}
			}
		}
		
		visited = new boolean[R][C];
		Queue<Pos> swan = new LinkedList<>();
		Queue<Pos> swana = new LinkedList<>();
		Pos tmp = new Pos(str, stc);
		tmp.time = 0;
		swan.add(tmp);
		swana.add(tmp);
		while(!swan.isEmpty()) {
			Pos q = swan.poll();
			for(int i=0; i<dr.length; i++) {
				int nr = q.r + dr[i];
				int nc = q.c + dc[i];
				if(nr == edr && nc == edc) {
					System.out.println(1);
					System.exit(0);
				}
				if(check(nr, nc) && map[nr][nc] == 0 && visited[nr][nc] == false) {
					visited[nr][nc] = true;
					map[nr][nc] = 3;
					tmp = new Pos(nr, nc);
					tmp.time = 0;
					swan.add(tmp);
					swana.add(tmp);
				}
			}
		}
		
		visited = new boolean[R][C];
		Queue<Pos> swanb = new LinkedList<>();
		swan.clear();
		tmp = new Pos(edr, edc);
		tmp.time = 0;
		swan.add(tmp);
		swanb.add(tmp);
		while(!swan.isEmpty()) {
			Pos q = swan.poll();
			for(int i=0; i<dr.length; i++) {
				int nr = q.r + dr[i];
				int nc = q.c + dc[i];
				if(check(nr, nc) && map[nr][nc] == 0 && visited[nr][nc] == false) {
					visited[nr][nc] = true;
					map[nr][nc] = 4;
					tmp = new Pos(nr, nc);
					tmp.time = 0;
					swan.add(tmp);
					swanb.add(tmp);
				}
			}
		}
		
		visited = new boolean[R][C];
		while(!ice.isEmpty()) {
			Pos q = ice.poll();
			visited[q.r][q.c] = true; 
			for(int i=0; i<dr.length; i++) {
				int nr = q.r + dr[i];
				int nc = q.c + dc[i];
				if(check(nr, nc) && map[nr][nc] == 1 && visited[nr][nc] == false) {
					visited[nr][nc] = true;
					tmp = new Pos(nr, nc);
					tmp.time = q.time + 1;
					ice.add(tmp);
				}
			}
			
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.out.println("-----------");
		}
		
	}
	
	static int dr[] = {0, 1, 0, -1};
	static int dc[] = {1, 0, -1, 0};
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<R && c<C;
	}
}
