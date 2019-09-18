package boj;

import java.util.Scanner;

public class BOJ_2563_색종이 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[][] map = new int[101][101];
		
		for(int i=0; i<N; i++) {
			int y = sc.nextInt();
			int x = sc.nextInt();
			for(int r=0; r<10; r++) {
				for(int c=0; c<10; c++) {
					map[x+r][y+c] = 1;
				}
			}
		}
		
		int result = 0;
		
		for(int i=1; i<=100; i++) {
			for(int j=1; j<=100 ;j++) {
				if(map[i][j] == 1) {
					result++;
				}
			}
		}
		
		System.out.println(result);
	}
}
