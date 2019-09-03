package boj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_16985_Maaaaaaaaaze {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[5][5][5];
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				for(int k=0; k<5; k++) {
					map[i][j][k] = sc.nextInt();
				}
			}
		}
		
		floor_rotate(new int[] {0, 1, 2, 3, 4}, new int[5], 0, new boolean[5]);
		
		
		
		if(MIN == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(MIN);
		}
	}
	
	static void floor_rotate(int[] arr, int[] sel, int idx, boolean[] visited) {
		if(idx == sel.length) {
			int[][][] tmp = new int[5][5][5];
			for(int i=0; i<sel.length; i++) {
				for(int j=0; j<5; j++) {
					System.arraycopy(map[sel[i]][j], 0, tmp[i][j], 0, map[sel[i]][j].length);
				}
			}
			comb_re(tmp, new int[] {0, 1, 2, 3}, new int[5], 0, 0);
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			sel[idx] = arr[i];
			floor_rotate(arr, sel, idx+1, visited);
			visited[i] = false;
		}
	}
	
	static class Coord{
		int x;
		int y;
		int z;
		int cnt;
		Coord(int x, int y, int z, int cnt){
			this.x = x;
			this.y = y;
			this.z = z;
			this.cnt = cnt;
		}
	}
	
	static int dx[] = {0, 1, 0, -1, 0, 0};
	static int dy[] = {1, 0, -1, 0, 0, 0};
	static int dz[] = {0, 0, 0, 0, -1, 1};
	static int[][][] map;
	static int MIN = Integer.MAX_VALUE;
	
	static void bfs(int[][][] arr) {
		Queue<Coord> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[5][5][5];
		queue.add(new Coord(0, 0, 0, 0));
		visited[0][0][0] = true;
		while(!queue.isEmpty()) {
			Coord q = queue.poll();
			if(q.x == 4 && q.y == 4 && q.z == 4) {
				MIN = Math.min(MIN, q.cnt);
				continue;
			}
			for(int i=0; i<6; i++) {
				int nx = q.x + dx[i];
				int ny = q.y + dy[i];
				int nz = q.z + dz[i];
				if(check(nx, ny, nz) && visited[nx][ny][nz] == false && arr[nx][ny][nz] == 1) {
					visited[nx][ny][nz] = true;
					queue.add(new Coord(nx, ny, nz, q.cnt+1));
				}
			}
		}
	}
	
	static boolean check(int x, int y, int z) {
		return x>=0 && y>=0 && z>=0 && x<5 && y<5 && z<5;
	}
	
	static void comb_re(int[][][] map2, int[] arr, int[] sel, int n, int r) {
		if(r == sel.length) {
			int[][][] tmp = new int[5][5][5];
			
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					System.arraycopy(map2[i][j], 0, tmp[i][j], 0, map2[i][j].length);
				}
			}
			
			for(int i=0; i<5; i++) {
				rotate(tmp, i, sel[i]);
			}
			
			if(tmp[0][0][0] == 0 || tmp[4][4][4] == 0) {
				return;
			}
			bfs(tmp);
			return;
		}
		
		if(n == arr.length) {
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			sel[r] = arr[i];
			comb_re(map2, arr, sel, i, r+1);
		}
	}
	
	static void rotate(int[][][] arr, int idx, int num) {
		int[][] tmp = new int[5][5];

		for(int k=0; k<num; k++) {
			for(int i=0; i<5; i++) {
				System.arraycopy(arr[idx][i], 0, tmp[i], 0, arr[idx][i].length);
			}
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					arr[idx][i][j] = tmp[4-j][i];
				}
			}
		}
	}
}
