package boj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class BOJ_2617_구슬찾기 {
	static int N, M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		llist = new HashSet[N+1];
		hlist = new HashSet[N+1];
		for(int i=0; i<=N; i++) {
			llist[i] = new HashSet<>();
			hlist[i] = new HashSet<>();
		}
		
		for(int i=0; i<M; i++) {
			int h = sc.nextInt();
			int l = sc.nextInt();
			if(h == l)
				continue;
			llist[l].add(h);
			hlist[h].add(l);
		}
		
		for(int i=1; i<=N; i++) {
			if(!llist[i].isEmpty()) {
				ArrayList<Integer> tmp = new ArrayList<>(llist[i]);
				int size = tmp.size();
				for(int j=0; j<size; j++) {
					ldfs(i, tmp.get(j), new boolean[N+1]);
				}
			}
			if(!hlist[i].isEmpty()) {
				ArrayList<Integer> tmp = new ArrayList<>(hlist[i]);
				int size = tmp.size();
				for(int j=0; j<size; j++) {
					hdfs(i, tmp.get(j), new boolean[N+1]);
				}
			}
		}
		
		int result = 0;
		
		for(int i=1; i<=N; i++) {
			if(!llist[i].isEmpty()) {
				if(llist[i].size() >= (N+1)/2) result++;
			}
			if(!hlist[i].isEmpty()) {
				if(hlist[i].size() >= (N+1)/2) result++;
			}
		}
		
		System.out.println(result);
	}
	
	static HashSet<Integer>[] llist;
	static HashSet<Integer>[] hlist;
	
	static void ldfs(int base, int idx, boolean[] visited) {
		if(llist[idx].isEmpty() || visited[idx])
			return;
		
		visited[base] = true;
		visited[idx] = true;
		llist[base].addAll(llist[idx]);
		for(int data: llist[idx]) {
			if(visited[data] == false) {
				ldfs(base, data, visited);
			}
		}
	}
	
	static void hdfs(int base, int idx, boolean[] visited) {
		if(hlist[idx].isEmpty() || visited[idx])
			return;
		
		visited[base] = true;
		visited[idx] = true;
		hlist[base].addAll(hlist[idx]);
		for(int data: hlist[idx]) {
			if(visited[data] == false) {
				hdfs(base, data, visited);
			}
		}
	}
}
