package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_7576_토마토 {
	static int N, M;
	static int cnt = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		int[][] arr = new int[N][M];
		ArrayList<Coord> s = new ArrayList<>();
		cnt = N*M;
		boolean flag = false;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == 1) {
					s.add(new Coord(i, j, 0));
				}
				else if(arr[i][j] == -1) {
					cnt--;
				}
				if(arr[i][j] == 0) {
					flag = true;
				}
			}
		}
		
		if(!flag) {
			System.out.println("0");
			System.exit(0);
		}
		solve(arr, s);
		
		if(cnt != 0) {
			System.out.println("-1");
		}
		else {
			System.out.println(result);
		}
	}
	
	static class Coord{
		int x;
		int y;
		int day = 0;
		Coord(int x, int y, int day){
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static int result = Integer.MAX_VALUE;
	
	static void solve(int[][] arr, ArrayList<Coord> s) {
		Queue<Coord> queue = new LinkedList<Coord>();	
		for(Coord c: s) {
			Coord tomato = new Coord(c.x, c.y, 0);
			queue.add(tomato);
			arr[tomato.x][tomato.y] = -1;
			cnt--;
		}
	
		while(!queue.isEmpty()) {
			Coord v = queue.poll();
			for(int i=0; i<4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];
				if(check(nx, ny) && arr[nx][ny] == 0) {
					arr[nx][ny] = -1;
					queue.add(new Coord(nx, ny, v.day+1));
					cnt--;
					if(cnt <= 0) {
						result = Math.min(v.day+1, result);
					}
				}
			}
		}
	}
	
	static boolean check(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}
}
