package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_16236_아기상어 {
	static class Fish implements Comparable<Fish>{
		int x;
		int y;
		int size;
		int eat;
		int dist;
		Fish(int x, int y, int size){
			this.x = x;
			this.y = y;
			this.size = size;
		}
		@Override
		public int compareTo(Fish o) {
			// TODO Auto-generated method stub
			if(this.dist == o.dist) {
				if(this.x == o.x) {
					return this.y - o.y;
				}
				return this.x - o.x;
			}
			return this.dist - o.dist;
		}
	}
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int[][] map = new int[N][N];
		Fish shark = null;
	
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 9) {
					shark = new Fish(i, j, 2);
					map[i][j] = 0;
				}
			}
		}
		solve(map, shark);
		if(result == Integer.MAX_VALUE) {
			System.out.println(0);
		}
		else {
			System.out.println(result);
		}
	}
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int result = Integer.MAX_VALUE;
	static void solve(int[][] map, Fish shark) {
		Queue<Fish> queue = new LinkedList<>();
		Queue<Fish> search = new LinkedList<>();
		shark.dist = 0;
		search.add(shark);
		
		while(!search.isEmpty()) {
			boolean[][] visited = new boolean[N][N];
			Fish s = search.poll();
			queue.add(s);
			PriorityQueue<Fish> tmpEat = new PriorityQueue<>();
			visited[shark.x][shark.y] = true; 
			while(!queue.isEmpty()) {
				Fish q = queue.poll();
				for(int i=0; i<4; i++) {
					int nx = q.x + dx[i];
					int ny = q.y + dy[i];
					if(check(nx, ny) && visited[nx][ny] == false) {
						if(map[nx][ny] <= s.size) {
							visited[nx][ny] = true;
							Fish tmpFish = new Fish(nx, ny, map[nx][ny]);
							tmpFish.dist = q.dist+1;
							queue.add(tmpFish);
							if(map[nx][ny] != 0 && map[nx][ny] < s.size) {
								tmpEat.add(tmpFish);
							}
						}
					}
				}
			}
			
			if(!tmpEat.isEmpty()) {
				// 가장 가까운 먹을 물고기 찾았음
				Fish eatFish = tmpEat.poll();
				s.eat++;
				if(s.eat == s.size) {
					s.size++;
					s.eat = 0;
				}
				s.x = eatFish.x;
				s.y = eatFish.y;
				s.dist = eatFish.dist;
				search.add(s);
				tmpEat.clear();
				map[s.x][s.y] = 0; 
			}
			else {
				if(s.dist != 0) {
					result = Math.min(result, s.dist);
				}
				break;
			}
		}
	}
	static boolean check(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
}
