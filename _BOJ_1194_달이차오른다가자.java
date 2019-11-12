package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _BOJ_1194_달이차오른다가자 {
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
	static boolean[][] visited;
	static boolean[] key;
	static int ans = 987654321;
	static Pos[] keyPos;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		key = new boolean[7];
		keyPos = new Pos[7];
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
		Queue<Pos>[] queue = new Queue[7];
		Queue<Integer> keyQueue = new LinkedList<>();
		queue[0] = new LinkedList<>();
		queue[0].add(new Pos(str, stc, 0));
		keyQueue.add(0);
		visited[str][stc] = true;
		while(!keyQueue.isEmpty()) {
			cur = keyQueue.poll();
			visited = new boolean[N][M];
			while(!queue[cur].isEmpty()) {
				Pos q = queue[cur].poll();
				for(int d=0; d<4; d++) {
					int nr = q.r + dr[d];
					int nc = q.c + dc[d];
					if(!check(nr, nc))
						continue;
					if(visited[nr][nc])
						continue;
					if(map[nr][nc] == '#')
						continue;
					if(Character.isUpperCase(map[nr][nc]) && !q.key[map[nr][nc]-'A'+1])
						continue;
					if(Character.isLowerCase(map[nr][nc])) {
						int tmpCur = map[nr][nc]-'a'+1;
						keyQueue.add(tmpCur);
						queue[tmpCur] = new LinkedList<>();
						Pos tmp = new Pos(nr, nc, q.time+1);
						System.arraycopy(q.key, 0, tmp.key, 0, q.key.length);
						tmp.key[tmpCur] = true;
						visited[nr][nc] = true;
						queue[tmpCur].add(tmp);
						map[nr][nc] = '.';
						continue;
					}
					if(map[nr][nc] == '1') {
						ans = Math.min(ans, q.time+1);
					}
					visited[nr][nc] = true;
					Pos tmp = new Pos(nr, nc, q.time+1);
					System.arraycopy(q.key, 0, tmp.key, 0, q.key.length);
					queue[cur].add(tmp);
				}
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
