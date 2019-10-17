package boj;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_16235_나무재테크_2 {
	static class Tree implements Comparable<Tree>{
		int r;
		int c;
		int age;
		Tree(int r, int c, int age){
			this.r = r;
			this.c = c;
			this.age = age;
		}
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
	static int N, M, K;
	static int[][] sup;
	static int[][] map;
	static PriorityQueue<Tree> tree; // 현재 나무
	static ArrayList<Tree> toDel; // 죽을 나무
	static PriorityQueue<Tree> tmp; // 임시 나무
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 맵 크기
		M = sc.nextInt(); // 나무 개수
		K = sc.nextInt(); // 몇년?
		sup = new int[N+1][N+1];
		map = new int[N+1][N+1];
		tree = new PriorityQueue<>();
		toDel = new ArrayList<>();
		tmp = new PriorityQueue<>();
		// 양분 입력
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				sup[i][j] = sc.nextInt();
				map[i][j] = 5;
			}
		}
		// 나무 입력
		for(int i=0; i<M; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int age = sc.nextInt();
			tree.add(new Tree(r, c, age));
		}
		
		for(int i=0; i<K; i++) {
			spring();
			summer();
			fall();
			winter();
		}
		
		System.out.println(tree.size());
	}
	
	static void spring() {
		while(!tree.isEmpty()) {
			Tree q = tree.poll();
			if(map[q.r][q.c] >= q.age) {
				map[q.r][q.c] -= q.age;
				q.age++;
				tmp.add(q);
			}
			else
				toDel.add(q);
		}
		tree.clear();
		tree.addAll(tmp);
		tmp.clear();
	}
	
	static void summer() {
		for(Tree d: toDel)
			map[d.r][d.c] += d.age/2; 
		toDel.clear();
	}
	
	static void fall() {
		while(!tree.isEmpty()) {
			Tree q = tree.poll();
			if(q.age % 5 == 0) {
				for(int i=0; i<8; i++) {
					int nr = q.r + dr[i];
					int nc = q.c + dc[i];
					if(!check(nr, nc))
						continue;
					tmp.add(new Tree(nr, nc, 1));
				}
			}
			tmp.add(q);
		}
		tree.clear();
		tree.addAll(tmp);
		tmp.clear();
	}
	
	static void winter() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] += sup[i][j];
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r>=1 && c>=1 && r<=N && c<=N;
	}
	
	static int[] dr = {0, 1, 0, -1, 1, -1, 1, -1};
	static int[] dc = {1, 0, -1, 0, 1, 1, -1, -1};
}
