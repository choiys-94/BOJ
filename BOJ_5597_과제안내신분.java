package boj;

import java.util.Scanner;

public class BOJ_5597_과제안내신분 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] arr = new int[31];
		for(int i=0; i<28; i++) {
			arr[sc.nextInt()]++;
		}
		
		for(int i=1; i<=30; i++) {
			if(arr[i] == 0)
				System.out.println(i);
		}
	}
}
