package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_16441_아기돼지와늑대 {
	static class Pos{
		int r;
		int c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int N, M;
	static char[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		Queue<Pos> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			String in = sc.next();
			map[i] = in.toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'W') {
					queue.add(new Pos(i, j));
					visited[i][j] = true;
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Pos q = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = q.r + dr[d];
				int nc = q.c + dc[d];
				if(visited[nr][nc])
					continue;
				if(map[nr][nc] == '+') {
					while(map[nr][nc] == '+') {
						nr += dr[d];
						nc += dc[d];
					}
					if(map[nr][nc] == '#') {
						nr -= dr[d];
						nc -= dc[d];
					}
				}
				if(map[nr][nc] == '#')
					continue;
				if(visited[nr][nc])
					continue;
				visited[nr][nc] = true;
				queue.add(new Pos(nr, nc));
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(i==0 || j==0 || i==N-1 || j==M-1)
					System.out.print("#");
				else if(visited[i][j] == false && map[i][j] == '.')
					System.out.print("P");
				else
					System.out.print(map[i][j]);

			}
			System.out.println();
		}
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
