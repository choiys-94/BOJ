package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_16235_나무재테크 {
	static class Coord implements Comparable<Coord>{
		int x;
		int y;
		int age;
		Coord(int x, int y, int age){
			this.x = x;
			this.y = y;
			this.age = age;
		}
		@Override
		public int compareTo(Coord o) {
			// TODO Auto-generated method stub
			return this.age - o.age;
		}
	}
	static class Tree{
		ArrayList<Coord> t = new ArrayList<>();
	}
	static int N, M, K;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		int[][] map = new int[N+1][N+1];
		int[][] food = new int[N+1][N+1];
		Tree[][] tree = new Tree[N+1][N+1];
		for(int i=1; i<=N; i++) {
			tree[i] = new Tree[N+1];
			for(int j=1; j<=N; j++) {
				tree[i][j] = new Tree();
				map[i][j] = 5;
				food[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();	
			tree[a][b].t.add(new Coord(a, b, c));
		}
		
		for(int i=0; i<K; i++) {
			dead = new ArrayList<>();
			spring(map, tree);
//			for(int j=1; j<=N; j++) {
//				System.out.println(Arrays.toString(map[j]));
//			}
//			System.out.println();
			summer(map, tree);
			fall(map, tree);
			if(i == K-1) {
				winter(map, tree, food, true);
			}
			else {
				winter(map, tree, food, false);
			}
		}

		System.out.println(result);
	}
	
	static ArrayList<Coord> dead;
	static int result = 0;
	
	static void spring(int[][] map, Tree[][] tree) {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				for(int k=0; k<tree[i][j].t.size(); k++) {
					Coord c = tree[i][j].t.get(k);
					if(c.age <= map[i][j]) {
						map[i][j] -= c.age;
						c.age++;
					}
					else {
						for(int l=tree[i][j].t.size()-1; l>=k; l--) {
							Coord d = tree[i][j].t.remove(l);
							d.x = i;
							d.y = j;
							dead.add(d);
						}
					}
				}
			}
		}
	}
	
	static void summer(int[][] map, Tree[][] tree) {
		for(Coord d: dead) {
			map[d.x][d.y] += d.age/2;
		}
	}
	
	static int[] dx = {0, 1, 0, -1, 1, -1, 1, -1};
	static int[] dy = {1, 0, -1, 0, 1, -1, -1, 1};
	
	static void fall(int[][] map, Tree[][] tree) {
		ArrayList<Coord> toAdd = new ArrayList<>();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				toAdd.clear();
				for(int l=0; l<tree[i][j].t.size(); l++) {
					Coord t = tree[i][j].t.get(l);
					if(t.age % 5 == 0) {
						for(int k=0; k<8; k++) {
							int nx = t.x + dx[k];
							int ny = t.y + dy[k];
							if(check(nx, ny)) {
								Coord a = new Coord(nx, ny, 1);
								tree[nx][ny].t.add(0, a);
							}
						}
					}
				}
			}
		}
	}
	
	static void winter(int[][] map, Tree[][] tree, int[][] food, boolean isLast) {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] += food[i][j];
				if(isLast) {
					result += tree[i][j].t.size();
				}
			}
		}
	}
	
	static boolean check(int x, int y) {
		return x>=1 && y>=1 && x<=N && y<=N;
	}
}
