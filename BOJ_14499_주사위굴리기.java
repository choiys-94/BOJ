package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_14499_주사위굴리기 {
	static int N, M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(4, 1, 3, 6));
		ArrayList<Integer> b = new ArrayList<>(Arrays.asList(2, 1, 5, 6));
		int[] dice = new int[7];
		int[] dx = {0, 0, 0, -1, 1};
		int[] dy = {0, 1, -1, 0, 0};
		N = sc.nextInt();
		M = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int K = sc.nextInt();
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int nx = x;
		int ny = y;
		if(map[nx][ny] != 0) {
			dice[a.get(a.size()-1)] = map[nx][ny];
			map[nx][ny] = 0;
		}
		for(int i=0; i<K; i++) {
			int dir = sc.nextInt();
			nx += dx[dir];
			ny += dy[dir];
			if(check(nx, ny)) {
				rotate(a, b, dir);
				if(map[nx][ny] == 0) {
					map[nx][ny] = dice[a.get(a.size()-1)];
				}
				else {
					dice[a.get(a.size()-1)] = map[nx][ny];
					map[nx][ny] = 0;
				}
				System.out.println(dice[a.get(1)]);
			}
			else {
				nx -= dx[dir];
				ny -= dy[dir];
			}
		}
	}
	/*
	 * 오른쪽 : a 오른쪽 shift, b[1] 바꾸고 b[3] 바꿈
	 * 왼쪽 : a 왼쪽 shift, b[1] 바꾸고 b[3] 바꿈 
	 * 위 : b 왼쪽 shift, a[1] 바꾸고 a[3] 바꿈 
	 * 아래 : b 오른쪽 shift, a[1] 바꾸고 a[3] 바꿈
	 */
	static void rotate(ArrayList<Integer> a, ArrayList<Integer> b, int type) {
		int tmp = -1;
		switch(type) {
		case 1:
			tmp = a.get(a.size()-1);
			a.remove(a.size()-1);
			a.add(0, tmp);
			b.set(1, a.get(1));
			b.set(3, a.get(3));
			break;
		case 2:
			tmp = a.get(0);
			a.remove(0);
			a.add(tmp);
			b.set(1, a.get(1));
			b.set(3, a.get(3));
			break;
		case 3:
			tmp = b.get(0);
			b.remove(0);
			b.add(tmp);
			a.set(1, b.get(1));
			a.set(3, b.get(3));
			break;
		case 4:
			tmp = b.get(b.size()-1);
			b.remove(b.size()-1);
			b.add(0, tmp);
			a.set(1, b.get(1));
			a.set(3, b.get(3));
			break;
		}
	}
	
	static boolean check(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}
}
