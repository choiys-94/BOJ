package boj;

import java.util.Scanner;

public class BOJ_15904_UCPC는무엇의약자일까 {
	static final String ucpc = "UCPC";
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String in = sc.nextLine();
		int idx = 0;
		boolean flag = false;
		for(int i=0; i<in.length(); i++) {
			if(in.charAt(i) == ucpc.charAt(idx)) {
				idx++;
			}
			if(idx == 4) {
				flag = true;
				break;
			}
		}
		if(flag)
			System.out.println("I love UCPC");
		else
			System.out.println("I hate UCPC");
	}
}
