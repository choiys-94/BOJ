package boj;

import java.util.Scanner;

public class BOJ_17136_색종이붙이기_2 {
	static int N = 10;
	static int[][] map;
	static int[] paper;
	static int ans = Integer.MAX_VALUE;
	static int num;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		map = new int[N][N];
		paper = new int[N+1]; // 색종이 개수
		for(int i=0; i<N; i++) {
			paper[i+1] = 5;
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		dfs(0);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	static void dfs(int cnt) {
		// 색종이 붙이는 데 가장 중요한 왼쪽 상단 첫번째 자리 찾는 루틴
		int str = -1;
		int stc = -1;
		out: for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) {
					str = i;
					stc = j;
					break out;
				}
			}
		}
		
		// 만약 끝까지 탐색했는데도 찾지 못했다면 모든 구역을 다 붙인 것
		if(str == -1 && stc == -1) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		// 크기가 3인 색종이를 붙였다면 3, 2, 1 모두 붙일 수 있기 때문에
		// 그 최대 크기를 찾는 루틴
		int max = 5;
		while(max > 0) {
			boolean flag = true;
			out: for(int i=str; i<str+max; i++) {
				for(int j=stc; j<stc+max; j++) {
					if(!check(str+max-1, stc+max-1) || map[i][j] == 0) {
						flag = false;
						break out;
					}
				}
			}
			if(flag)
				break;
			max--;
		}
		
		// 찾은 최대 크기의 색종이부터 1까지 다 붙여봄
		for(int i=max; i>0; i--) {
			// 해당 크기의 색종이가 남아있다면 그 크기만큼 모두 붙임
			if(paper[i] != 0) {
				for(int r=str; r<str+i; r++) {
					for(int c=stc; c<stc+i; c++) {
						map[r][c] = 0;
					}
				}
				// 붙인 후 해당 색종이 개수를 줄여주고 dfs 
				paper[i]--;
				dfs(cnt+1);
				// 돌아왔다면 색종이 개수 다시 늘려줌
				paper[i]++;
				
				// 붙였던 색종이 떼는 작업
				for(int r=str; r<str+i; r++) {
					for(int c=stc; c<stc+i; c++) {
						map[r][c] = 1;
					}
				}
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
}
