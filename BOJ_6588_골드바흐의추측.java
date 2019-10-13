package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_6588_골드바흐의추측 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] visited = new boolean[1000001];
		ArrayList<Integer> sosu = new ArrayList<>();
		for(int i=2; i<=1000000; i++) {
			if(!visited[i]) {
				for(int j=i*2; j<=1000000; j+=i) {
					visited[j] = true;
				}
			}
		}
		
		for(int i=2; i<=1000000; i++) {
			if(i % 2 == 1 && !visited[i])
				sosu.add(i);
		}

		StringBuilder sb = new StringBuilder();
		while(true) {
			int N = Integer.parseInt(br.readLine().trim());
			if(N == 0)
				break;
			int token = 0;
			boolean flag = true;
			for(int i=0; i<sosu.size(); i++) {
				token = sosu.get(i);
				if(binarySearch(sosu, N-token, 0, sosu.size()-1)) {
					sb.append(N).append(" = ").append(token).append(" + ").append(N-token).append("\n");
					flag = false;
					break;
				}
			}
			if(flag) {
				sb.append("Goldbach's conjecture is wrong.\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	static boolean binarySearch(ArrayList<Integer> sosu, int n, int left, int right) {
		
		while(left <= right) {
			int mid = (left+right)/2;
			if(sosu.get(mid) == n) {
				return true;
			}
			if(left == right)
				break;
			if(sosu.get(mid) > n) {
				right = mid - 1;
			}
			else if(sosu.get(mid) < n) {
				left = mid + 1;
			}
		}
		return false;
	}
}
