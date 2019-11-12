package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class _BOJ_1043_거짓말 {
	static int N, M, K;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 사람 수
		M = sc.nextInt(); // 파티 수
		K = sc.nextInt(); // 진실 아는 사람 수
		ArrayList<Integer> p = new ArrayList<>();
		for(int i=0; i<K; i++) {
			p.add(sc.nextInt());
		}
		
		int cnt = 0;
		ArrayList<Integer>[] party = new ArrayList[M];
		for(int i=0; i<M; i++) {
			int L = sc.nextInt(); // 파티에 오는 사람 수
			party[i] = new ArrayList<>();
			for(int j=0; j<L; j++) {
				party[i].add(sc.nextInt());
			}
		}
		
		System.out.println(cnt);
	}
}
