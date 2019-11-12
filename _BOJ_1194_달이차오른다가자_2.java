package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _BOJ_1194_달이차오른다가자_2 {
	static class Pos{
		int r, c, time;
		boolean[] key = new boolean[27];
		Pos(int r, int c, int time){
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int N, M;
	static int str, stc;
	static char[][] map;
	static boolean[][][] visited;
	static boolean[] key;
	static int ans = 987654321;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][7];
		key = new boolean[7];
		for(int i=0; i<N; i++) {
			String in = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = in.charAt(j);
				if(map[i][j] == '0') {
					str = i;
					stc = j;
					map[i][j] = '.';
				}
			}
		}
		
		int cur = 0;
		Queue<Pos> queue = new LinkedList<>();
		queue = new LinkedList<>();
		queue.add(new Pos(str, stc, 0));
		visited[str][stc][0] = true;
		
		while(!queue.isEmpty()) {
			Pos q = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = q.r + dr[d];
				int nc = q.c + dc[d];
				if(!check(nr, nc))
					continue;
				if(visited[nr][nc][cur])
					continue;
				if(Character.isUpperCase(map[nr][nc])) {
					
					continue;
				}
				if(Character.isLowerCase(map[nr][nc])) {
					cur = map[nr][nc]-'a'+1;
					key[cur] = true;
					map[nr][nc] = '.';
				}
				if(map[nr][nc] == '1') {
					ans = Math.min(ans, q.time+1);
					break;
				}
				visited[nr][nc][cur] = true;
				queue.add(new Pos(nr, nc, q.time+1));
			}
		}
		
		ans = (ans == 987654321) ? -1 : ans;
		System.out.println(ans);
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
