package boj;

import java.util.Scanner;

public class BOJ_16926_배열돌리기1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int R = sc.nextInt();
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		
		for(int r=0; r<R; r++) {
			for(int i=0; i<Math.min(N, M)/2; i++) {
				int x = i;
				int y = i;
				int dir = 0;
				int cnt = 0;
				int t = map[x][y];
				while(true) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					int tmp = t;
					if(dir == 3 && nx == i && ny == i) {
						map[nx][ny] = tmp;
						break;
					}
					if(cnt != 0 && (nx == i-1 || nx == N-i || ny == i-1 || ny == M-i)) {
						dir++;
						continue;
					}
					t = map[nx][ny];
					map[nx][ny] = tmp;
					x = nx;
					y = ny;
					cnt++;
				}
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
