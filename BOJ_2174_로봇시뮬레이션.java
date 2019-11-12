package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_2174_로봇시뮬레이션 {
	static class Robot{
		int r;
		int c;
		int dir;
		Robot(int r, int c, int dir){
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	static int N, M;
	static int R, K;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt(); // 세로
		N = sc.nextInt(); // 가로
		R = sc.nextInt(); // 로봇 개수
		K = sc.nextInt(); // 명령 개수
		map = new int[N+1][M+1];
		ArrayList<Robot> robot = new ArrayList<>();
		robot.add(new Robot(0,0,0));
		for(int i=1; i<=R; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int d = 0;
			switch(sc.next().charAt(0)) {
			case 'N':
				d = 0;
				break;
			case 'W':
				d = 1;
				break;
			case 'S':
				d = 2;
				break;
			case 'E':
				d = 3;
				break;
			}
			robot.add(new Robot(b, a, d));
			map[b][a] = i;
		}
		
		boolean flag = true;
		
		out: for(int i=0; i<K; i++) {
			int idx = sc.nextInt();
			char op = sc.next().charAt(0);
			int c = sc.nextInt();
			int d = robot.get(idx).dir;
			switch(op) {
			case 'R':
				for(int j=0; j<c%4; j++) {
					d--;
					if(d == -1) d = 3;
				}
				robot.get(idx).dir = d;
				break;
			case 'L':
				for(int j=0; j<c%4; j++) {
					d++;
					if(d == 4) d = 0;
				}
				robot.get(idx).dir = d;
				break;
			case 'F':
				int nr = robot.get(idx).r;
				int nc = robot.get(idx).c;
				for(int j=0; j<c; j++) {
					nr += dr[d];
					nc += dc[d];
					if(!check(nr, nc)) {
						System.out.println("Robot " + (idx) + " crashes into the wall");
						flag = false;
						break out;
					}
					if(map[nr][nc] != 0) {
						System.out.println("Robot " + (idx) + " crashes into robot " + map[nr][nc]);
						flag = false;
						break out;
					}
				}
				map[robot.get(idx).r][robot.get(idx).c] = 0;
				map[nr][nc] = idx;
				robot.get(idx).r = nr;
				robot.get(idx).c = nc;
				break;
			}
		}
		if(flag)
			System.out.println("OK");
	}
	
	static boolean check(int r, int c) {
		return r>=1 && c>=1 && r<=N && c<=M;
	}
	
	// N W S E
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, -1, 0, 1};
}
