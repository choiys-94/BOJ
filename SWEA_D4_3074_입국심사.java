package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_D4_3074_입국심사 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			long[] arr = new long[N];
			for(int i=0; i<N; i++) {
				arr[i] = Long.parseLong(br.readLine());
			}
			Arrays.sort(arr);
			long right = arr[N-1]*M;
			long left = 0;
			
			long ans = right;
			
			while(left <= right) {
				long mid = (left + right)/2;
				long sum = 0;
				for(int i=0; i<N; i++) {
					if(arr[i] <= mid)
						sum += mid/arr[i];
					else
						break;
				}
				if(sum >= M) {
					ans = Math.min(ans, mid);
					right = mid - 1;
				}
				else
					left = mid + 1;
				if(left > right)
					ans = Math.min(ans, left);
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}
