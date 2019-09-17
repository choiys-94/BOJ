package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_16988_Baaaaaaaaaduk2_Easy {
	static int N, M;
	static class Coord{
		int x;
		int y;
		Coord(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		int[][] map = new int[N][M];
		ArrayList<Coord> arr = new ArrayList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 0) {
					arr.add(new Coord(i, j));
				}
			}
		}
		
		comb(map, arr, new Coord[2], 0, 0);
		
		System.out.println(MAX);
	}
	
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	static boolean[][] visited;
	static int cnt;
	static int MAX = Integer.MIN_VALUE;
	
	static void solve(int[][] map) {
		int tCnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				tCnt = 0;
				if(map[i][j] == 2 && visited[i][j] == false) {
					visited[i][j] = true;
					tCnt++;
					Queue<Coord> queue = new LinkedList<>();
					queue.add(new Coord(i, j));
					boolean flag = false;
					while(!queue.isEmpty()) {
						Coord q = queue.poll();
						for(int k=0; k<4; k++) {
							int nx = q.x + dx[k];
							int ny = q.y + dy[k];
							if(check(nx, ny) && map[nx][ny] == 0) {
								tCnt = 0;
								flag = true;
							}
							else if(check(nx, ny) && map[nx][ny] == 2 && visited[nx][ny] == false) {
								if(!flag)
									tCnt++;
								visited[nx][ny] = true;
								queue.add(new Coord(nx, ny));
							}
						}
					}
				}
				cnt += tCnt;
			}
		}
	}
	
	static void comb(int[][] map, ArrayList<Coord> arr, Coord[] sel, int n, int r) {
		if(r == sel.length) {
			int[][] tmp = new int[N][M];
			visited = new boolean[N][M];
			for(int i=0; i<N; i++) {
				System.arraycopy(map[i], 0, tmp[i], 0, map[i].length);
			}
			for(int i=0; i<sel.length; i++) {
				tmp[sel[i].x][sel[i].y] = 1; 
			}
			cnt = 0;
			solve(tmp);
			MAX = Math.max(MAX, cnt);
			return;
		}
		
		if(n == arr.size()) {
			return;
		}
		
		sel[r] = arr.get(n);
		comb(map, arr, sel, n+1, r+1);
		comb(map, arr, sel, n+1, r);
	}
	
	static boolean check(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}
}
