package boj;

import java.util.Scanner;

public class BOJ_2798_블랙잭 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					if(i ==j || j == k || i == k)
						continue;
					int tmp = arr[i] + arr[j] + arr[k];
					if(tmp <= M && max < tmp) {
						max = tmp;
					}
				}
			}
		}
		
		System.out.println(max);
	}
}
