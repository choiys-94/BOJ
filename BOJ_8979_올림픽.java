package boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_8979_올림픽 {
	static class Medal implements Comparable<Medal>{
		int idx;
		int gold;
		int silver;
		int copper;
		int rank = 1;
		Medal(int i, int g, int s, int c){
			idx = i;
			gold = g;
			silver = s;
			copper = c;
		}
		@Override
		public int compareTo(Medal o) {
			// TODO Auto-generated method stub
			if(o.gold == this.gold) {
				if(o.silver == this.silver) {
					return o.copper - this.copper;
				}
				return o.silver - this.silver;
			}
			return o.gold - this.gold;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		ArrayList<Medal> arr = new ArrayList<>();
		for(int i=0; i<N; i++) {
			arr.add(new Medal(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
		}
		
		Collections.sort(arr);
		int cnt = 1;
		int result = 1;
		int tmp = 0;
		boolean flag = false;
		for(int i=1; i<arr.size(); i++) {
			Medal a = arr.get(i-1);
			Medal b = arr.get(i);
			if(a.gold == b.gold && a.silver == b.silver && a.copper == b.copper) {
				b.rank = cnt;
				tmp++;
				if(i == 1)
					tmp++;
				flag = true;
			}
			else {
				if(flag == false) {
					cnt++;
					tmp = 0;
				}
				cnt += tmp;
				b.rank = cnt;
				tmp = 1;
				flag = false;
			}
			if(b.idx == M)
				result = b.rank;
		}
		System.out.println(result);
	}
}
