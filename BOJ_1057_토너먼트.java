package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_1057_토너먼트 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int A = sc.nextInt();
		int B = sc.nextInt();
		
		solve(N, A, B);
	}
	
	static void solve(int N, int A, int B) {
		ArrayList<Integer> players = new ArrayList<>();
		ArrayList<Integer> tmp = new ArrayList<>();
		int cnt = 1;
		for(int i=1; i<=N; i++) {
			players.add(i);
		}
		
		boolean flag = false;
		
		while(players.size() >= 2) {
			for(int i=1; i<players.size(); i+=2) {
				if((players.get(i) == A && players.get(i-1) == B) || (players.get(i) == B && players.get(i-1) == A)){
					flag = true;
					break;
				}
				else if(players.get(i) == A || players.get(i-1) == A) {
					tmp.add(A);
				}
				else if(players.get(i) == B || players.get(i-1) == B) {
					tmp.add(B);
				}
				else {
					tmp.add(players.get(i));
				}
			}
			if(players.size()%2 == 1) {
//				if((players.get(players.size()-1) == A && players.get(players.size()-2) == B) || (players.get(players.size()-1) == B && players.get(players.size()-2) == A)){
//					flag = true;
//					break;
//				}
				tmp.add(players.get(players.size()-1));
			}
			if(flag)
				break;
			players = new ArrayList<>(tmp);
			tmp.clear();
			cnt++;
		}
		if(flag)
			System.out.println(cnt);
		else {
			System.out.println(-1);
		}
	}
}
