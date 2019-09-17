package boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_3190_뱀 {
	static class Path{
		int x;
		int y;
		Path(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static class Snake implements Comparable<Snake>{
		int t;
		char d;
		Snake(int t, char d){
			this.t = t;
			this.d = d;
		}
		@Override
		public int compareTo(Snake o) {
			// TODO Auto-generated method stub
			return this.t - o.t;
		}
	}
	
	static int N, K, L;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int[][] map = new int[N+1][N+1];
		
		K = sc.nextInt();
		for(int i=0; i<K; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = 1;
		}
		
		L = sc.nextInt();
		ArrayList<Snake> snake = new ArrayList<>();
		for(int i=0; i<L; i++) {
			int t = sc.nextInt();
			char d = sc.next().charAt(0);
			snake.add(new Snake(t, d));
		}
		
		Collections.sort(snake);
		
		solve(map, snake);
	}
	
	static void solve(int[][] map, ArrayList<Snake> snake) {
		int len = 1;
		int dir = 0;
		int cur_time = 1;
		//우 하 좌 상
		int dx[] = {0, 1, 0, -1};
		int dy[] = {1, 0, -1, 0};
		
		int x = 1;
		int y = 1;
		map[x][y] = -1;
		
		Queue<Path> path = new LinkedList<>();
		path.add(new Path(x, y));
		while(true) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if(check(nx, ny)) {
				if(map[nx][ny] == -1) {
					System.out.println(cur_time);
					System.exit(0);
				}
				else if(map[nx][ny] == 0){
					Path tmp = path.poll();
					map[tmp.x][tmp.y] = 0; 
				}
				path.add(new Path(nx, ny));
				map[nx][ny] = -1;
				x = nx;
				y = ny;
			}
			else {
				System.out.println(cur_time);
				System.exit(0);
			}
			if(!snake.isEmpty()) {
				Snake s = snake.get(0);
				if(s.t == cur_time) {
					if(s.d == 'L') {
						dir--;
						if(dir < 0) {
							dir = 3;
						}
					}
					else if(s.d == 'D') {
						dir++;
						if(dir > 3) {
							dir = 0;
						}
					}
					snake.remove(0);
				}
			}

		cur_time++;
		}
	}
	
	static boolean check(int x, int y) {
		return x>=1 && y>=1 && x<N+1 && y<N+1;
	}
}
