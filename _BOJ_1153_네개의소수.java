package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class _BOJ_1153_네개의소수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean[] visited = new boolean[1000001];
		ArrayList<Integer> sosu = new ArrayList<>();
		for(int i=2; i<=250000; i++) {
			if(!visited[i]) {
				for(int j=i*2; j<=250000; j+=i) {
					visited[j] = true;
				}
				sosu.add(i);
			}
		}

		int N = sc.nextInt();
		int idx = sosu.size()-1;
		for(int i=0; i<sosu.size(); i++) {
			if(sosu.get(i) > N)
				idx = i-1;
		}
		
		for(int i=0; i<idx; i++) {
			for(int j=0; j<idx; j++) {
				for(int k=0; k<idx; k++) {
					for(int l=0; l<idx; l++) {
						if(sosu.get(i))
					}
				}
			}
		}
	}
}
