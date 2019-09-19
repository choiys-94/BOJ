package boj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class BOJ_5567_결혼식 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		ArrayList<Integer> f = new ArrayList<>();
		ArrayList<Integer> ff = new ArrayList<>();
		int[] ina = new int[M];
		int[] inb = new int[M];
		for(int i=0; i<M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			ina[i] = a;
			inb[i] = b;
			if(a == 1) {
				f.add(b);
			}
		}
		
		for(int i=0; i<M; i++) {
			if(f.contains(ina[i]) && inb[i] != 1) {
				ff.add(inb[i]);
			}
			if(f.contains(inb[i])  && ina[i] != 1) {
				ff.add(ina[i]);
			}
		}
		
		HashSet<Integer> hs = new HashSet<>();
		for(Integer i: f)
			hs.add(i);
		for(Integer i: ff)
			hs.add(i);
		
		System.out.println(hs.size());
	}
}
