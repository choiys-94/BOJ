package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_10539_수빈이와수열 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		ArrayList<Integer> oarr = new ArrayList<>();
		for(int i=1; i<=N; i++) {
			int in = sc.nextInt();
			if(i == 1)
				oarr.add(in);
			else {
				int sum = 0;
				for(int j=0; j<i-1; j++) {
					sum += oarr.get(j);
				}
				oarr.add(in*i - sum);
			}
		}
		
		for(Integer i: oarr) {
			System.out.print(i + " ");
		}
	}
}
