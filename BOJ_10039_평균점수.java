package boj;

import java.util.Scanner;

public class BOJ_10039_평균점수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int result = 0;
		for(int i=0; i<5; i++) {
			int in = sc.nextInt();
			if(in > 40)
				result += in;
			else
				result += 40;
		}
		System.out.println(result/5);
	}
}
