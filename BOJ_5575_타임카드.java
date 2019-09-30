package boj;

import java.util.Scanner;

public class BOJ_5575_타임카드 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 3600 60 1
		for(int i=0; i<3; i++) {
			int h1 = sc.nextInt()*3600;
			int m1 = sc.nextInt()*60;
			int s1 = sc.nextInt();
			int h2 = sc.nextInt()*3600;
			int m2 = sc.nextInt()*60;
			int s2 = sc.nextInt();
			
			int sub = (h2 + m2 + s2) - (h1 + m1 + s1);
			int h3 = sub/3600;
			sub %= 3600;
			int m3 = sub/60;
			sub %= 60;
			int s3 = sub;
			
			System.out.println(h3 + " " + m3 + " " + s3);
		}
	}
}
