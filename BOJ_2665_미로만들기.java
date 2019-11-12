package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2665_미로만들기 {
	static class Pos{
		int r, c, cnt;
		Pos(int r, int c, int cnt){
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static int N;
	static int[][] map;
	static boolean[][] tvisited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][N];
		tvisited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			String in = sc.next();
			for(int j=0; j<N; j++) {
				if(in.charAt(j) == '0')
					map[i][j] = 987654321;
				else
					map[i][j] = 123456789;
				
			}
		}
		Queue<Pos> queue = new LinkedList<>();
		Queue<Pos> black = new LinkedList<>();
		black.add(new Pos(0, 0, 0));
		map[0][0] = 0;
		while(!black.isEmpty()) {
			Pos b = black.poll();
			queue.add(new Pos(b.r, b.c, b.cnt));
			while(!queue.isEmpty()) {
				Pos q = queue.poll();
				for(int d=0; d<4; d++) {
					int nr = q.r + dr[d];
					int nc = q.c + dc[d];
					if(!check(nr, nc))
						continue;
					if(map[nr][nc] <= map[q.r][q.c])
						continue;
					if(map[nr][nc] == 123456789) {
						map[nr][nc] = q.cnt;
						queue.add(new Pos(nr, nc, q.cnt));
					}
					else if(map[nr][nc] == 987654321){
						map[nr][nc] = q.cnt+1;
						black.add(new Pos(nr, nc, q.cnt+1));
					}
				}
			}
		}
		System.out.println(map[N-1][N-1]);
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
