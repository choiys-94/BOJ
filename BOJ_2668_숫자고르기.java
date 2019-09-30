package boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class BOJ_2668_숫자고르기 {
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int[] adj = new int[N+1];
		for(int i=1; i<=N; i++) {
			adj[i] = sc.nextInt();
		}
		
		tmp = new HashSet<>();
		HashSet<Integer> res = new HashSet<>();
		visited = new boolean[N+1];
		vis = new boolean[N+1];
		for(int i=1; i<=N; i++) {
			flag = false;
			dfs(adj, i, i, 0);
			if(flag) {
				res.addAll(tmp);
				System.arraycopy(visited, 0, vis, 0, visited.length);
			}
			else {
				System.arraycopy(vis, 0, visited, 0, vis.length);
				tmp.clear();
			}
		}
		System.out.println(res.size());
		ArrayList<Integer> rres = new ArrayList<>(res);
		Collections.sort(rres);
		for(int r: rres)
			System.out.println(r);
	}
	
	static HashSet<Integer> tmp;
	static boolean[] visited;
	static boolean[] vis;
	static boolean flag;
	static void dfs(int[] adj, int idx, int start, int cnt) {
		if(cnt != 0 && idx == start) {
			flag = true;
			return;
		}
		int dst = adj[idx];
		if(visited[dst] == false) {
			visited[dst] = true;
			tmp.add(dst);
			dfs(adj, dst, start, cnt+1);
		}
	}
}
