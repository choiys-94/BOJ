package boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_5618_공약수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr = new int[3];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		int g = 0;
		ArrayList<Integer> result = new ArrayList<>();
		if(N == 2) {
			g = gcd(arr[0], arr[1]);
		}
		else {
			g = gcd(gcd(arr[0], arr[1]),arr[2]);
		}
		for(int i=1; i<=Math.sqrt(g); i++) {
			if(g % i == 0) {
				if(!result.contains(i))
					result.add(i);
				if(!result.contains(g/i))
					result.add(g/i);
			}
		}
		Collections.sort(result);
		for(Integer i: result)
			System.out.println(i);
	}
	
	static int gcd(int a, int b) {
		while(true) {
			if(a % b == 0)
				return b;
			a = a % b;
			int tmp = 0;
			tmp = a;
			a = b;
			b = tmp;
		}
	}
}
