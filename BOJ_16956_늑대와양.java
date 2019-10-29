package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_16956_늑대와양 {
	static class Pos{
		int r;
		int c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int N, M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		char[][] map = new char[N][M];
		ArrayList<Pos> sheep = new ArrayList<>();
		for(int i=0; i<N; i++) {
			map[i] = sc.next().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'S') {
					sheep.add(new Pos(i, j));
				}
			}
		}
		
		boolean flag = true;
		
		out: for(Pos s: sheep) {
			for(int i=0; i<4; i++) {
				int nr = s.r + dr[i];
				int nc = s.c + dc[i];
				if(!check(nr, nc))
					continue;
				if(map[nr][nc] == 'W') {
					flag = false;
					break out;
				}
				if(map[nr][nc] == 'S')
					continue;
				map[nr][nc] = 'D';
			}
		}
		if(flag) {
			System.out.println(1);
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
		else {
			System.out.println(0);
		}
		
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
