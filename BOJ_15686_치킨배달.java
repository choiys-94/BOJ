package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_15686_치킨배달 {
	static class Pos{
		int r;
		int c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int N, M;
	static ArrayList<Pos> house;
	static ArrayList<Pos> chicken;
	static int cnt = 0;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		house = new ArrayList<>();
		chicken = new ArrayList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int in = sc.nextInt();
				if(in == 1)
					house.add(new Pos(i, j));
				else if(in == 2)
					chicken.add(new Pos(i, j));
			}
		}
		
		comb(new int[M], 0, 0);
		
		System.out.println(ans);
	}
	
	static void solve(int[] sel) {
		for(Pos h: house) {
			int tmp = Integer.MAX_VALUE;
			for(int i=0; i<sel.length; i++) {
				tmp = Math.min(tmp, distance(h, chicken.get(sel[i])));
			}
			cnt += tmp;
		}
	}
	
	static void comb(int[] sel, int idx, int k) {
		if(sel.length == k) {
			cnt = 0;
			solve(sel);
			ans = Math.min(ans, cnt);
			return;
		}
		if(chicken.size() == idx) {
			return;
		}
		
		sel[k] = idx;
		comb(sel, idx+1, k+1);
		comb(sel, idx+1, k);
	}
	
	static int distance(Pos a, Pos b) {
		return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
	}
}
