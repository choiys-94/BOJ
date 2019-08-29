package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_1094_막대기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int part = 64;
		ArrayList<Integer> sticks = new ArrayList<>();
		sticks.add(part);
		while(true) {
			int sum = 0;
			for(int s: sticks) {
				sum += s;
			}
			if(sum == N) {
				break;
			}
			else if(sum > N) {
				int tmp = sticks.get(sticks.size()-1)/2;
				sticks.remove(sticks.size()-1);
				sticks.add(tmp);
				sticks.add(tmp);
				sum = 0;
				for(int i=0; i<sticks.size()-1; i++) {
					sum += sticks.get(i);
				}
				if(sum >= N) {
					sticks.remove(sticks.size()-1);
				}
			}
		}
		System.out.println(sticks.size());
	}
}
