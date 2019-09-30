package boj;

import java.util.Scanner;

public class BOJ_2804_크로스워드만들기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String A = sc.next();
		String B = sc.next();
		int aidx = -1;
		int bidx = -1;
		out:for(int i=0; i<A.length(); i++) {
			for(int j=0; j<B.length(); j++) {
				if(A.charAt(i) == B.charAt(j)) {
					aidx = i;
					bidx = j;
					break out;
				}
			}
		}
		
		for(int i=0; i<B.length(); i++) {
			for(int j=0; j<A.length(); j++) {
				if(j == aidx)
					System.out.print(B.charAt(i));
				else if(i == bidx)
					System.out.print(A.charAt(j));
				else
					System.out.print(".");
			}
			System.out.println();
		}
	}
}
