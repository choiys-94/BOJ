package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _BOJ_2602_돌다리건너기 {
	static class Pos{
		int type;
		int cnt;
		int idx;
		Pos(int type, int cnt, int idx){
			this.type = type;
			this.cnt = cnt;
			this.idx = idx;
		}
	}
	static ArrayList<Character> seq;
	static ArrayList<Character>[] bridge;
	static int N, M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		seq = new ArrayList<>();
		bridge = new ArrayList[2];
		bridge[0] = new ArrayList<>();
		bridge[1] = new ArrayList<>();
		String in = sc.next();
		for(int i=0; i<in.length(); i++)
			seq.add(in.charAt(i));
		for(int i=0; i<2; i++) {
			in = sc.next();
			for(int j=0; j<in.length(); j++) {
				bridge[i].add(in.charAt(j));
			}
		}
		
		int ans = 0;
		
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(0, 0, 0));
		queue.add(new Pos(1, 0, 0));
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size > 0) {
				size--;
				Pos q = queue.poll();
				if(q.cnt == seq.size()) {
					ans++;
					continue;
				}
				if(q.idx > bridge[0].size()) {
					continue;
				}
				for(int i=q.idx; i<bridge[0].size(); i++) {
					if(seq.get(q.cnt) == bridge[q.type].get(i)) {
						int type = (q.type == 1) ? 0 : 1;
						queue.add(new Pos(type, q.cnt+1, i+1));
						continue;
					}
				}
			}
		}
		System.out.println(ans);
	}
}
