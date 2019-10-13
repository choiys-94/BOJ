package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10342_FunHouse {
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = 0;
		StringBuilder sb = new StringBuilder();
		while(true) {
			tc++;
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0)
				break;
			
			int r = -1, c = -1;
			char[][] map = new char[N][M];
			for(int i=0; i<N; i++) {
				String in = br.readLine().trim();
				for(int j=0; j<M; j++) {
					char ch = in.charAt(j);
					map[i][j] = ch;
					if(map[i][j] == '*') {
						r = i;
						c = j;
					}
				}
			}
			
			int dir = 0;
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(check(nr, nc) && !(map[nr][nc] == 'x')) {
					dir = i;
					break;
				}
			}
			
			int nr = r;
			int nc = c;
			while(true) {
				nr += dr[dir];
				nc += dc[dir];
				if(!check(nr, nc))
					continue;
				if(map[nr][nc] == 'x') {
					map[nr][nc] = '&';
					break;
				}
				switch(dir) {
				case 0:
					if(map[nr][nc] == '/') dir = 3;
					else if(map[nr][nc] == '\\') dir = 1;
					break;
				case 1:
					if(map[nr][nc] == '/') dir = 2;
					else if(map[nr][nc] == '\\') dir = 0;
					break;
				case 2:
					if(map[nr][nc] == '/') dir = 1;
					else if(map[nr][nc] == '\\') dir = 3;
					break;
				case 3:
					if(map[nr][nc] == '/') dir = 0;
					else if(map[nr][nc] == '\\') dir = 2;
					break;
				}
			}
//			System.out.println("HOUSE "+tc);
			sb.append("HOUSE ").append(tc).append("\n");
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
//					System.out.print(map[i][j]);
					sb.append(map[i][j]);
				}
//				System.out.println();
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	//				      우    하     좌     상
	static int dr[] = {0, 1, 0, -1};
	static int dc[] = {1, 0, -1, 0};
}
