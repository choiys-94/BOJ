package boj;

import java.util.Scanner;

public class BOJ_14500_테트리미노 {
	static int N, M;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				t1(i, j);
				t2(i, j);
				t3(i, j);
				t4(i, j);
				t5(i, j);
			}
		}
		
		System.out.println(result);
	}
	
	static int result = Integer.MIN_VALUE;
	
	static void t1(int r, int c) {
		int c1 = c;
		int c2 = c + 1;
		int c3 = c + 2;
		int c4 = c + 3;
		int r1 = r;
		int r2 = r + 1;
		int r3 = r + 2;
		int r4 = r + 3;
		if(check(r, c1) && check(r, c2) && check(r, c3) && check(r, c4)) {
			result = Math.max(result, map[r][c1] + map[r][c2] + map[r][c3] + map[r][c4]);
		}
		if(check(r1, c) && check(r2, c) && check(r3, c) && check(r4, c)) {
			result = Math.max(result, map[r1][c] + map[r2][c] + map[r3][c] + map[r4][c]);
		}
	}
	
	static void t2(int r, int c) {
		if(check(r, c) && check(r, c+1) && check(r+1, c) && check(r+1, c+1)) {
			result = Math.max(result, map[r][c] + map[r][c+1] + map[r+1][c] + map[r+1][c+1]);
		}
	}
	
	static void t3(int r, int c) {
		if(check(r, c) && check(r+1, c) && check(r+2, c) && check(r+2, c+1)) {
			result = Math.max(result, map[r][c] + map[r+1][c] + map[r+2][c] + map[r+2][c+1]);
		}
		if(check(r, c) && check(r+1, c) && check(r+2, c) && check(r+2, c-1)) {
			result = Math.max(result, map[r][c] + map[r+1][c] + map[r+2][c] + map[r+2][c-1]);
		}
		
		if(check(r, c) && check(r+1, c) && check(r+1, c+1) && check(r+1, c+2)) {
			result = Math.max(result, map[r][c] + map[r+1][c] + map[r+1][c+1] + map[r+1][c+2]);
		}
		if(check(r, c) && check(r+1, c) && check(r, c+1) && check(r, c+2)) {
			result = Math.max(result, map[r][c] + map[r+1][c] + map[r][c+1] + map[r][c+2]);
		}
		
		if(check(r, c) && check(r, c+1) && check(r+1, c) && check(r+2, c)) {
			result = Math.max(result, map[r][c] + map[r][c+1] + map[r+1][c] + map[r+2][c]);
		}
		if(check(r, c) && check(r, c-1) && check(r+1, c) && check(r+2, c)) {
			result = Math.max(result, map[r][c] + map[r][c-1] + map[r+1][c] + map[r+2][c]);
		}
		
		if(check(r, c) && check(r, c+1) && check(r, c+2) && check(r+1, c+2)) {
			result = Math.max(result, map[r][c] + map[r][c+1] + map[r][c+2] + map[r+1][c+2]);
		}
		if(check(r, c) && check(r, c+1) && check(r, c+2) && check(r-1, c+2)) {
			result = Math.max(result, map[r][c] + map[r][c+1] + map[r][c+2] + map[r-1][c+2]);
		}
	}
	
	static void t4(int r, int c) {
		if(check(r, c) && check(r+1, c) && check(r+1, c+1) && check(r+2, c+1)) {
			result = Math.max(result, map[r][c] + map[r+1][c] + map[r+1][c+1] + map[r+2][c+1]);
		}
		if(check(r, c) && check(r+1, c) && check(r+1, c-1) && check(r+2, c-1)) {
			result = Math.max(result, map[r][c] + map[r+1][c] + map[r+1][c-1] + map[r+2][c-1]);
		}
		
		if(check(r, c) && check(r, c+1) && check(r-1, c+1) && check(r-1, c+2)) {
			result = Math.max(result, map[r][c] + map[r][c+1] + map[r-1][c+1] + map[r-1][c+2]);
		}
		if(check(r, c) && check(r, c+1) && check(r+1, c+1) && check(r+1, c+2)) {
			result = Math.max(result, map[r][c] + map[r][c+1] + map[r+1][c+1] + map[r+1][c+2]);
		}
	}
	
	static void t5(int r, int c) {
		if(check(r, c) && check(r, c+1) && check(r+1, c+1) && check(r, c+2)) {
			result = Math.max(result, map[r][c] + map[r][c+1] + map[r+1][c+1] + map[r][c+2]);
		}
		if(check(r, c) && check(r, c+1) && check(r+1, c+1) && check(r-1, c+1)) {
			result = Math.max(result, map[r][c] + map[r][c+1] + map[r+1][c+1] + map[r-1][c+1]);
		}
		if(check(r, c) && check(r, c+1) && check(r-1, c+1) && check(r, c+2)) {
			result = Math.max(result, map[r][c] + map[r][c+1] + map[r-1][c+1] + map[r][c+2]);
		}
		if(check(r, c) && check(r+1, c) && check(r+2, c) && check(r+1, c+1)) {
			result = Math.max(result, map[r][c] + map[r+1][c] + map[r+2][c] + map[r+1][c+1]);
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
}
