package boj;

import java.util.Scanner;

public class BOJ_12790_Mini_Fantasy_War {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		for(int i=0; i<N; i++) {
			int oa = sc.nextInt();
			int ob = sc.nextInt();
			int oc = sc.nextInt();
			int od = sc.nextInt();
			oa += sc.nextInt();
			ob += sc.nextInt();
			oc += sc.nextInt();
			od += sc.nextInt();
			
			if(oa < 1) oa = 1;
			if(ob < 1) ob = 1;
			if(oc < 0) oc = 0;
			
			int sum = oa + 5*ob + 2*oc + 2*od;
			System.out.println(sum);
		}
	}
}
