package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_4153_직각삼각형 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long[] in = new long[3];
		while(true) {
			in[0] = sc.nextLong();
			in[1] = sc.nextLong();
			in[2] = sc.nextLong();
			Arrays.sort(in);
			if(in[0] == 0 && in[1] == 0 && in[2] == 0)
				break;
			if(Math.pow(in[0], 2) + Math.pow(in[1], 2) == Math.pow(in[2], 2))
				System.out.println("right");
			else
				System.out.println("wrong");
		}
	}
}
