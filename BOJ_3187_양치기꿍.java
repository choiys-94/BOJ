package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3187_양치기꿍 {
	static class Pos{
		int r;
		int c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int N, M;
	static int sheepCnt, wolfCnt;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		ArrayList<Pos> exist = new ArrayList<>();
		for(int i=0; i<N; i++) {
			String in = br.readLine();
			for(int j=0; j<M; j++) {
				switch(in.charAt(j)) {
				case '.':
					map[i][j] = 0;
					break;
				case '#':
					map[i][j] = 1;
					break;
				case 'v':
					map[i][j] = 2;
					exist.add(new Pos(i, j));
					break;
				case 'k':
					map[i][j] = 3;
					exist.add(new Pos(i, j));
					break;
				}
			}
		}
		
		boolean[][] visited = new boolean[N][M];
		
		for(Pos e: exist) {
			int sheep = 0, wolf = 0;
			int r = e.r;
			int c = e.c;
			if(visited[r][c])
				continue;
			Queue<Pos> queue = new LinkedList<>();
			queue.add(new Pos(r, c));
			visited[r][c] = true;
			if(map[r][c] == 2)
				wolf++;
			else
				sheep++;
			while(!queue.isEmpty()) {
				Pos q = queue.poll();
				for(int d=0; d<4; d++) {
					int nr = q.r + dr[d];
					int nc = q.c + dc[d];
					if(!check(nr, nc))
						continue;
					if(visited[nr][nc])
						continue;
					if(map[nr][nc] == 1)
						continue;
					if(map[nr][nc] == 2)
						wolf++;
					else if(map[nr][nc] == 3)
						sheep++;
					visited[nr][nc] = true;
					queue.add(new Pos(nr, nc));
				}
			}
			if(sheep > wolf)
				wolf = 0;
			else
				sheep = 0;
			wolfCnt += wolf;
			sheepCnt += sheep;
		}
		
		System.out.println(sheepCnt + " " + wolfCnt);
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
