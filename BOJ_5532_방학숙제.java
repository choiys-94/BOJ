package boj;

import java.util.Scanner;

public class BOJ_5532_방학숙제 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int L = sc.nextInt();
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		int D = sc.nextInt();
		
		int r1 = 0;
		int r2 = 0;
		if(A % C == 0)
			r1 = A / C;
		else
			r1 = A / C + 1;
		
		if(B % D == 0)
			r2 = B / D;
		else
			r2 = B / D + 1;
		
		System.out.println(L - Math.max(r1, r2));
	}
}
