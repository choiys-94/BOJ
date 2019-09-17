package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17135_캐슬디펜스 {
	static class Coord implements Comparable<Coord>{
		int x;
		int y;
		int distance;
		Coord(int x, int y){
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Coord o) {
			// TODO Auto-generated method stub
			if(this.distance == o.distance) {
				return this.y - o.y;
			}
			return this.distance - o.distance;
		}
	}
	
	static int N, M, D;
	static int enemyCnt = 0;
	static ArrayList<Coord> del;
	static int result = 0;
	static int MAX = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();	// 행
		M = sc.nextInt();	// 열
		D = sc.nextInt();	// 공격 거리
		int[][] map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int[] arr = new int[M];
		for(int i=0; i<M; i++) {
			arr[i] = i+1;
		}
		
		MAX = Integer.MIN_VALUE;
		comb(map, arr, new int[3], 0, 0);
		
		System.out.println(MAX);
	}
	
	static void solve(int[][] map, int[] sel) {
		for(int s=0; s<N; s++) {
			del = new ArrayList<>();
			for(int i=0; i<sel.length; i++) {
				attack(map, new Coord(0,sel[i]));
			}
			
			if(!del.isEmpty()) {
				for(Coord d: del) {
					if(map[d.x][d.y] == 1) {
						map[d.x][d.y] = 0;
						result++;
					}
				}
			}
			down(map);
		}
	}
	
	static void comb(int[][] map, int[] arr, int[] sel, int n, int r) {
		if(r == sel.length) {
			result = 0;
			int[][] tmp = new int[N][M];
			for(int i=0; i<N; i++) {
				System.arraycopy(map[i], 0, tmp[i], 0, map[i].length);
			}
			solve(tmp, sel);
			MAX = Math.max(MAX, result);
			return;
		}
		
		if(n == arr.length) {
			return;
		}
		
		sel[r] = n;
		comb(map, arr, sel, n+1, r+1);
		sel[r] = n;
		comb(map, arr, sel, n+1, r);
	}
	
	static void attack(int[][] map, Coord arc) {
		PriorityQueue<Coord> toDel = new PriorityQueue<>();
		
		for(int i=N-1; i>=0; i--) {
			for(int j=0; j<M; j++) {
				int distance = Math.abs(arc.x - i + N) + Math.abs(arc.y - j);
				if(map[i][j] == 1 && distance <= D) {
					Coord enemy = new Coord(i, j);
					enemy.distance = distance;
					toDel.add(enemy);
				}
			}
		}
		if(!toDel.isEmpty()) {
			del.add(toDel.poll());
		}
	}
	
	static void down(int[][] map) {
		for(int i=0; i<M; i++) {
			for(int j=N-2; j>=0; j--) {
				map[j+1][i] = map[j][i];
			}
			map[0][i] = 0;
		}
	}
	
	static boolean check(int x, int y) {
		return x>=0 && y>=0 && x<N && x<M;
	}
}
