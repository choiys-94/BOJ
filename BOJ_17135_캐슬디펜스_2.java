package boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class BOJ_17135_캐슬디펜스_2 {
	static class Enemy{
		int r;
		int c;
		Enemy(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int N, M, D;
	static ArrayList<Enemy> enemy;
	static ArrayList<Enemy> tenemy;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 행의 수
		M = sc.nextInt(); // 열의 수
		D = sc.nextInt(); // 사정거리
		enemy = new ArrayList<Enemy>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				int in = sc.nextInt();
				if(in == 1) {
					enemy.add(new Enemy(i, j));
				}
			}
		}
		tenemy = new ArrayList<>();
		for(Enemy e: enemy) {
			tenemy.add(new Enemy(e.r, e.c));
		}
		comb(new int[3], 0, 0);
		System.out.println(ans);
	}
	
	static void play(int[] sel) {
		HashSet<Integer> toDel = new HashSet<>();
		int cnt = 0;
		while(!enemy.isEmpty()) {
			toDel.clear(); // 죽일 적 임시 배열 초기화
			for(int i=0; i<sel.length; i++) {
				int r = N; // 현재 궁수 행  
				int c = sel[i]; // 현재 궁수 열
				int dist = Integer.MAX_VALUE;
				int tc = 999; // 죽일 적 임시 열 값 
				int idx = 999; // 죽일 적 인덱스 값
				
				// 적 탐색하면서 없앨 적 선별
				for(int j=0; j<enemy.size(); j++) {
					Enemy cur = enemy.get(j);
					int tdist = Math.abs(r - cur.r) + Math.abs(c - cur.c);
					// 이전에 구한 적과 거리가 같을 경우
					if(tdist > D)
						continue;
					if(dist == tdist) {
						// 이번이 더 왼쪽이면 교체
						if(tc > cur.c) {
							tc = cur.c;
							idx = j;
						}
					}
					// 이번 놈이 더 짧으면 바로 교체
					else if(dist > tdist) {
						dist = tdist;
						tc = cur.c;
						idx = j;
					}
				}
				if(idx != 999)
					toDel.add(idx);
			}
			// 맞아 죽은 놈들 처리
			cnt += toDel.size();

//			for(int d: toDel) {
//				System.out.println(enemy.get(d).r + " " + enemy.get(d).c);
//			}
//			System.out.println("cnt: " + cnt);
			
			if(!toDel.isEmpty()) {
				ArrayList<Integer> tmpDel = new ArrayList<>(toDel);
				Collections.sort(tmpDel);
				for(int i=tmpDel.size()-1; i>=0; i--) {
					enemy.remove((int)tmpDel.get(i));
				}
			}
			
			// 적 한칸씩 내리기
			if(!enemy.isEmpty()) {
				for(int i=enemy.size()-1; i>=0; i--) {
					if(enemy.get(i).r + 1 == N) {
						enemy.remove(i);
						continue;
					}
					enemy.get(i).r += 1;
				}
			}
		}
		ans = Math.max(ans, cnt);
	}
	static void comb(int[] sel, int idx, int k) {
		if(sel.length == k) {
//			System.out.println(Arrays.toString(sel));
			play(sel);
			enemy.clear();
			for(Enemy e: tenemy) {
				enemy.add(new Enemy(e.r, e.c));
			}
			return;
		}
		if(M == idx)
			return;
		
		sel[k] = idx;
		comb(sel, idx+1, k+1);
		comb(sel, idx+1, k);
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && r<M;
	}
}
