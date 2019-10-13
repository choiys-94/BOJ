package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12174_include {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			String in = br.readLine().trim();
			String[] arr = new String[N];
			sb.append("Case #").append(tc).append(": ");
			for(int i=0; i<N; i++) {
				arr[i] = new String(in.substring(8*i, 8*i+8));
				arr[i] = arr[i].replace("O", "0").replace("I", "1");
				sb.append((char)Integer.parseInt(arr[i], 2));
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
