package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2581_소수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[] flag = new boolean[10001];
		
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		
		long sum = 0;
		int first = -1;
		boolean f = true;
		
		for(int i=2; i<=b; i++) {
			if(flag[i]) continue;
			for(int j=i*2; j<=b; j+=i)
				flag[j] = true;
			if(i >= a) {
				if(f) {
					first = i;
					f = false;
				}
				sum += i;
			}
		}
		
		if(first != -1) {
			System.out.println(sum);
			System.out.println(first);
		}
		else
			System.out.println(-1);
	}
}
