package boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_16937_두스티커 {
	static class Sticker implements Comparable<Sticker>{
		int h;
		int w;
		int area;
		Sticker(int h, int w){
			this.h = h;
			this.w = w;
			this.area = h*w;
		}
		@Override
		public int compareTo(Sticker o) {
			// TODO Auto-generated method stub
			return o.area - this.area;
		}
	}
	static int H, W, N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		H = sc.nextInt(); // 세로
		W = sc.nextInt(); // 가로
		N = sc.nextInt(); // 스티커 수
		ArrayList<Sticker> sticker = new ArrayList<>();
		for(int i=0; i<N; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			if((a >= H && b >= W) || (a >= W && b >= H)) {
				continue;
			}
			else if(a <= H && b <= W) {
				sticker.add(new Sticker(a, b));
			}
			else if(b <= H && a <= W) {
				sticker.add(new Sticker(b, a));
			}
		}
		
		Collections.sort(sticker);
		
		for(int i=0; i<sticker.size()-1; i++) {
			for(int j=i+1; j<sticker.size(); j++) {
				if(i == j) {
					continue;
				}
				Sticker s1 = sticker.get(i);
				Sticker s2 = sticker.get(j);
				solve(new Sticker(s1.h, s1.w), new Sticker(s2.h, s2.w));
				solve(new Sticker(s1.h, s1.w), new Sticker(s2.w, s2.h));
				solve(new Sticker(s1.w, s1.h), new Sticker(s2.h, s2.w));
				solve(new Sticker(s1.w, s1.h), new Sticker(s2.w, s2.h));
			}
		}
		if(result == Integer.MIN_VALUE)
			System.out.println(0);
		else
			System.out.println(result);
	}
	
	static int result = Integer.MIN_VALUE;
	
	static void solve(Sticker s1, Sticker s2) {
		int rh1 = -1;
		int rh2 = -1;
		int rw1 = -1;
		int rw2 = -1;
		
		if((s1.h == H && s1.w == W) || (s1.w == H && s1.h == W)) {
			return;
		}
		else if(s1.h == H) {
			rh1 = H;
			rw1 = W-s1.w;
			if(rw1 < 0)
				return;
			if(s2.h <= rh1 && s2.w <= rw1) {
				result = Math.max(result, s1.area+s2.area);
			}
			else if(s2.w <= rh1 && s2.h <= rw1) {
				result = Math.max(result, s1.area+s2.area);
			}
		}
		else if(s1.w == W) {
			rh1 = H-s1.h;
			rw1 = W;
			if(rh1 < 0)
				return;
			if(s2.h <= rh1 && s2.w <= rw1) {
				result = Math.max(result, s1.area+s2.area);
			}
			else if(s2.w <= rh1 && s2.h <= rw1) {
				result = Math.max(result, s1.area+s2.area);
			}
		}
		else {
			rh1 = H - s1.h;
			rw1 = W;
			rh2 = H;
			rw2 = W - s1.w;
			if(rh1 < 0 || rw2 < 0)
				return;
			if((s2.h <= rh1 && s2.w <= rw1) || (s2.h <= rh2 && s2.w <= rw2)) {
				result = Math.max(result, s1.area+s2.area);
			}
			else if((s2.w <= rh1 && s2.h <= rw1) || (s2.w <= rh2 && s2.h <= rw2)) {
				result = Math.max(result, s1.area+s2.area);
			}
		}
	}
}
