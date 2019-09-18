package boj;

import java.util.Scanner;

public class BOJ_10872_팩토리얼 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		System.out.println(fact(N));
	}
	
	static int fact(int N) {
		if(N == 0)
			return 1;
		else if(N == 1)
			return 1;

		return N*fact(N-1);
	}
}
