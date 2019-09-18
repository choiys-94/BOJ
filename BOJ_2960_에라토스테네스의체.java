package boj;

import java.util.Scanner;

public class BOJ_2960_에라토스테네스의체 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean[] visited = new boolean[1001];
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int result = 0;
		
		out:for(int i=2; i<=N; i++) {
			if(visited[i] == false) {
				K--;
				visited[i] = true;
				if(K == 0) {
					result = i;
					break out;
				}
				for(int j=i*2; j<=N; j+=i) {
					if(visited[j] == false) {
						K--;
						visited[j] = true;
						if(K == 0) {
							result = j;
							break out;
						}
					}
				}
			}
		}
		System.out.println(result);
	}
}
