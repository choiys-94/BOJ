package boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class _BOJ_17143_낚시왕 {
	static class Shark implements Comparable<Shark>{
		int x;
		int y;
		int speed;
		int dir;
		int size;
		Shark(int x, int y, int speed, int dir, int size){
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
		@Override
		public int compareTo(Shark o) {
			// TODO Auto-generated method stub
			if(this.y == o.y) {
				return this.x - o.x;
			}
			return this.y - o.y;
		}
	}
	static int N, M, K;
	static int fx, fy;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();	// 행
		M = sc.nextInt();	// 열
		K = sc.nextInt();	// 상어 마리 수
		fx = 1;
		fy = 0;
		ArrayList<Shark> shark = new ArrayList<>();
		for(int i=0; i<K; i++) {
			// 상어 정보 입력: x좌표, y좌표, 속력, 방향, 크기 
			shark.add(new Shark(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
		}
		
		if(!shark.isEmpty()) {
			for(int i=0; i<M; i++) {
				if(shark.isEmpty())
					break;
				Collections.sort(shark);
				fish(shark);
				move(shark);
			}
		}
		
		System.out.println(result);
	}
	
	static int result = 0;
	
	static void fish(ArrayList<Shark> shark) {
		if(!check(fx, ++fy)) {
			return;
		}
		
		int dist = 99999;
		int idx = -1;
		for(int i=0; i<shark.size(); i++) {
			if(shark.get(i).y == fy) {
				if(dist > shark.get(i).x) {
					dist = shark.get(i).x; 
					idx = i;
				}
			}
		}
		if(idx != -1) {
			result += shark.get(idx).size;
			shark.remove(idx);
		}
	}
	
	// 0, 위, 아래, 오른쪽, 왼쪽
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, 1, -1};
	
	static void move(ArrayList<Shark> shark) {
		for(Shark s: shark) {
			int cnt = s.speed;
			while(cnt > 0) {
				cnt--;
				int nx = s.x + dx[s.dir];
				int ny = s.y + dy[s.dir];
				if(!check(nx, ny)) {
					if(s.dir == 1)
						s.dir = 2;
					else if(s.dir == 2)
						s.dir = 1;
					else if(s.dir == 3)
						s.dir = 4;
					else if(s.dir == 4)
						s.dir = 3;
					nx = s.x + dx[s.dir];
					ny = s.y + dy[s.dir];
				}
				s.x = nx;
				s.y = ny;
			}
		}
		
		Collections.sort(shark);
		for(int i=shark.size()-1; i>=1; i--) {
			if(shark.get(i).x == shark.get(i-1).x && shark.get(i).y == shark.get(i-1).y) {
				if(shark.get(i).size > shark.get(i-1).size) {
					shark.remove(i-1);
				}
				else {
					shark.remove(i);
				}
			}
		}
	}
	
	static boolean check(int x, int y) {
		return x>=1 && y>=1 && x<=N && y<=M;
	}
}
