package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class _BOJ_17143_낚시왕 {
	static class Shark{
		int r;
		int c;
		int s;
		int d;
		int z;
		Shark(int r, int c, int s, int d, int z){
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	static int R, C, M;
	static int[][] map;
	static HashMap<Integer, Shark> shark;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();	// 행
		C = sc.nextInt();	// 열
		M = sc.nextInt();	// 개수
		map = new int[R+1][C+1];
		shark = new HashMap<>();
		
		for(int i=1; i<=M; i++) {
			int r = sc.nextInt();	// 상어행
			int c = sc.nextInt();	// 상어열
			int s = sc.nextInt();	// 속력
			int d = sc.nextInt();	// 방향
			int z = sc.nextInt();	// 크기
			shark.put(i, new Shark(r, c, s, d, z));
			map[r][c] = i;
		}
		
		int result = 0;
		ArrayList<Integer> toDel = new ArrayList<>();
		int[][] tmp = new int[R+1][C+1];
		for(int i=1; i<=C; i++) {
			toDel.clear();
			if(shark.isEmpty())
				break;
			for(int j=1; j<=R; j++) {
				Arrays.fill(tmp[j], 0);
			}
			for(int j=1; j<=R; j++) {
				if(map[j][i] != 0) {
					result += shark.get(map[j][i]).z;
					shark.remove(map[j][i]);
					map[j][i] = 0;
					break;
				}
			}
			for(int s: shark.keySet()) {
				Shark current = shark.get(s);
				int dir = current.d;
				int speed = current.s;
				int size = current.z;
				int nr = current.r + speed*dr[dir];
				int nc = current.c + speed*dc[dir];
				
				if(dir <= 2) {
					if(speed/R > 0) {
						if((speed/R)%2 == 1)
							dir = dir == 1 ? 2 : 1;
						nr = current.r + (speed%R)*dr[dir];
					}
					else
						nr = current.r + speed*dr[dir];
				}
				else {
					if(speed/C > 0) {
						if((speed/C)%2 == 1)
							dir = dir == 3 ? 4 : 3;
						nc = current.c + (speed%C)*dc[dir];
					}
					else
						nc = current.c + speed*dc[dir];
				}
				
//				System.out.println(s + " " + nr + " " + nc);
				if(tmp[nr][nc] != 0 && shark.get(tmp[nr][nc]).z > size) {
					toDel.add(s);
				}
				else if(tmp[nr][nc] != 0 && shark.get(tmp[nr][nc]).z < size){
					toDel.add(tmp[nr][nc]);
					tmp[nr][nc] = s;
					shark.put(s, new Shark(nr, nc, speed, dir, size));
				}
				else {
					tmp[nr][nc] = s;
					shark.put(s, new Shark(nr, nc, speed, dir, size));
				}
			}
			for(int j=1; j<=R; j++) {
				System.arraycopy(tmp[j], 0, map[j], 0, tmp[j].length);
			}
			for(int s: toDel) {
				shark.remove(s);
			}
		}
		
		System.out.println(result);
	}
	
	static boolean check(int r, int c) {
		return r>=1 && c>=1 && r<=R && c<=C;
	}
	
	// 위 아래 오른쪽 왼쪽
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, 1, -1};
}
