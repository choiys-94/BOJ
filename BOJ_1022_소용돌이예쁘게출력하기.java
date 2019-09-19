package boj;

import java.util.Scanner;

public class BOJ_1022_소용돌이예쁘게출력하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] dx = {0, -1, 0, 1};
		int[] dy = {1, 0, -1, 0};
		
		int idx = 1;
		int dir = 0;
		int cnt = 1;
		int x = 5000, y = 5000;
		int r1 = sc.nextInt()+5000;
		int c1 = sc.nextInt()+5000;
		int r2 = sc.nextInt()+5000;
		int c2 = sc.nextInt()+5000;
		int size_x = -r1 + r2;
		int size_y = -c1 + c2;
		int[][] map = new int[size_x+1][size_y+1];
		
		while(idx < 100050000) {
			for(int j=0; j<2; j++) {
				for(int i=0; i<cnt; i++) {
					idx++;
					x += dx[dir];
					y += dy[dir];
					if(x >= r1 && y >= c1 && x <= r2 && y <= c2) {
						map[x-r1][y-c1] = idx;
					}
				}
				dir = (dir + 1) % 4;
			}
			cnt++;
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=0; i<=size_x; i++) {
			for(int j=0; j<=size_y; j++) {
				if(map[i][j] == 0) map[i][j] = 1;
				max = Math.max(max, Integer.toString(map[i][j]).length());
			}
		}
		
		for(int i=0; i<=size_x; i++) {
			for(int j=0; j<=size_y; j++) {
				switch(max) {
				case 1:
					System.out.printf("%d ", map[i][j]);
					break;
				case 2:
					System.out.printf("%2d ", map[i][j]);
					break;					
				case 3:
					System.out.printf("%3d ", map[i][j]);
					break;
				case 4:
					System.out.printf("%4d ", map[i][j]);
					break;
				case 5:
					System.out.printf("%5d ", map[i][j]);
					break;
				case 6:
					System.out.printf("%6d ", map[i][j]);
					break;
				case 7:
					System.out.printf("%7d ", map[i][j]);
					break;
				case 8:
					System.out.printf("%8d ", map[i][j]);
					break;
				case 9:
					System.out.printf("%9d ", map[i][j]);
					break;
				case 10:
					System.out.printf("%10d ", map[i][j]);
					break;
				}
			}
			System.out.println();
		}
	}
}
