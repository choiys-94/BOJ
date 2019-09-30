package boj;

import java.util.Scanner;

public class BOJ_10409_서버 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int T = sc.nextInt();
		int cnt = 0;
		for(int i=0; i<N; i++) {
			int in = sc.nextInt();
			if(T - in >= 0) {
				T -= in;
				cnt++;
			}
			else {
				break;
			}
		}
		System.out.println(cnt);
	}
}
