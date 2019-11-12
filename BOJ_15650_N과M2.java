package boj;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_15650_N과M2 {
	static class Num implements Comparable<Num>{
		int[] a = new int[M];
		Num(int[] a){
			for(int i=0; i<M; i++) {
				this.a[i] = a[i];
			}
		}
		@Override
		public int compareTo(Num o) {
			for(int i=0; i<M; i++) {
				if(this.a[i] == o.a[i])
					continue;
				return this.a[i] - o.a[i];
			}
			return this.a[M-1] - o.a[M-1];
		}
	}
	static int N, M;
	static PriorityQueue<Num> ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		ans = new PriorityQueue<>();
		comb(new int[M], 1, 0);
		while(!ans.isEmpty()) {
			Num a = ans.poll();
			for(int i=0; i<M; i++) {
				System.out.print(a.a[i] + " ");
			}
			System.out.println();
		}
	}
	
	static void comb(int[] sel, int idx, int k) {
		if(k == sel.length) {
			ans.add(new Num(sel));
			return;
		}
		if(idx == N+1) {
			return;
		}
		
		sel[k] = idx;
		comb(sel, idx+1, k+1);
		comb(sel, idx+1, k);
	}
}
