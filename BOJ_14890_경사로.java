package boj;

import java.util.Scanner;

public class BOJ_14890_경사로 {
	static int N, L;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		solve(map);
		
		System.out.println(result);
	}
	
	static int[] dr = {1, 0};
	static int[] dc = {0, 1};
	static int result = 0;
	
	static void solve(int[][] map) {
		for(int i=0; i<N; i++) {
			int nr = 1;
			int nc = i;
			int cnt = 1;
			boolean flag = true;
			
			out: while(nr < N) {
				if(map[nr-1][nc] == map[nr][nc]) {
					cnt++;
				}
				else {
					if(map[nr-1][nc] - map[nr][nc] == 1) {
						cnt = 0;
						for(int j=0; j<L-1; j++) {
							if(!(check(nr+j+1, nc) && map[nr+j][nc] == map[nr+j+1][nc])) {
								flag = false;
								break out;
							}
						}
						nr += L-1;
					}
					else if(!(map[nr-1][nc] - map[nr][nc] == -1 && cnt >= L)) {
						flag = false;
						break;
					}
					else {
						cnt = 1;
					}
				}
				nr++;
			}
			if(flag)
				result++;
			
			nr = i;
			nc = 1;
			cnt = 1;
			flag = true;
			out: while(nc < N) {
				if(map[nr][nc-1] == map[nr][nc]) {
					cnt++;
				}
				else {
					if(map[nr][nc-1] - map[nr][nc] == 1) {
						cnt = 0;
						for(int j=0; j<L-1; j++) {
							if(!(check(nr, nc+j+1) && map[nr][nc+j] == map[nr][nc+j+1])) {
								flag = false;
								break out;
							}
						}
						nc += L-1;
					}
					else if(!(map[nr][nc-1] - map[nr][nc] == -1 && cnt >= L)) {
						flag = false;
						break;
					}
					else {
						cnt = 1;
					}
				}
				nc++;
			}
			if(flag)
				result++;
		}
	}
	
	static boolean check(int r, int c) {
		return r<N && c<N;
	}
}
