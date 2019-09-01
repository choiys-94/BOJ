package boj;

import java.util.Arrays;
import java.util.Scanner;

public class _BOJ_17136_색종이붙이기 {
	static int N = 10;
	static int MIN = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] map = new int[N][N];
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) {
					cnt++;
				}
			}
		}
		if(cnt == 0) {
			System.out.println(0);
			System.exit(0);
		}
		solve(map, new int[] {0, 5, 5, 5, 5, 5}, cnt, 1, 0);
		
		if(MIN == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(MIN);
		}
	}
	
	static void solve(int[][] map, int[] s, int cnt, int idx, int line) {
		if(MIN-1 < idx) {
			return;
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(MIN-1 < idx) {
					return;
				}
				if(map[i][j] == 1) {
					for(int k=5; k>0; k--) {
						if(MIN-1 < idx) {
							return;
						}
						int[][] tmp = new int[N][N];
						tmp = paste(map, i, j, k);
						if(tmp != null) {
							s[k]--;
							if(s[k] < 0) {
								s[k]++;
								continue;
							}
							cnt -= k*k;
							if(cnt == 0) {
								MIN = Math.min(MIN, idx);
								cnt += k*k;
								return;
							}
							solve(tmp, Arrays.copyOfRange(s, 0, s.length), cnt, idx+1, i);
							cnt += k*k;
						}
					}
				}
			}
		}
	}
	
	static int[][] paste(int[][] map, int x, int y, int size) {
		if(!check(x, y, size)){
			return null;
		}
		int[][] tmp = new int[N][N];
		for(int i=0; i<N; i++) {
			System.arraycopy(map[i], 0, tmp[i], 0, map[i].length);
		}

		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(tmp[i][j] == 0) {
					return null;
				}
				else {
					tmp[i][j] = 0;
				}
			}
		}
		return tmp;
	}
	
	static boolean check(int x, int y, int size) {
		return x>=0 && y>=0 && x+size-1<N && y+size-1<N;
	}
}
