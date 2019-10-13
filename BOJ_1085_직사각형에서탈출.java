package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1085_직사각형에서탈출 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int res = Integer.MAX_VALUE;
		res = Math.min(res, Math.abs(r-w));
		res = Math.min(res, Math.abs(r));
		res = Math.min(res, Math.abs(c-h));
		res = Math.min(res, Math.abs(c));
		
		System.out.println(res);
	}
}
