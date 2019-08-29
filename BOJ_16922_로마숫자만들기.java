package algorithm;

import java.util.HashSet;
import java.util.Scanner;

public class BOJ_16922_로마숫자만들기 {
	static int[] nums = {1, 5, 10, 50};
	static int N;
	static HashSet<Integer> result = new HashSet<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		solve(0, 0, 0, 0);
		
		System.out.println(result.size());
	}
	
	static void solve(int idx, int n, int r, int sum) {
		if(r == N) {
			result.add(sum);
			return;
		}
		
		if(n == nums.length) {
			return;
		}
		
		solve(idx+1, n, r+1, sum + nums[n]);
		solve(idx+1, n+1, r, sum);
	}
}