package boj;

import java.util.Scanner;

public class _BOJ_2579_계단오르기_메모 {
	static int N;
	static int[] arr;
	static int[] mScore;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N+1];
		mScore = new int[N+1];
		for(int i=1; i<=N; i++)
			arr[i] = sc.nextInt();
		dfs(N, 1, arr[N]);
		System.out.println(ans);
	}
	
	static void dfs(int idx, int step, int sum) {
		if(idx == 0)
			ans = Math.max(ans, sum);
		if(idx < 1)
			return;
		if(mScore[idx] > sum)
			return;
		
		if(step + 1 < 3 || idx == 1) {
			mScore[idx-1] = Math.max(mScore[idx-1], sum + arr[idx-1]);
			dfs(idx-1, step + 1, sum + arr[idx-1]);
		}
		if(idx-2 >= 0 && mScore[idx-2] < sum + arr[idx-2]) {
			mScore[idx-2] = sum + arr[idx-2];
			dfs(idx-2, 1, sum + arr[idx-2]);
		}
	}
}
