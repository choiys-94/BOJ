package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_1764_듣보잡 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<String> in = new ArrayList<>();
		ArrayList<String> in2 = new ArrayList<>();
		ArrayList<String> result = new ArrayList<>();
		for(int i=0; i<N; i++) {
			in.add(br.readLine());
		}
		for(int i=0; i<M; i++) {
			in2.add(br.readLine());
		}
		
		Collections.sort(in);
		Collections.sort(in2);
	
		int idx1 = 0;
		int idx2 = 0;
		while(idx1 < N && idx2 < M) {
			if(in.get(idx1).equals(in2.get(idx2))) {
				result.add(in.get(idx1));
				idx1++;
				idx2++;
			}
			else if(in.get(idx1).compareTo(in2.get(idx2)) > 0) {
				idx2++;
			}
			else if(in.get(idx1).compareTo(in2.get(idx2)) < 0) {
				idx1++;
			}
		}
		
		System.out.println(result.size());
		for(String r: result) {
			System.out.println(r);
		}
	}
}
