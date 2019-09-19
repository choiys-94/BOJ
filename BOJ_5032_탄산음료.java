package boj;

import java.util.Scanner;

public class BOJ_5032_탄산음료 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int e = sc.nextInt();	//현재 빈병
		int f = sc.nextInt();	//발견한 빈병
		int c = sc.nextInt();	//새병 위한 개수
		e += f;
		int sum = 0;
		while(e >= c) {
			sum += e/c;
			e = e%c + e/c;
		}
		
		System.out.println(sum);
	}
}
