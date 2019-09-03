package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17070_파이프옮기기1 {
	static int N;
	static class Coord{
		int x;
		int y;
		int dir;
		int weight = 1;
		Coord(int x, int y, int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 1, 0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int[][] map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		boolean[][] visited = new boolean[N+1][N+1];
		visited[1][2] = true;
		dfs(map, visited, 1, 2, 0);
		System.out.println(result);
	}
	
	static int result = 0;
	
	static void dfs(int[][] map, boolean[][] visited, int x, int y, int dir) {
		if(x == N && y == N) {
			result++;
			return;
		}
		
		if(dir == 0) {
			for(int i=0; i<2; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(i==1 && check(nx, ny) && visited[nx][ny] == false && map[nx-1][ny] != 1 && map[nx][ny-1] != 1 && map[nx][ny] != 1) {
					visited[nx][ny] = true;
					dfs(map, visited, nx, ny, i);
					visited[nx][ny] = false;
				}
				else if(i!=1 && check(nx, ny) && visited[nx][ny] == false && map[nx][ny] != 1) {
					visited[nx][ny] = true;
					dfs(map, visited, nx, ny, i);
					visited[nx][ny] = false;
				}
			}
		}
		else if(dir == 1) {
			for(int i=0; i<3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(i==1 && check(nx, ny) && visited[nx][ny] == false && map[nx-1][ny] != 1 && map[nx][ny-1] != 1 && map[nx][ny] != 1) {
					visited[nx][ny] = true;
					dfs(map, visited, nx, ny, i);
					visited[nx][ny] = false;
				}
				else if(i!=1 && check(nx, ny) && visited[nx][ny] == false && map[nx][ny] != 1) {
					visited[nx][ny] = true;
					dfs(map, visited, nx, ny, i);
					visited[nx][ny] = false;
				}
			}
		}
		
		else if(dir == 2) {
			for(int i=1; i<3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(i==1 && check(nx, ny) && visited[nx][ny] == false && map[nx-1][ny] != 1 && map[nx][ny-1] != 1 && map[nx][ny] != 1) {
					visited[nx][ny] = true;
					dfs(map, visited, nx, ny, i);
					visited[nx][ny] = false;
				}
				else if(i!=1 && check(nx, ny) && visited[nx][ny] == false && map[nx][ny] != 1) {
					visited[nx][ny] = true;
					dfs(map, visited, nx, ny, i);
					visited[nx][ny] = false;
				}
			}			
		}
	}
	
	static boolean check(int x, int y) {
		return x>=1 && y>=1 && x<=N && y<=N;
	}
}
