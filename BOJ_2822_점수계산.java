package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2822_점수계산 {
	static class Point implements Comparable<Point>{
		int idx;
		int score;
		Point(int i, int s){
			idx = i;
			score = s;
		}
		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return o.score-this.score;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Point[] in = new Point[8];
		for(int i=0; i<8; i++) {
			in[i] = new Point((i+1), sc.nextInt());
		}
		
		Arrays.sort(in);
		int[] result = new int[5];
		int sum = 0;
		for(int i=0; i<5; i++) {
			result[i] = in[i].idx;
			sum += in[i].score;
		}
		Arrays.sort(result);
		System.out.println(sum);
		for(int i=0; i<5; i++) {
			System.out.print(result[i] + " ");
		}
	}
}
