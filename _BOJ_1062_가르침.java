package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class _BOJ_1062_가르침 {
	static int N, K;
	static String set = "bdefghjklmnopqsuvwxyz";
	static String[] in;
	static ArrayList<Character> letter;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt()-5;
		
		in = new String[N];
		letter = new ArrayList<>(Arrays.asList(new Character[] {'a','r','t','i','c'}));
		for(int i=0; i<N; i++) {
			String tmp = sc.next();
			tmp = tmp.substring(4, tmp.length()-4);
			in[i] = tmp;
		}
		
		if(K < 0) {
			System.out.println(0);
			System.exit(0);
		}
		
		solve(new boolean[set.length()], 0);
		System.out.println(ans);
	}
	
	static void solve(boolean[] visited, int cnt) {
		if(cnt == K) {
			int sum = 0;
			for(int i=0; i<in.length; i++) {
				boolean flag = true;
				for(int j=0; j<in[i].length(); j++) {
					if(!letter.contains(in[i].charAt(j))) {
						flag = false;
						break;
					}
				}
				if(flag)
					sum++;
			}
			ans = Math.max(ans, sum);
			return;
		}
		
		for(int i=0; i<set.length(); i++) {
			if(visited[i])
				continue;
			letter.add(set.charAt(i));
			visited[i] = true;
			solve(visited, cnt+1);
			visited[i] = false;
			letter.remove(letter.size()-1);
		}
	}
}
