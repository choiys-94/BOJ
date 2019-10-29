package boj;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BOJ_1026_보물 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr = new int[N];
		Integer[] brr = new Integer[N];
		for(int i=0; i<N; i++)
			arr[i] = sc.nextInt();
		Arrays.sort(arr);
		for(int i=0; i<N; i++)
			brr[i] = sc.nextInt();
		Arrays.sort(brr, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		long sum = 0;
		for(int i=0; i<N; i++) {
			sum += arr[i] * brr[i];
		}
		
		System.out.println(sum);
	}
}
