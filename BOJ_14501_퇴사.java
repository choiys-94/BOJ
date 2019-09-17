package boj;

import java.util.Scanner;

public class BOJ_14501_퇴사 {
	static class Work{
		int due;		// 상담 기간
		int price;		// 상담 비용
		Work(int d, int p){
			due = d;
			price = p;
		}
	}
	static int N;
	static int MAX = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();		// 퇴사일
		
		Work[] works = new Work[N];		// 퇴사일까지의 상담 배열
		for(int i=0; i<N; i++) {
			int d = sc.nextInt();		// 기간
			int p = sc.nextInt();		// 비용
			works[i] = new Work(d, p);
		}
		
		solve(works, 0, 0);
		
		System.out.println(MAX);
	}
	
	static void solve(Work[] works, int idx, int sum) {
		if(idx > N) {
			return;
		}
		
		MAX = Math.max(MAX, sum);
		
		for(int i=idx; i<N; i++) {
			solve(works, i + works[i].due, sum + works[i].price);
		}
	}
}
