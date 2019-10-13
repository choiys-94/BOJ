package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17129_윌리암슨수액빨이딱따구리가정보섬에올라온이유 {
	static int N, M;
	static int str, stc;
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
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			String in = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = in.charAt(j) - '0';
				if(map[i][j] == 2) {
					map[i][j] = 0;
					str = i;
					stc = j;
				}
			}
		}
		
		boolean[][] visited = new boolean[N][M];
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(str, stc, 0));
		visited[str][stc] = true;
		int res = -1;
		while(!queue.isEmpty()) {
			Pos q = queue.poll();
			if(map[q.r][q.c] != 0 && map[q.r][q.c] != 1) {
				res = q.time;
				break;
			}
			for(int i=0; i<4; i++) {
				int nr = q.r + dr[i];
				int nc = q.c + dc[i];
				if(check(nr, nc) && !visited[nr][nc] && map[nr][nc] != 1) {
					visited[nr][nc] = true;
					queue.add(new Pos(nr, nc, q.time + 1));
				}
			}
		}
		
		if(res != -1) {
			System.out.println("TAK");
			System.out.println(res);
		}
		else {
			System.out.println("NIE");
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
