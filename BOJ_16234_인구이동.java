package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_16234_인구이동 {
	static class Population{
		int x;
		int y;
		int num;
		Population(int x, int y, int num){
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
	static int N, L, R;
	static boolean visited[][];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();	// 맵 크기
		L = sc.nextInt();	// L 이상
		R = sc.nextInt();	// R 이하
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int cnt = 0;
		
		while(true) {
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j] == false) {
						solve(map, i, j);
					}
				}
			}
			if(flag) {
				break;
			}
			else {
				flag = true;
			}
			cnt++;
		}
		
		System.out.println(cnt);
	}
	
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	static boolean flag = true;
	
	static void solve(int[][] map, int x, int y) {
		Queue<Population> queue = new LinkedList<>();
		ArrayList<Population> union = new ArrayList<>();
		
		queue.add(new Population(x, y, map[x][y]));
		visited[x][y] = true;
		union.add(new Population(x, y, map[x][y]));
		while(!queue.isEmpty()) {
			Population q = queue.poll();
			for(int i=0; i<4; i++) {
				int nx = q.x + dx[i];
				int ny = q.y + dy[i];
				
				if(check(nx, ny) && visited[nx][ny] == false) {
					int sub = Math.abs(q.num - map[nx][ny]);
					if(sub >= L && sub <= R) {
						visited[nx][ny] = true;
						union.add(new Population(nx, ny, map[nx][ny]));
						queue.add(new Population(nx, ny, map[nx][ny]));
					}
				}
			}
		}
		if(union.size() > 1) {
			int sum = 0;
			for(Population p: union) {
				sum += p.num;
			}
			sum /= union.size();
			for(Population p: union) {
				map[p.x][p.y] = sum; 
			}
			flag = false;
		}
	}
	
	static boolean check(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
}
