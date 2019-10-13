package boj;

import java.util.Scanner;

public class BOJ_1547_공 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr = new int[4];
		for(int i=1; i<=3; i++) {
			arr[i] = i;
		}
		for(int i=1; i<=N; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int aidx = -1, bidx = -1;
			for(int j=1; j<=3; j++) {
				if(arr[j] == a)
					aidx = j;
				if(arr[j] == b)
					bidx = j;
			}
			int tmp = arr[aidx];
			arr[aidx] = arr[bidx];
			arr[bidx] = tmp;
		}
		System.out.println(arr[1]);
	}
}
