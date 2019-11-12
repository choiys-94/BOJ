package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class _BOJ_10216_CountCircleGroups {
	static int N;
	static int[] parents;
	static int[][] in;
	static int[][] adj;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			in = new int[N][3];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				in[i][0] = r;
				in[i][1] = c;
				in[i][2] = v;
			}
			
			parents = new int[N];
			adj = new int[N][N];
			
			for(int i=0; i<N-1; i++) {
				for(int j=i+1; j<N; j++) {
					if(in[i][2] == 0 || in[j][2] == 0)
						continue;
					double dist = Math.sqrt((Math.pow(Math.abs(in[i][0] - in[j][0]),2) + Math.pow(Math.abs(in[i][1] - in[j][1]),2)));
					if(dist <= in[i][2] + in[j][2]) {
						adj[i][j] = 1;
						adj[j][i] = 1;
					}
				}
			}
			
			makeSet(N);
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(adj[i][j] == 1) {
						union(i, j);
					}
				}
			}
//			System.out.println(Arrays.toString(parents));			
			int cnt = 0;
			for(int i=0; i<N; i++) {
				if(parents[i] == i)
					cnt++;
			}
			System.out.println(cnt);
		}
	}
	
	static void makeSet(int n) {
		for(int i=0; i<n; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int x) {
		if(x == parents[x])
			return x;
		return parents[x] = findSet(parents[x]);
	}
	
	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if(px != py) {
			if(px < py)
				parents[py] = px;
			else
				parents[px] = py;
		}
	}
}
