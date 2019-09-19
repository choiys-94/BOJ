package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_5576_콘테스트 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] arr = new int[10];
		for(int i=0; i<10; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		System.out.println(arr[9] + arr[8] + arr[7]);
		Arrays.fill(arr, 0);
		for(int i=0; i<10; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		System.out.println(arr[9] + arr[8] + arr[7]);
	}
}
