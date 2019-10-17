package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_12100_2048_Easy {
	static int N;
	static int[][] map;
	static int[][] tMap;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][N];
		tMap = new int[N][N];
		visited = new boolean[N][N];
		int[] arr = new int[4];
		
		for(int i=0; i<4; i++) {
			arr[i] = i;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		//방향 경우의 수
		comb(arr, new int[5], 0, 0);
		
		System.out.println(ans);
	}
	
	static int ans = 0;
	static boolean[][] visited;
	// 우 하 좌 상
	static void move(int dir) {
		for(int i=0; i<N; i++) {
			Arrays.fill(visited[i], false);
		}
		switch(dir) {
		case 0:
			for(int i=0; i<N; i++) {
				for(int j=N-1; j>=0; j--) {
					int token = tMap[i][j];
					ans = Math.max(ans, token);
					if(tMap[i][j] == 0)
						continue;
					int nr = i;
					int nc = j;
					while(true) {
						nr += dr[dir];
						nc += dc[dir];
						if(!check(nr, nc)) {
							tMap[i][j] = 0;
							tMap[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
						if(tMap[nr][nc] == 0)
							continue;
						if(tMap[nr][nc] == token && !visited[nr][nc]) {
							tMap[i][j] = 0;
							tMap[nr][nc] = token*2;
							ans = Math.max(ans, token*2);
							visited[nr][nc] = true;
							break;
						}
						else {
							tMap[i][j] = 0;
							tMap[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
					}
				}
			}
			break;
		case 1:
			for(int i=0; i<N; i++) {
				for(int j=N-1; j>=0; j--) {
					int token = tMap[j][i];
					ans = Math.max(ans, token);
					if(tMap[j][i] == 0)
						continue;
					int nr = j;
					int nc = i;
					while(true) {
						nr += dr[dir];
						nc += dc[dir];
						if(!check(nr, nc)) {
							tMap[j][i] = 0;
							tMap[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
						if(tMap[nr][nc] == 0)
							continue;
						if(tMap[nr][nc] == token && !visited[nr][nc]) {
							tMap[j][i] = 0;
							tMap[nr][nc] = token*2;
							ans = Math.max(ans, token*2);
							visited[nr][nc] = true;
							break;
						}
						else {
							tMap[j][i] = 0;
							tMap[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
					}
				}
			}
			break;
		case 2:
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int token = tMap[i][j];
					ans = Math.max(ans, token);
					if(tMap[i][j] == 0)
						continue;
					int nr = i;
					int nc = j;
					while(true) {
						nr += dr[dir];
						nc += dc[dir];
						if(!check(nr, nc)) {
							tMap[i][j] = 0;
							tMap[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
						if(tMap[nr][nc] == 0)
							continue;
						if(tMap[nr][nc] == token && !visited[nr][nc]) {
							tMap[i][j] = 0;
							tMap[nr][nc] = token*2;
							ans = Math.max(ans, token*2);
							visited[nr][nc] = true;
							break;
						}
						else {
							tMap[i][j] = 0;
							tMap[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
					}
				}
			}
			break;
		case 3:
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int token = tMap[j][i];
					ans = Math.max(ans, token);
					if(tMap[j][i] == 0)
						continue;
					int nr = j;
					int nc = i;
					while(true) {
						nr += dr[dir];
						nc += dc[dir];
						if(!check(nr, nc)) {
							tMap[j][i] = 0;
							tMap[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
						if(tMap[nr][nc] == 0)
							continue;
						if(tMap[nr][nc] == token && !visited[nr][nc]) {
							tMap[j][i] = 0;
							tMap[nr][nc] = token*2;
							ans = Math.max(ans, token*2);
							visited[nr][nc] = true;
							break;
						}
						else {
							tMap[j][i] = 0;
							tMap[nr-dr[dir]][nc-dc[dir]] = token;
							break;
						}
					}
				}
			}
			break;
		}
	}
	
	static void play(int[] sel) {
		for(int i=0; i<N; i++) {
			System.arraycopy(map[i], 0, tMap[i], 0, map[i].length);
		}
		for(int i=0; i<sel.length; i++) {
			move(sel[i]);
		}
		
//		for(int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(tMap[i]));
//		}
//		System.out.println("--------------------");
	}
	
	// 방향 경우의 수(중복 조합)
	static void comb(int[] arr, int[] sel, int idx, int k) {
		if(sel.length == k) {
//			System.out.println(Arrays.toString(sel));
			play(sel);
			return;
		}
		
		if(arr.length == idx) {
			return;
		}
		
		sel[k] = idx;
		comb(arr, sel, 0, k+1);
		comb(arr, sel, idx+1, k);
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
