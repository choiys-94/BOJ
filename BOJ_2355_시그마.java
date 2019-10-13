package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2355_시그마 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long a = Integer.parseInt(st.nextToken());
		long b = Integer.parseInt(st.nextToken());
		if(a > b) {
			long tmp = a;
			a = b;
			b = tmp;
		}
		if(b-a == 1)
			System.out.println(a+b);
		else {
			long res = (a+b) * ((b-a+1)/2);
			if((b-a) % 2 == 0)
				res += (a+b)/2;
			System.out.println(res);
		}
	}
}
