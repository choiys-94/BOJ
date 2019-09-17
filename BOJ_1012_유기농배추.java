package algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1012_유기농배추 {
	static int N, M;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int result = 0;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();

		for (int T = 1; T <= tc; T++) {
			N = sc.nextInt();
			M = sc.nextInt();
			int K = sc.nextInt();
			int[][] arr = new int[N][M];
			for(int i=0; i<K; i++) {
				int tx = sc.nextInt();
				int ty = sc.nextInt();
				arr[tx][ty] = 1;
			}
			
			
			boolean[][] visited = new boolean[N][M];
			
			result = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(arr[i][j] == 1 && visited[i][j] == false) {
						result++;
						visited[i][j] = true;
//						solve(arr, visited, i, j);
						bfs(arr, i, j);
					}
				}
			}
			
			System.out.println(result);
		}
	}
	
	static class Cabbage{
		int r;
		int c;
		Cabbage(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static void bfs(int[][] map, int r, int c) {
		Cabbage cabbage = new Cabbage(r, c);
		Queue<Cabbage> queue = new LinkedList<>();
		
		queue.add(cabbage);
		map[cabbage.r][cabbage.c] = 0; 
		
		while(!queue.isEmpty()) {
			Cabbage cab = queue.poll();
			for(int i=0; i<4; i++) {
				int nr = cab.r + dx[i];
				int nc = cab.c + dy[i];
				if(check(nr, nc) && map[nr][nc] == 1) {
					map[nr][nc] = 0;
					queue.add(new Cabbage(nr, nc));
				}
			}
		}
	}
	
	static void solve(int[][] arr, boolean[][] visited, int x, int y) {
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(check(nx, ny) && arr[nx][ny] == 1 && visited[nx][ny] == false) {
				visited[nx][ny] = true;
				solve(arr, visited, nx, ny);
			}
		}
	}
	
	static boolean check(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}
}
