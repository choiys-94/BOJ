package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _BOJ_3197_백조의호수2 {
	static int R, C, str, stc, edr, edc;
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
		
		R = sc.nextInt();
		C = sc.nextInt();
		int[][] map = new int[R][C];
		boolean isSwan = false;
		Queue<Pos> water = new LinkedList<>();
		for(int i=0; i<R; i++) {
			String in = sc.next();
			for(int j=0; j<C; j++) {
				switch(in.charAt(j)) {
				case 'L':
					water.add(new Pos(i, j, 0));
					if(isSwan) {
						edr = i;
						edc = j;
						map[i][j] = 4;
						break;
					}
					str = i;
					stc = j;
					map[i][j] = 3;
					isSwan = true;
					break;
				case '.':
					water.add(new Pos(i, j, 0));
					map[i][j] = 0;
					break;
				case 'X':
					map[i][j] = 1;
					break;
				}
			}
		}
		
		boolean[][] visited = new boolean[R][C];
		Queue<Pos> swan = new LinkedList<>();
		Queue<Pos> swana = new LinkedList<>();
		swan.add(new Pos(str, stc, 0));
		swana.add(new Pos(str, stc, 0));
		visited[str][stc] = true;
		while(!swan.isEmpty()) {
			Pos q = swan.poll();
			for(int i=0; i<dr.length; i++) {
				int nr = q.r + dr[i];
				int nc = q.c + dc[i];
				if(check(nr, nc) && visited[nr][nc] == false && map[nr][nc] == 0) {
					swana.add(new Pos(nr, nc, 0));
					visited[nr][nc] = true;
					map[nr][nc] = 3;
					swan.add(new Pos(nr, nc, 0));
				}
			}
		}

		visited = new boolean[R][C];
		swan.clear();
		Queue<Pos> swanb = new LinkedList<>();
		swan.add(new Pos(edr, edc, 0));
		swanb.add(new Pos(edr, edc, 0));
		visited[edr][edc] = true;
		while(!swan.isEmpty()) {
			Pos q = swan.poll();
			for(int i=0; i<dr.length; i++) {
				int nr = q.r + dr[i];
				int nc = q.c + dc[i];
				if(check(nr, nc) && visited[nr][nc] == false && map[nr][nc] == 0) {
					swanb.add(new Pos(nr, nc, 0));
					visited[nr][nc] = true;
					map[nr][nc] = 4;
					swan.add(new Pos(nr, nc, 0));
				}
			}
		}
		
		visited = new boolean[R][C];
		int curTime = 0;
		while(!water.isEmpty()) {
			Pos q = water.poll();
			if(curTime != q.time) {
				
				curTime = q.time;
				Queue<Pos> tmp = new LinkedList<>(swana);
				
				while(!tmp.isEmpty()) {
					Pos sa = tmp.poll();
					for(int i=0; i<dr.length; i++) {
						int nr = sa.r + dr[i];
						int nc = sa.c + dc[i];
						if(check(nr, nc) && map[nr][nc] == 4) {
//							for(int a=0; a<R; a++) {
//								for(int b=0; b<C; b++) {
//									System.out.print(map[a][b]);
//								}
//								System.out.println();
//							}
//							System.out.println(q.time + "------------------------");
							System.out.println(q.time);
							System.exit(0);
						}
						if(check(nr, nc) && map[nr][nc] == 0) {
							tmp.add(new Pos(nr, nc, q.time));
							swana.add(new Pos(nr, nc, q.time));
							map[nr][nc] = 3;
						}
					}
				}

				tmp = new LinkedList<>(swanb);
				while(!tmp.isEmpty()) {
					Pos sb = tmp.poll();
					for(int i=0; i<dr.length; i++) {
						int nr = sb.r + dr[i];
						int nc = sb.c + dc[i];
						if(check(nr, nc) && map[nr][nc] == 3) {
//							for(int a=0; a<R; a++) {
//								for(int b=0; b<C; b++) {
//									System.out.print(map[a][b]);
//								}
//								System.out.println();
//							}
//							System.out.println(q.time + "------------------------");
							System.out.println(q.time);
							System.exit(0);
						}
						if(check(nr, nc) && map[nr][nc] == 0) {
							tmp.add(new Pos(nr, nc, q.time));
							swanb.add(new Pos(nr, nc, q.time));
							map[nr][nc] = 4;
						}
					}
				}
			}
			
			boolean isA = false;
			boolean isB = false;
			for(int i=0; i<dr.length; i++) {
				int nr = q.r + dr[i];
				int nc = q.c + dc[i];
				if(check(nr, nc) && map[nr][nc] == 3)
					isA = true;
				else if(check(nr, nc) && map[nr][nc] == 4)
					isB = true;
					
				if(check(nr, nc) && map[nr][nc] == 1) {
					water.add(new Pos(nr, nc, q.time+1));
					map[nr][nc] = 0;
				}
			}
			if(isA && isB) {
				System.out.println(q.time+1);
				System.exit(0);
			}
		}
		
		Queue<Pos> tmp = new LinkedList<>(swana);
		while(!tmp.isEmpty()) {
			Pos sa = tmp.poll();
			for(int i=0; i<dr.length; i++) {
				int nr = sa.r + dr[i];
				int nc = sa.c + dc[i];
				if(check(nr, nc) && map[nr][nc] == 4) {
					System.out.println(curTime+1);
					System.exit(0);
				}
				if(check(nr, nc) && map[nr][nc] == 0) {
					tmp.add(new Pos(nr, nc, curTime));
					swana.add(new Pos(nr, nc, curTime));
					map[nr][nc] = 3;
				}
			}
		}
		
		tmp = new LinkedList<>(swanb);
		while(!tmp.isEmpty()) {
			Pos sb = tmp.poll();
			for(int i=0; i<dr.length; i++) {
				int nr = sb.r + dr[i];
				int nc = sb.c + dc[i];
				if(check(nr, nc) && map[nr][nc] == 3) {
					System.out.println(curTime+1);
					System.exit(0);
				}
				if(check(nr, nc) && map[nr][nc] == 0) {
					tmp.add(new Pos(nr, nc, curTime));
					swanb.add(new Pos(nr, nc, curTime));
					map[nr][nc] = 4;
				}
			}
		}
//		for(int a=0; a<R; a++) {
//			for(int b=0; b<C; b++) {
//				System.out.print(map[a][b]);
//			}
//			System.out.println();
//		}
//		System.out.println("------------------------");
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static boolean check(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
}
