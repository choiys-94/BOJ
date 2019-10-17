package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458_시험감독_2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine()); 
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		long ans = 0;
		for(int i=0; i<N; i++) {
			ans++;
			arr[i] -= a;
			while(arr[i] > 0) {
				if(arr[i] < b) {
					ans++;
					break;
				}
				ans += arr[i] / b;
				arr[i] %= b;
			}
		}
		
		System.out.println(ans);
	}
}
