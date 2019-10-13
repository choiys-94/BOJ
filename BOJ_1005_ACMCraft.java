package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1005_ACMCraft {
	static int N, K;
	static int[] price;
	static ArrayList<Integer> forward[];
	static ArrayList<Integer> backward[];
	static int goal;
	static int[] parents;
	
	static class Node{
		int node;
		int sum;
		Node(int node, int sum){
			this.node = node;
			this.sum = sum;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			price = new int[N+1];
			forward = new ArrayList[N+1];
			backward = new ArrayList[N+1];
			parents = new int[N+1];
			ArrayList<Node> tmp[] = new ArrayList[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				price[i] = Integer.parseInt(st.nextToken());
				forward[i] = new ArrayList<>();
				backward[i] = new ArrayList<>();
				tmp[i] = new ArrayList<>();
			}
			for(int i=1; i<=K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				forward[a].add(b);
				backward[b].add(a);
			}
			goal = Integer.parseInt(br.readLine());
			boolean[] visited = new boolean[N+1];
		
			ArrayList<Integer> start = new ArrayList<>();
			
			int[] check = new int[N+1];
			for(int i=1; i<=N; i++) {
				if(!backward[i].isEmpty())
					check[i] += backward[i].size();
				else
					start.add(i);
			}

			ArrayList<Integer> res = new ArrayList<>();
			
			Queue<Node> queue = new LinkedList<>();
			for(int s: start) {
				queue.add(new Node(s, price[s]));
				visited[s] = true;
			}
			out: while(!queue.isEmpty()) {
				Node v = queue.poll();
				if(v.node == goal) {
					if(check[v.node] == 0) {
//						sb.append(v.sum).append("\n");
//						break;
						res.add(v.sum);
						continue;
					}
				}
				
				for(int i=0; i<forward[v.node].size(); i++) {
					int token = forward[v.node].get(i);
					if(visited[token] && check[token] == 0)
						continue;
					check[token]--;
					tmp[token].add(new Node(token, v.sum + price[token]));
					
//					System.out.println(token + ": " + v.sum);
//					System.out.println(Arrays.toString(check));
					
					if(check[token] == 0) {
						Collections.sort(tmp[token], new Comparator<Node>() {
							@Override
							public int compare(Node o1, Node o2) {
								return o2.sum - o1.sum;
							}
						});
						
						if(token == goal) {
//							sb.append(tmp[token].get(0).sum).append("\n");
							res.add(tmp[token].get(0).sum);
							continue;
						}
						visited[token] = true;
						queue.add(new Node(token, tmp[token].get(0).sum));
						continue;
					}
				}
			}
			Collections.sort(res);
			sb.append(res.get(0)).append("\n");
		}
		System.out.println(sb.toString());
	}
}
