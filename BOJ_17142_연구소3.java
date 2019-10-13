package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17142_연구소3 {
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
	static int N, M;
	static int[][] map;
	static ArrayList<Pos> virus;
	static int remain;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 연구소의 크기 N(4 ≤ N ≤ 50)
		M = sc.nextInt(); // 바이러스의 개수 M(1 ≤ M ≤ 10)
		map = new int[N][N];
		
		virus = new ArrayList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 2) {
					virus.add(new Pos(i, j, 0));
				}
				else if(map[i][j] == 0) {
					remain++;
				}
			}
		}
		comb(new Pos[M], 0, 0);
		if(result == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(result);
	}
	
	static void solve(Pos[] sel) {
		Queue<Pos> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int r = remain;
		for(int i=0; i<sel.length; i++) {
			queue.add(new Pos(sel[i].r, sel[i].c, 0));
			visited[sel[i].r][sel[i].c] = true; 
		}
		
		int time = 0;
		while(!queue.isEmpty()) {
			Pos q = queue.poll();
			for(int i=0; i<4; i++) {
				int nr = q.r + dr[i];
				int nc = q.c + dc[i];
				if(check(nr, nc) && (map[nr][nc] == 0 || map[nr][nc] == 2) && visited[nr][nc] == false) {
					visited[nr][nc] = true;
					queue.add(new Pos(nr, nc, q.time+1));
					if(map[nr][nc] == 0) {
						r--;
						time = q.time+1;
					}
				}
			}
		}
		
		if(r <= 0) {
			result = Math.min(result, time);
		}
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static void comb(Pos[] sel, int idx, int k) {
		if(k == sel.length) {
			solve(sel);
			return;
		}
		
		if(idx == virus.size()) {
			return;
		}
		
		sel[k] = virus.get(idx);
		comb(sel, idx+1, k+1);
		comb(sel, idx+1, k);
	}
	
	static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}
