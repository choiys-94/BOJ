package boj;

import java.util.Scanner;

public class BOJ_16283_farm {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt();
		int b = sc.nextInt();
		int n = sc.nextInt();
		int w = sc.nextInt();
		int sheep = 0;
		int goat = 0;
		boolean flag = false;
		
		for(int i=1; i<=1000; i++) {
			for(int j=1; j<=1000; j++) {
				if(i + j == n && (i*a + j*b) == w) {
					if(flag) {
						System.out.println(-1);
						System.exit(0);
					}
					flag = true;
					sheep = i;
					goat = j;
				}
			}
		}
		if(sheep == 0 && goat == 0)
			System.out.println(-1);
		else
			System.out.println(sheep + " " + goat);
	}
}
