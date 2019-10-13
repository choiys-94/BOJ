package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_4963_섬의개수 {
	static int N, M;
	static class Pos{
		int r;
		int c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true){
			M = sc.nextInt();
			N = sc.nextInt();
			if(N == 0 && M == 0)
				break;
			
			int[][] map = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			int result = 0;
			boolean[][] visited = new boolean[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 1 && visited[i][j] == false) {
						result++;
						visited[i][j] = true;
						Queue<Pos> queue = new LinkedList<>();
						queue.add(new Pos(i, j));
						while(!queue.isEmpty()) {
							Pos q = queue.poll();
							for(int d=0; d<dr.length; d++) {
								int nr = q.r + dr[d];
								int nc = q.c + dc[d];
								if(check(nr, nc) && map[nr][nc] == 1 && visited[nr][nc] == false) {
									visited[nr][nc] = true;
									queue.add(new Pos(nr, nc));
								}
							}
						}
					}
				}
			}
			
			System.out.println(result);
		}
	}
	
	static int[] dr = {0, 1, 0, -1, 1, -1, 1, -1};
	static int[] dc = {1, 0, -1, 0, 1, 1, -1, -1};
	
	static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
}
