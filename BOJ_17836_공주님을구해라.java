package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17836_공주님을구해라 {
	static class Node{
		int r, c, time;
		int sword;
		Node(int r, int c, int time, int sword){
			this.r = r;
			this.c = c;
			this.time = time;
			this.sword = sword;
		}
	}
	static int N, M, T;
	static int str, stc, edr, edc;
	static int[][] map;
	static boolean[][][] visited;
	static int ans = 987654321;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 행 
		M = sc.nextInt(); // 열
		T = sc.nextInt(); // 시간
		map = new int[N][M];
		visited = new boolean[N][M][2];
		edr = N-1;
		edc = M-1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0, 0, 0, 0));
		visited[0][0][0] = true;
		while(!queue.isEmpty()) {
			Node q = queue.poll();
			if(q.time > T)
				break;
			if(q.r == edr && q.c == edc) {
				ans = Math.min(q.time, ans);
				break;
			}
			for(int d=0; d<4; d++) {
				int nr = q.r + dr[d];
				int nc = q.c + dc[d];
				if(!check(nr, nc))
					continue;
				if(visited[nr][nc][q.sword])
					continue;
				if(q.sword == 0 && map[nr][nc] == 1)
					continue;
				if(map[nr][nc] == 2)
					q.sword = 1;
				visited[nr][nc][q.sword] = true;
				queue.add(new Node(nr, nc, q.time+1, q.sword));
			}
		}
		if(ans == 987654321)
			System.out.println("Fail");
		else
			System.out.println(ans);
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
