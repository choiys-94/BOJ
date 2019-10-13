package boj;

import java.util.Scanner;

public class SWEA_의석이의우뚝선산 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			int result = 0, up = 0, down = 0;
			for(int i=0; i<N; i++) {
				arr[i] = sc.nextInt();
				if(i == 0)
					continue;
				if(down != 0 && arr[i-1] < arr[i]) {
					result += up*down;
					up = 0;
					down = 0;
				}
				if(arr[i-1] > arr[i])
					down++;
				else if(arr[i-1] < arr[i])
					up++;
			}
			result += up*down;
			System.out.println("#" + tc + " " + result);
		}
	}
}
