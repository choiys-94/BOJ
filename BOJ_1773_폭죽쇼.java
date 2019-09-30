package boj;

import java.util.Scanner;

public class BOJ_1773_폭죽쇼 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int C = sc.nextInt();
		int[] p = new int[N];
		boolean[] res = new boolean[C+1];
		for(int i=0; i<N; i++) {
			p[i] = sc.nextInt();
			for(int j=p[i]; j<=C; j+=p[i]) {
				res[j] = true;
			}
		}
		int cnt = 0;
		
		for(int i=0; i<=C; i++) {
			if(res[i])
				cnt++;
		}
		System.out.println(cnt);
	}
}