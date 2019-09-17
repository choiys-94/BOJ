package boj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2468_안전영역 {
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int[][] arr = new int[N][N];
		visited = new boolean[N][N];
		int min_height = Integer.MAX_VALUE;
		int max_height = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				arr[i][j] = sc.nextInt();
				max_height = Math.max(max_height, arr[i][j]);
			}
		}
		
		for(int i=0; i<max_height; i++) {
			int[][] tmp = new int[N][N];
			for(int j=0; j<N; j++) {
				System.arraycopy(arr[j], 0, tmp[j], 0, arr[j].length);
				Arrays.fill(visited[j], false);
			}
			result = 0;
			rainfall(tmp, i);
		}
		if(MAX == Integer.MIN_VALUE) {
			System.out.println(1);
		}
		else {
			System.out.println(MAX);
		}
	}
	
	static class Coord{
		int x;
		int y;
		Coord(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int MAX = Integer.MIN_VALUE;
	static int result = 0;
	
	static void bfs(int[][] arr, Coord st) {
		Queue<Coord> queue = new LinkedList<>();
		queue.add(st);
		while(!queue.isEmpty()) {
			Coord q = queue.poll();
			for(int i=0; i<4; i++) {
				int nx = q.x + dx[i];
				int ny = q.y + dy[i];
				if(check(nx, ny) && visited[nx][ny] == false && arr[nx][ny] != -1) {
					visited[nx][ny] = true;
					queue.add(new Coord(nx, ny));
				}
			}
		}
		result++;
	}
	
	//높이(height)만큼 비 쏟아짐
	static void rainfall(int[][] arr, int height) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] <= height) {
					arr[i][j] = -1;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] != -1 && visited[i][j] == false) {
					visited[i][j] = true;
					bfs(arr, new Coord(i, j));
				}
			}
		}
		
		MAX = Math.max(MAX, result);
	}
	
	static boolean check(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
}
