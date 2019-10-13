package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9019_DSLR {
	static class DSLR{
		int num;
		String str = new String();
		DSLR(int num, String str, String ch){
			this.num = num;
			this.str = str + ch;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		Queue<DSLR> queue = new LinkedList<>();
		HashSet<Integer> tmp = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			queue.clear();
			queue.add(new DSLR(a, "", ""));
			tmp.clear();
			while(!queue.isEmpty()) {
				DSLR q = queue.poll();
				if(tmp.contains(q.num)) {
					continue;
				}
				tmp.add(q.num);
				if(q.num == b) {
					sb.append(q.str).append("\n");
					break;
				}
				queue.add(new DSLR(dFunc(q.num), q.str, "D"));
				queue.add(new DSLR(sFunc(q.num), q.str, "S"));
				queue.add(new DSLR(lFunc(q.num), q.str, "L"));
				queue.add(new DSLR(rFunc(q.num), q.str, "R"));
			}
		}
		System.out.println(sb.toString());
	}

	static int dFunc(int num) {
		return (num*2)%10000;
	}
	
	static int sFunc(int num) {
		return (num-1) < 0 ? 9999 : num-1;
	}
	
	static int lFunc(int num) {
		return (num*10)%10000 + num/1000;
	}
	
	static int rFunc(int num) {
		return (num/10) + (num%10)*1000;
	}
}
