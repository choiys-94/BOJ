package boj;

import java.util.Scanner;

public class BOJ_2823_유턴싫어 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		char[][] map = new char[N][M];
		int dx[] = {1, 0, -1, 0};
		int dy[] = {0, 1, 0, -1};
		
		for(int i=0; i<N; i++) {
			String in = sc.next();
			for(int j=0; j<M; j++) {
				map[i][j] = in.charAt(j);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == '.') {
					int cnt = 0;
					for(int k=0; k<4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if(check(nx, ny, N, M) && map[nx][ny] == '.') {
							cnt++;
						}
					}
					if(cnt < 2) {
						System.out.println(1);
						System.exit(0);
					}
				}
			}
		}
		
		System.out.println(0);
	}
	
	static boolean check(int x, int y, int N, int M) {
		return x>=0 && y>=0 && x<N && y<M;
	}
}
