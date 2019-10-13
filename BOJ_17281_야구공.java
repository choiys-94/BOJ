package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_17281_야구공 {
	static int R, C = 9;
	static int[][] player;
	static boolean[] ru;
	static int[] arr;
	static int result = Integer.MIN_VALUE;
	static int score = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		player = new int[R][C];
		arr = new int[C];
		ru = new boolean[4];
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				player[i][j] = sc.nextInt();
			}
		}
		for(int i=0; i<C; i++) {
			arr[i] = i;
		}
		
		perm(0);
		
		System.out.println(result);
	}
	
	static void perm(int idx) {
		if(arr.length == idx) {
			if(arr[3] != 0)
				return;
			
			score = 0;
			play();
			result = Math.max(result, score);
			return;
		}
		
		for(int i=idx; i<arr.length; i++) {
			swap(arr, idx, i);
			perm(idx+1);
			swap(arr, idx, i);
		}
	}
	
	static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
	
	static void play() {
		int tj = 0;
		for(int i=0; i<R; i++) {
			int out = 0;
			Arrays.fill(ru, false);
			while(out < 3) {
				int t = player[i][arr[tj]];
				switch(t) {
				case 0:
					out++;
					break;
				case 1:
				case 2:
				case 3:
				case 4:
					hit(t);
					break;
				}
				tj = (tj + 1) % C;
			}
		}
	}
	
	static void hit(int t) {
		ru[0] = true;
		for(int i=3; i>=0; i--) {
			if(ru[i] && i + t > 3) {
				ru[i] = false;
				score++;
			}
			else if(ru[i] && i + t <= 3){
				ru[i] = false;
				ru[i+t] = true;
			}
		}
	}
}
