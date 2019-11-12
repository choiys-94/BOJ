package boj;

import java.util.Scanner;

public class BOJ_4672_Dont_Get_Rooked {
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			N = sc.nextInt();
			if(N == 0)
				break;
			int[][] map = new int[N][N];
			for(int i=0; i<N; i++) {
				String in = sc.next();
				for(int j=0; j<N; j++) {
					char ch = in.charAt(j);
					if(ch == '.')
						map[i][j] = 0;
					else
						map[i][j] = 1;
				}
			}
			ans = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == 0) {
						map[i][j] = 2;
						dfs(map, i, j, 1);
						map[i][j] = 0;
					}
				}
			}
			System.out.println(ans);
		}
	}

	static int ans;
	
	static void dfs(int[][] map, int r, int c, int cnt) {
		int[][] tmap = new int[N][N];
		for(int i=0; i<N; i++)
			System.arraycopy(map[i], 0, tmap[i], 0, map[i].length);
		
		ans = Math.max(ans, cnt);
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(!check(nr, nc))
				continue;
			if(tmap[nr][nc] == 1)
				continue;
			while(true) {
				if(!check(nr, nc))
					break;
				if(tmap[nr][nc] == 1)
					break;
				tmap[nr][nc] = 2;
				nr += dr[d];
				nc += dc[d];
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(tmap[i][j] == 0) {
					tmap[i][j] = 2;
					dfs(tmap, i, j, cnt+1);
					tmap[i][j] = 0;
				}
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
