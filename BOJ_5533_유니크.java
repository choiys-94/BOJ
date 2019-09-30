package boj;

import java.util.Scanner;

public class BOJ_5533_유니크 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[][] player = new int[N][3];
		int[] sum = new int[N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<3; j++) {
				player[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<3; j++) {
				boolean flag = true;
				for(int k=0; k<N; k++) {
					if(i != k && player[k][j] == player[i][j]) {
						flag = false;
						break;
					}
				}
				if(flag) {
					sum[i] += player[i][j];
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			System.out.println(sum[i]);
		}
	}
}
