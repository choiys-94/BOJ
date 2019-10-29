package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14659_한조서열정리하고옴ㅋㅋ {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int in = Integer.parseInt(st.nextToken());
		int prev = in;
		int sum = 0;
		int ans = Integer.MIN_VALUE;
		for(int i=1; i<N; i++) {
			in = Integer.parseInt(st.nextToken());
			if(prev > in) {
				sum++;
				ans = Math.max(ans, sum);
			}
			else {
				sum = 0;
				prev = in;
			}
		}
		
		System.out.println(ans);
	}
}
