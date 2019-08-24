package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_2667_단지번호붙이기 {
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int N, cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			String tmp = sc.next();
			for(int j=0; j<N; j++) {
				arr[i][j] = tmp.charAt(j)-'0';
			}
		}
		
		cnt = 0;
		ArrayList<Integer> result = new ArrayList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] == 1 && visited[i][j] == false) {
					visited[i][j] = true;
					cnt++;
					solve(i, j);
					result.add(cnt);
					cnt = 0;
				}
			}
		}
		
		Collections.sort(result);
		System.out.println(result.size());
		for(Integer i: result) {
			System.out.println(i);
		}
	}
	
	static void solve(int x, int y) {
		int nx, ny;
		for(int i=0; i<4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if(check(nx, ny) && visited[nx][ny] == false && arr[nx][ny] == 1) {
				visited[nx][ny] = true;
				cnt++;
				solve(nx, ny);
			}
		}
	}
	
	static boolean check(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
}
