package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class _BOJ_1722_순열의순서 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();

		int type = sc.nextInt();
		
		boolean[] visited = new boolean[N+1];
		long[] fact = new long[N+1];
		fact[1] = 1;
		for(int i=2; i<=N; i++) {
			fact[i] = fact[i-1]*i;
		}
		
		if(type == 1) {
			long in = sc.nextLong();
			for(int i=1; i<=N; i++) {
				int idx = 1;
				if(in - fact[N-i] > 0) {
					while(visited[idx] == true) {
						idx++;
					}
					while(in - fact[N-i] > 0) {
						in -= fact[N-i];
						idx++;
					}
					visited[idx] = true;
					System.out.print(idx + " ");
				}
				else {
					while(visited[idx] == true) {
						idx++;
					}
					visited[idx] = true;
					System.out.print(idx + " ");
				}
			}
		}
		else {
			int sum = 0;
			for(int i=1; i<=N; i++) {
				int in = sc.nextInt();
				int idx = 1;
				while(in != i) {
					if(visited[idx] == false) {
						sum += fact[N-i];
						visited[idx] = true;
						break;
					}
					idx++;
				}
			}
			if(sum == 0)
				System.out.println(1);
			else
				System.out.println(sum);
		}
	}
}
