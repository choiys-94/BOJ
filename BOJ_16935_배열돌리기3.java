package boj;

import java.util.Scanner;

public class BOJ_16935_배열돌리기3 {
	static int N, M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		int R = sc.nextInt();
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int t;
		int[][] tmp;
		for(int i=0; i<R; i++) {
			switch(sc.nextInt()) {
			case 1:
				map = one(map);
				break;
			case 2:
				map = two(map);
				break;
			case 3:
				t = N;
				N = M;
				M = t;
				map = three(map);
				break;
			case 4:
				t = N;
				N = M;
				M = t;
				map = four(map);
				break;
			case 5:
				map = five(map);
				break;
			case 6:
				map = six(map);
				break;
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static int[][] one(int[][] map){
		int[][] tmp = new int[N][M];
		for(int i=N-1; i>=0; i--) {
			System.arraycopy(map[i], 0, tmp[N-1-i], 0, map[i].length);
		}
		return tmp;
	}
	
	static int[][] two(int[][] map){
		int[][] tmp = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=M-1; j>=0; j--) {
				tmp[i][j] = map[i][M-1-j];
			}
		}
		return tmp;
	}
	
	static int[][] three(int[][] map){
		int[][] tmp = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				tmp[i][j] = map[M-j-1][i];
			}
		}
		return tmp;
	}
	
	static int[][] four(int[][] map){
		int[][] tmp = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				tmp[i][j] = map[j][N-i-1];
			}
		}
		return tmp;
	}
	
	
	static int[][] five(int[][] map){
		int[][] tmp = new int[N][M];
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				tmp[i][j] = map[i+N/2][j];
			}
		}
		for(int i=0; i<N/2; i++) {
			for(int j=M/2; j<M; j++) {
				tmp[i][j] = map[i][j-M/2];
			}
		}
		for(int i=N/2; i<N; i++) {
			for(int j=0; j<M/2; j++) {
				tmp[i][j] = map[i][j+M/2];
			}
		}
		for(int i=N/2; i<N; i++) {
			for(int j=M/2; j<M; j++) {
				tmp[i][j] = map[i-N/2][j];
			}
		}
		return tmp;
	}
	
	static int[][] six(int[][] map){
		int[][] tmp = new int[N][M];
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				tmp[i][j] = map[i][j+M/2];
			}
		}
		for(int i=0; i<N/2; i++) {
			for(int j=M/2; j<M; j++) {
				tmp[i][j] = map[i+N/2][j];
			}
		}
		for(int i=N/2; i<N; i++) {
			for(int j=0; j<M/2; j++) {
				tmp[i][j] = map[i-N/2][j];
			}
		}
		for(int i=N/2; i<N; i++) {
			for(int j=M/2; j<M; j++) {
				tmp[i][j] = map[i][j-M/2];
			}
		}
		return tmp;
	}
}
