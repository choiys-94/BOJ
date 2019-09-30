package boj;

import java.util.Scanner;

public class BOJ_2630_색종이만들기 {
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		dfs(map);
		System.out.println(white);
		System.out.println(blue);
	}
	
	static int blue, white;
	
	static void dfs(int[][] map) {
		if(map.length == 1) {
			if(map[0][0] == 1)
				blue++;
			else
				white++;
			return;
		}
		if(isOk(map)) {
			if(map[0][0] == 1)
				blue++;
			else
				white++;
			return;
		}
		
		int[][] tmap = new int[map.length/2][map.length/2];
		int x = 0, y = 0;
		for(int i=0; i<map.length/2; i++) {
			for(int j=0; j<map.length/2; j++) {
				tmap[i][j] = map[i][j]; 
			}
		}
		dfs(tmap);
		for(int i=0; i<map.length/2; i++) {
			for(int j=0; j<map.length/2; j++) {
				tmap[i][j] = map[i][map.length/2+j];
			}
		}
		dfs(tmap);
		for(int i=0; i<map.length/2; i++) {
			for(int j=0; j<map.length/2; j++) {
				tmap[i][j] = map[map.length/2+i][j];
			}
		}
		dfs(tmap);
		for(int i=0; i<map.length/2; i++) {
			for(int j=0; j<map.length/2; j++) {
				tmap[i][j] = map[map.length/2+i][map.length/2+j];
			}
		}
		dfs(tmap);
	}
	
	static boolean isOk(int[][] map) {
		int cnt = 0;
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map.length; j++) {
				if(map[i][j] == 1)
					cnt++;
			}
		}
		if(cnt == map.length * map.length || cnt == 0)
			return true;

		return false;
	}
}
