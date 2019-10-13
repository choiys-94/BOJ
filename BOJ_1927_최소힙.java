package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1927_최소힙 {
	static class Heap implements Comparable<Heap>{
		int x;
		Heap(int x){
			this.x = x;
		}
		@Override
		public int compareTo(Heap o) {
			return this.x - o.x;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Heap> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int in = Integer.parseInt(br.readLine());
			if(in == 0) {
				if(pq.isEmpty())
					sb.append("0");
				else
					sb.append(pq.poll().x);
				sb.append("\n");
			}
			else
				pq.add(new Heap(in));
		}
		System.out.println(sb.toString());
	}
}
