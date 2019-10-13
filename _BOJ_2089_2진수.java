package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class _BOJ_2089_2진수 {
	static long N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextLong();
		ArrayList<Long> arr = new ArrayList<>();
		long token = 1;
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=33; i++) {
			
			if(i % 2 == 0) {
				arr.add((-1)*token);
			}
			else {
				arr.add(token);
			}
			
			if(Math.abs(token) > Math.abs(N)) {
				if(N >= 0)
					arr.add(token << 1);
				if(N < 0)
					arr.add((-1)*(token << 1));
				break;
			}
			token <<= 1;
		}
		
		sb = new StringBuilder();
//		for(long a: arr)
//			System.out.println(a);
		dfs(arr, 0, 0, new boolean[arr.size()]);
		System.out.println(sb.toString());
	}
	
	static void dfs(ArrayList<Long> arr, int idx, long sum, boolean[] visited) {
		if(sum == N) {
			StringBuilder sb = new StringBuilder();
			for(int i=visited.length-1; i>=0; i--) {
				if(visited[i])
					sb.append(1);
				else if(sb.length() != 0)
					sb.append(0);
			}
			System.out.println(sb.toString());
			System.exit(0);
		}
		for(int i=idx; i<arr.size(); i++) {
			if(visited[i])
				continue;
			visited[i] = true;
			dfs(arr, i, sum + arr.get(i), visited);
			visited[i] = false;
		}
	}
}
