package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class _BOJ_2580_스도쿠 {
	static class Pos{
		int r;
		int c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static final int N = 9;
	static int[][] map;
	static ArrayList<Pos> arr;
	static boolean[] validate;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		map = new int[N][N];
		arr = new ArrayList<>();
		validate = new boolean[N+1];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 0) {
					arr.add(new Pos(i, j));
				}
			}
		}
		
		solve(0);
	}
	
	static boolean flag = false;
	
	static void solve(int cnt) {
		if(cnt == arr.size()) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.exit(0);
			return;
		}
		
		for(int i=0; i<arr.size(); i++) {
			Pos cur = arr.get(i);
			if(map[cur.r][cur.c] != 0)
				continue;
			Arrays.fill(validate, false);
			for(int j=0; j<N; j++) {
				validate[map[cur.r][j]] = true;
				validate[map[j][cur.c]] = true;
			}
			int str = -1;
			int stc = -1;
			if(cur.r < 3)
				str = 0;
			else if(cur.r < 6)
				str = 3;
			else if(cur.r < 9)
				str = 6;
			
			if(cur.c < 3)
				stc = 0;
			else if(cur.c < 6)
				stc = 3;
			else if(cur.c < 9)
				stc = 6;
			
			for(int r=str; r<str+3; r++) {
				for(int c=stc; c<stc+3; c++) {
					validate[map[r][c]] = true;
				}
			}
			
			for(int k=1; k<=9; k++) {
				if(validate[k] == false) {
					map[cur.r][cur.c] = k; 
					solve(cnt+1);
					map[cur.r][cur.c] = 0; 
				}
			}
		}
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
}
