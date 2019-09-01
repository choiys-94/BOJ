package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1021_회전하는큐 {
	static int cnt = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		ArrayList<Integer> queue = new ArrayList<>();
		for(int i=1; i<=N; i++) {
			queue.add(i);
		}
		for(int i=0; i<M; i++) {
			solve(queue, sc.nextInt());
		}

		System.out.println(cnt);
	}
	
	static void solve(ArrayList<Integer> queue, int target) {
		int idx = queue.indexOf(target);
		if(idx > queue.size()/2) {
			while(queue.get(0) != target) {
				cnt++;
				rshift(queue);
			}
			queue.remove(0);
		}
		else if(idx <= queue.size()/2) {
			while(queue.get(0) != target) {
				cnt++;
				lshift(queue);
			}
			queue.remove(0);
		}
		else{
			queue.remove(0);
		}
	}
	
	static void lshift(ArrayList<Integer> queue) {
		int tmp = queue.get(0);
		queue.remove(0);
		queue.add(tmp);
	}
	
	static void rshift(ArrayList<Integer> queue) {
		int tmp = queue.get(queue.size()-1);
		queue.remove(queue.size()-1);
		queue.add(0,tmp);
	}
}
