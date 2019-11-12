package boj;

import java.util.HashSet;
import java.util.Scanner;

public class BOJ_14954_HappyNumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		HashSet<Long> hs = new HashSet<>();
		String N = sc.next();
		long prev = -1;
		boolean flag = true;
		while(true) {
			hs.add(Long.parseLong(N));
			if(prev == hs.size()) {
				flag = false;
				break;
			}
			prev = hs.size();
			long tmp = 0;
			for(int i=0; i<N.length(); i++) {
				tmp += (long)Math.pow(N.charAt(i)-'0', 2);
			}
			if(tmp == 1)
				break;
			N = tmp+"";
		}
		
		if(flag)
			System.out.println("HAPPY");
		else
			System.out.println("UNHAPPY");
	}
}
