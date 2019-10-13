package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class BOJ_17140_이차원배열과연산 {
	static int[][] arr;
	static int R = 3, C = 3;
	static int maxR = Integer.MIN_VALUE;
	static int maxC = Integer.MIN_VALUE;
	static class Count implements Comparable<Count>{
		int num;
		int cnt;
		Count(int num, int cnt){
			this.num = num;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Count o) {
			if(this.cnt == o.cnt) {
				return this.num - o.num;
			}
			return this.cnt - o.cnt;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		arr = new int[102][101];
		
		int r = sc.nextInt();
		int c = sc.nextInt();
		int v = sc.nextInt();
		
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				arr[i][j] = sc.nextInt();
			}
			arr[i][0] = R;	// 가로 배열의 길이 저장
			arr[101][i] = C;	// 세로 배열의 길이 저장
		}
		
		int time = 0;

		maxR = R;
		maxC = C;
		while(time <= 100) {
			R = maxR;
			C = maxC;
			if(arr[r][c] == v) {
				break;
			}
			if(R >= C) {
				maxC = Integer.MIN_VALUE;
				for(int i=1; i<=R; i++) {
					opR(i);
				}
			}
			else {
				maxR = Integer.MIN_VALUE;
				for(int i=1; i<=C; i++) {
					opC(i);
				}
			}
				
			time++;
		}
		if(time > 100)
			System.out.println(-1);
		else
			System.out.println(time);
	}
	
	static void opR(int idx) {
		HashMap<Integer, Integer> intCount = new HashMap<>();
		
		for(int i=1; i<=C; i++) {
			int key = arr[idx][i];
			if(key == 0)
				continue;
			if(intCount.containsKey(key)) {
				intCount.put(key, intCount.get(key)+1);
			}
			else {
				intCount.put(key, 1);
			}
		}
		
		ArrayList<Count> tmpList = new ArrayList<>();
		for(int c: intCount.keySet()) {
			tmpList.add(new Count(c, intCount.get(c)));
		}
		Collections.sort(tmpList);
		
		arr[idx][0] = tmpList.size()*2;
		
		int base = 0;
		for(int i=1; i<arr[idx][0]; i+=2) {
			if(base < tmpList.size()) {
				arr[idx][i] = tmpList.get(base).num;
				arr[idx][i+1] = tmpList.get(base).cnt;
				base++;
			}
			else {
				break;
			}
		}
		
		for(int i=arr[idx][0]+1; i<=100; i++) {
			arr[idx][i] = 0;
		}
		
		maxC = Math.max(maxC, arr[idx][0]);
	}
	
	static void opC(int idx) {
		HashMap<Integer, Integer> intCount = new HashMap<>();
		for(int i=1; i<=R; i++) {
			int key = arr[i][idx];
			if(key == 0)
				continue;
			if(intCount.containsKey(key)) {
				intCount.put(key, intCount.get(key)+1);
			}
			else {
				intCount.put(key, 1);
			}
		}
		
		ArrayList<Count> tmpList = new ArrayList<>();
		for(int c: intCount.keySet()) {
			tmpList.add(new Count(c, intCount.get(c)));
		}
		Collections.sort(tmpList);
		
		arr[101][idx] = tmpList.size()*2;
		int base = 0;
		for(int i=1; i<arr[101][idx]; i += 2) {
			if(base < tmpList.size()) {
				arr[i][idx] = tmpList.get(base).num;
				arr[i+1][idx] = tmpList.get(base).cnt;
				base++;
			}
			else {
				break;
			}
		}
		for(int i=arr[101][idx]+1; i<=100; i++) {
			arr[i][idx] = 0;
		}
		maxR = Math.max(maxR, arr[101][idx]);
		
	}
}
