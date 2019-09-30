package boj;

import java.util.Scanner;

public class BOJ_1018_체스판다시칠하기 {
	static int N, M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		int min = Integer.MAX_VALUE;
		char[][] map = new char[N][M];
		for(int i=0; i<N; i++) {
			map[i] = sc.next().toCharArray();
		}
		
		for(int i=0; i<=N-8; i++) {
			for(int j=0; j<=M-8; j++) {
				min = Math.min(min, check(map, i, j));
			}
		}
		
		System.out.println(min);
	}
	
	static int check(char[][] map, int x, int y) {
		int result = 0;
		int min = Integer.MAX_VALUE;
		char[][] tmap = new char[N][M];
		for(int i=0; i<N; i++)
			System.arraycopy(map[i], 0, tmap[i], 0, map[i].length);
		char token = 'W';
		for(int i=x; i<x+8; i++) {
			if(tmap[i][y] != token ) {
				result++;
				tmap[i][y] = token;
			}
			for(int j=y; j<y+7; j++) {
				token = (tmap[i][j] == 'W' ? 'B' : 'W');
				if(tmap[i][j] == tmap[i][j+1]) {
					result++;
					tmap[i][j+1] = token;
				}
			}
		}
		min = Math.min(result, min);
		result = 0;
		for(int i=0; i<N; i++)
			System.arraycopy(map[i], 0, tmap[i], 0, map[i].length);
		token = 'B';
		for(int i=x; i<x+8; i++) {
			if(tmap[i][y] != token ) {
				result++;
				tmap[i][y] = token;
			}
			for(int j=y; j<y+7; j++) {
				token = (tmap[i][j] == 'W' ? 'B' : 'W');
				if(tmap[i][j] == tmap[i][j+1]) {
					result++;
					tmap[i][j+1] = token;
				}
			}
		}
		min = Math.min(result, min);
		return min;
	}
}
