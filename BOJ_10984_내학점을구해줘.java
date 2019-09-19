package boj;

import java.util.Scanner;

public class BOJ_10984_내학점을구해줘 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int num = 0;
			double score = 0;
			for(int i=0; i<N; i++) {
				int tNum = sc.nextInt();
				double tScore = sc.nextDouble();
				num += tNum;
				score += tNum*tScore;
			}
			
			System.out.print(num);
			System.out.printf(" %.1f\n", score/num);
		}
	}
}
