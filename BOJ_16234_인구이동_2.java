package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_16234_인구이동_2 {
	static class Pos{
		int r;
		int c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int N, L, R;
	static int[][] map;
	static boolean flag = true;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 맵 크기
		L = sc.nextInt(); // L부터
		R = sc.nextInt(); // R까지
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		Queue<Pos> queue = new LinkedList<>();
		ArrayList<Pos> toMod = new ArrayList<>();
		int ans = 0;
		while(flag) {
			flag = false;
			boolean[][] visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					queue.clear();
					toMod.clear();
					int sum = 0;
					if(visited[i][j])
						continue;
					queue.add(new Pos(i, j));
					visited[i][j] = true;
					while(!queue.isEmpty()) {
						Pos q = queue.poll();
						toMod.add(new Pos(q.r, q.c));
						sum += map[q.r][q.c];
						for(int d=0; d<4; d++) {
							int nr = q.r + dr[d];
							int nc = q.c + dc[d];
							if(!check(nr, nc))
								continue;
							if(visited[nr][nc])
								continue;
							if(isOk(map[q.r][q.c], map[nr][nc])) {
								queue.add(new Pos(nr, nc));
								visited[nr][nc] = true;
								flag = true;
							}
						}
					}
					if(!toMod.isEmpty()) {
						sum /= toMod.size();
						for(Pos p: toMod) {
							map[p.r][p.c] = sum; 
						}
					}
				}
			}
			if(flag)
				ans++;
		}
		System.out.println(ans);
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static boolean isOk(int a, int b) {
		int tmp = Math.abs(a-b);
		return tmp >= L && tmp <= R;
	}
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
}
