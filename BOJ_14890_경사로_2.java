package boj;

import java.util.Scanner;

public class BOJ_14890_경사로_2 {
	static int N, L;
	static int[][] map;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 맵 길이
			L = sc.nextInt(); // 경사로 길이
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			ans = 0;
			for(int i=0; i<N; i++) {
				row(i, 0, new boolean[N]);
				col(0, i, new boolean[N]);
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void row(int col, int row, boolean[] visited) {
		if(row == N-1) {
			ans++;
			return;
		}
		if(!check(col, row+1))
			return;
		if(map[col][row] - map[col][row+1] == -1) {
			if(!check(col, row-L+1))
				return;
			for(int i=0; i<L; i++) {
				if(map[col][row] != map[col][row-i] || visited[row-i]) {
					return;
				}
				visited[row-i] = true;
			}
			row(col, row+1, visited);
		}
		else if(map[col][row] - map[col][row+1] == 1) {
			if(!check(col, row+L))
				return;
			for(int i=1; i<=L; i++) {
				if(map[col][row+1] != map[col][row+i] || visited[row+i]) {
					return;
				}
				visited[row+i] = true;
			}
			row(col, row+L, visited);
		}
		else if(map[col][row] == map[col][row+1]) {
			row(col, row+1, visited);
		}
	}
	
	static void col(int col, int row, boolean[] visited) {
		if(col == N-1) {
			ans++;
			return;
		}
		if(!check(col+1, row))
			return;
		if(map[col][row] - map[col+1][row] == -1) {
			if(!check(col-L+1, row))
				return;
			for(int i=0; i<L; i++) {
				if(map[col][row] != map[col-i][row] || visited[col-i]) {
					return;
				}
				visited[col-i] = true;
			}
			col(col+1, row, visited);
		}
		else if(map[col][row] - map[col+1][row] == 1) {
			if(!check(col+L, row))
				return;
			for(int i=1; i<=L; i++) {
				if(map[col+1][row] != map[col+i][row] || visited[col+i]) {
					return;
				}
				visited[col+i] = true;
			}
			col(col+L, row, visited);
		}
		else if(map[col][row] == map[col+1][row]) {
			col(col+1, row, visited);
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
}
