package boj;

import java.util.Scanner;

public class BOJ_2935_소음 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String A = sc.next();
		char op = sc.next().charAt(0);
		String B = sc.next();
		
		StringBuilder sb = new StringBuilder();
		if(op == '*') {
			sb.append("1");
			for(int i=0; i<A.length()-1 + B.length()-1; i++)
				sb.append("0");
		}
		else {
			if(A.equals(B)) {
				sb.append("2");
				for(int i=0; i<A.length()-1; i++)
					sb.append("0");
			}
			else if(A.length() > B.length()) {
				sb.append("1");
				for(int i=0; i<A.length()-B.length()-1; i++) {
					sb.append("0");
				}
				sb.append("1");
				for(int i=0; i<B.length()-1; i++) {
					sb.append("0");
				}
			}
			else if(A.length() < B.length()) {
				sb.append("1");
				for(int i=0; i<B.length()-A.length()-1; i++) {
					sb.append("0");
				}
				sb.append("1");
				for(int i=0; i<A.length()-1; i++) {
					sb.append("0");
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}
