package boj;

import java.util.Scanner;

public class BOJ_3053_택시기하학 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int R = sc.nextInt();
		
		double a = (double)(R*R*Math.PI);
		double b = (double)(Math.pow(2*R, 2)/2);
		
		System.out.printf("%.6f\n", a);
		System.out.printf("%.6f", b);
	}
}
