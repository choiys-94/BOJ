package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14430_자원캐기 {
	static int N, M;
	static int[][] map;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = map[0][0];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int d=0; d<2; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(!check(nr, nc))
						continue;
					dp[i][j] = Math.max(dp[nr][nc] + map[i][j], dp[i][j]);
				}
			}
		}
		
		System.out.println(dp[N-1][M-1]);
	}

	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[] dr = {-1, 0};
	static int[] dc = {0, -1};
}
