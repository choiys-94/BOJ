package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class _BOJ_1325_효율적인해킹 {
	static class Node implements Comparable<Node>{
		int idx;
		int cnt;
		Node(int idx, int cnt){
			this.idx = idx;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Node o) {
			if(o.cnt == this.cnt)
				return this.idx - o.idx;
			return o.cnt - this.cnt;
		}
	}
	static int N, M;
	static ArrayList<Integer>[] forward;
	static ArrayList<Integer>[] backward;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		forward = new ArrayList[N+1];
		backward = new ArrayList[N+1];
		boolean[] visited = new boolean[N+1];
		for(int i=1; i<=N; i++) {
			forward[i] = new ArrayList<>();
			backward[i] = new ArrayList<>();
		}
		
		TreeSet<Integer> tmpRes = new TreeSet<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		});
		ArrayList<Integer> start = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tmpRes.add(a);
			tmpRes.add(b);
			forward[a].add(b);
			backward[b].add(a);
			if(backward[a].size() == 0) {
				start.add(a);
			}
		}
		if(N == 1) {
			System.out.println(1);
			System.exit(0);
		}
		int[] memo = new int[N+1];
		Queue<Node> queue = new LinkedList<>();
		
		for(int i=start.size()-1; i>=0; i--) {
			if(backward[start.get(i)].size() != 0) {
				start.remove(i);
			}
			else
				queue.add(new Node(start.get(i), 0));
		}
			
		if(queue.isEmpty()) {
			for(int t: tmpRes) {
				System.out.print(t + " ");
			}
			System.exit(0);
		}
		PriorityQueue<Node> res = new PriorityQueue<>();
		
		while(!queue.isEmpty()) {
			Node v = queue.poll();
			if(forward[v.idx].size() != 0) {
				for(int i=0; i<forward[v.idx].size(); i++) {
					int next = forward[v.idx].get(i);
					if(forward[next].size() == 0) {
						res.add(new Node(next, v.cnt+1));
					}
					if(visited[next]) {
						if(!(memo[next] < v.cnt+1))
							continue;
					}
					if(memo[next] < v.cnt+1) {
						visited[next] = true;
						memo[next] = v.cnt+1;
						queue.add(new Node(next, v.cnt+1));
					}
					else
						continue;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		if(!res.isEmpty()) {
			Node tmp = new Node(res.peek().idx, res.peek().cnt);
			tmp.idx = -1;
			while(!res.isEmpty()) {
				Node node = res.poll();
				if(tmp.cnt != node.cnt)
					break;
				if(tmp.idx != node.idx)
					sb.append(node.idx).append(" ");
				tmp = node;
			}
		}
		
		System.out.println(sb.toString());
	}
}
