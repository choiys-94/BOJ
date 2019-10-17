package boj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1463_1로만들기 {
	static class Node{
		int num;
		int cnt;
		Node(int num, int cnt){
			this.num = num;
			this.cnt = cnt;
		}
	}
	static int N;
	static int[] memo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		memo = new int[N+1];
		Arrays.fill(memo, 9999999);
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(N, 0));
		while(!queue.isEmpty()) {
			Node q = queue.poll();
			if(q.num == 1) {
				System.out.println(q.cnt);
				break;
			}
			if(memo[q.num] < q.cnt)
				continue;
			if(q.num % 3 == 0)
				queue.add(new Node(q.num/3, q.cnt+1));
			else if(q.num % 2 == 0)
				queue.add(new Node(q.num/2, q.cnt+1));
			queue.add(new Node(q.num-1, q.cnt+1));
			memo[q.num] = q.cnt; 
		}
	}
}
