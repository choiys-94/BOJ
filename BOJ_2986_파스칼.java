package boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_2986_파스칼 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		ArrayList<Integer> su = new ArrayList<>();
		for(int i=1; i<=(int)Math.sqrt(N); i++) {
			if(N % i == 0) {
				su.add(i);
				su.add(N/i);
			}
		}
		Collections.sort(su);
		int result = 0;
		for(int i=su.size()-1; i>=0; i--) {
			if(su.get(i) < N) {
				result = N - su.get(i);
				break;
			}
		}
		System.out.println(result);
	}
}
