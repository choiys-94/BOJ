package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _BOJ_9328_열쇠 {
	static class Pos{
		int r, c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int N, M;
	static int[][] map;
	static boolean[] fkey;
	static boolean[][][] visited;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			fkey = new boolean[27];
			visited = new boolean[N][M][27];
			HashMap<Integer, ArrayList<Pos>> door = new HashMap<>();
			for(int i=0; i<N; i++) {
				String in = br.readLine().trim();
				for(int j=0; j<M; j++) {
					char ch = in.charAt(j);
					if(ch == '*')
						map[i][j] = -1;
					else if(ch == '.')
						map[i][j] = 0;
					else if(Character.isLowerCase(ch)) {
						map[i][j] = ch - 'a' + 1;
						if(i == 0 || j == 0 || i == N-1 || j == M-1) {
							fkey[ch-'a'+1] = true;
							map[i][j] = 0;
						}
					}
					else if(Character.isUpperCase(ch)) {
						map[i][j] = ch - 'A' + 100 + 1;
					}
					else if(ch == '$')
						map[i][j] = -9;
				}
			}
			
			
			String in = br.readLine();
			if(!in.equals("0")) {
				for(int i=0; i<in.length(); i++) {
					fkey[in.charAt(i)-'a'+1] = true;
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] > 100 && fkey[map[i][j]-100])
						map[i][j] = 0;
					else if(map[i][j] > 100) {
						if(!door.containsKey(map[i][j]-100))
							door.put(map[i][j]-100, new ArrayList<Pos>());
						door.get(map[i][j]-100).add(new Pos(i, j));
					}
				}
			}
			
			Queue<Pos> queue = new LinkedList<>();
			// 좌
			for(int r=1; r<N; r++) {
				int c = 0;
				if(map[r][c] == 0)
					queue.add(new Pos(r, c));
			}
			
			// 상
			for(int c=0; c<M-1; c++) {
				int r = 0;
				if(map[r][c] == 0)
					queue.add(new Pos(r, c));
			}
			
			// 우
			for(int r=0; r<N-1; r++) {
				int c = M-1;
				if(map[r][c] == 0)
					queue.add(new Pos(r, c));
			}
			
			// 하
			for(int c=1; c<M; c++) {
				int r = N-1;
				if(map[r][c] == 0)
					queue.add(new Pos(r, c));
			}
			
			ans = 0;
			int cur = 0;
			while(!queue.isEmpty()) {
				Pos q = queue.poll();
				for(int d=0; d<4; d++) {
					int nr = q.r + dr[d];
					int nc = q.c + dc[d];
					if(!check(nr, nc))
						continue;
					if(map[nr][nc] == -1)
						continue;
					if(visited[nr][nc][cur])
						continue;
					if(map[nr][nc] > 100) {
						if(!fkey[map[nr][nc]-100])
							continue;
						map[nr][nc] = 0;
					}
					if(map[nr][nc] > 0 && map[nr][nc] < 27) {
						fkey[map[nr][nc]] = true;
						cur = map[nr][nc];
						if(door.containsKey(map[nr][nc])) {
							for(Pos p: door.get(map[nr][nc])) {
								map[p.r][p.c] = 0;
							}
							door.remove(map[nr][nc]);
						}
						map[nr][nc] = 0;
					}
					visited[nr][nc][cur] = true;
					queue.add(new Pos(nr, nc));
				}
			}
			
			queue.clear();
			queue = new LinkedList<>();
			visited = new boolean[N][M][1];
			// 좌
			for(int r=1; r<N; r++) {
				int c = 0;
				if(map[r][c] == 0)
					queue.add(new Pos(r, c));
				else if(map[r][c] > 100 && fkey[map[r][c]-100])
					queue.add(new Pos(r, c));
			}
			
			// 상
			for(int c=0; c<M-1; c++) {
				int r = 0;
				if(map[r][c] == 0)
					queue.add(new Pos(r, c));
				else if(map[r][c] > 100 && fkey[map[r][c]-100])
					queue.add(new Pos(r, c));
			}
			
			// 우
			for(int r=0; r<N-1; r++) {
				int c = M-1;
				if(map[r][c] == 0)
					queue.add(new Pos(r, c));
				else if(map[r][c] > 100 && fkey[map[r][c]-100])
					queue.add(new Pos(r, c));
			}
			
			// 하
			for(int c=1; c<M; c++) {
				int r = N-1;
				if(map[r][c] == 0)
					queue.add(new Pos(r, c));
				else if(map[r][c] > 100 && fkey[map[r][c]-100])
					queue.add(new Pos(r, c));
			}
			
			while(!queue.isEmpty()) {
				Pos q = queue.poll();
				for(int d=0; d<4; d++) {
					int nr = q.r + dr[d];
					int nc = q.c + dc[d];
					if(!check(nr, nc))
						continue;
					if(map[nr][nc] == -1)
						continue;
					if(visited[nr][nc][0])
						continue;
					if(map[nr][nc] > 100) {
						if(!fkey[map[nr][nc]-100])
							continue;
						map[nr][nc] = 0;
					}
					if(map[nr][nc] > 0 && map[nr][nc] < 27) {
						fkey[map[nr][nc]] = true;
						map[nr][nc] = 0;
					}
					if(map[nr][nc] == -9) {
						map[nr][nc] = 0;
						ans++;
					}
					visited[nr][nc][0] = true;
					queue.add(new Pos(nr, nc));
				}
			}
			
			System.out.println(ans);
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
