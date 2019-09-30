package boj;

import java.util.Scanner;

public class BOJ_16927_배열돌리기2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		long R = sc.nextLong();
		int[][] map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		long token = 1;
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		for(int i=0; i<Math.min(N, M)/2; i++) {
			for(int k=0; k<R%((N-i*2-1)*2 + (M-i*2-1)*2); k++){
				int x = i;
				int y = i;
				int t = map[x][y];
				int dir = 0;
				boolean flag = false;
				while(true) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					int tmp = t;
					if(dir != 0 && nx == i && ny == i) {
						map[nx][ny] = tmp;
						break;
					}
					if(flag && (nx == i-1 || nx == N-i || ny == i-1 || ny == M-i)) {
						dir++;
						continue;
					}
					t = map[nx][ny];
					map[nx][ny] = tmp;
					x = nx;
					y = ny;
					flag = true;
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
