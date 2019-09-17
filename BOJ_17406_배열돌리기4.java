package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_17406_배열돌리기4 {
	static int col_st;
	static int col_end;
	static int row_st;
	static int row_end;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static ArrayList<Integer[]> info;
	static int N, M;
	static int[][] arr;
	static int[][] tmp_arr;
	static ArrayList<Integer> result = new ArrayList<>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		int K = sc.nextInt();
		int[] sel = new int[K];
		info = new ArrayList<>();
		arr = new int[N + 1][M + 1];
		tmp_arr = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		for (int k = 0; k < K; k++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int s = sc.nextInt();
			Integer[] tmp = { r, c, s };
			info.add(tmp);
			sel[k] = k;
		}
		perm(sel, 0);
		
		Collections.sort(result);
		System.out.println(result.get(0));
	}

	static boolean check(int x, int y) {
		return x >= col_st && y >= row_st && x <= col_end && y <= row_end;
	}

	static void perm(int[] sel, int idx) {
		if(idx == sel.length) {
//			System.out.println(Arrays.toString(sel));
			for(int j=1; j<=N; j++) {
				System.arraycopy(arr[j], 1, tmp_arr[j], 1, M);
			}
			for(int i=0; i<sel.length; i++) {
//				System.out.println(info.get(i)[0]+" "+info.get(i)[1]+" "+info.get(i)[2]);
				col_st = info.get(sel[i])[0] - info.get(sel[i])[2];
				row_st = info.get(sel[i])[1] - info.get(sel[i])[2];
				col_end = info.get(sel[i])[0] + info.get(sel[i])[2];
				row_end = info.get(sel[i])[1] + info.get(sel[i])[2];
				solve();
			}
			
			int min = 999999999;
			for (int i = 1; i <= N; i++) {
				int sum = 0;
				for (int j = 1; j <= M; j++) {
					sum += arr[i][j];
				}
				min = Math.min(min, sum);
			}
			result.add(min);
			
			for(int j=1; j<=N; j++) {
				System.arraycopy(tmp_arr[j], 1, arr[j], 1, M);
			}
			return;
		}
		
		for(int i=idx; i<sel.length; i++) {
			swap(sel, idx, i);
			perm(sel, idx+1);
			swap(sel, idx, i);
		}
	}
	
	static void swap(int[] sel, int idx, int i) {
		int tmp = sel[idx];
		sel[idx] = sel[i];
		sel[i] = tmp;
	}
	
	static void solve() {
		int x = col_st;
		int y = row_st;
		while (col_end != col_st) {
			int tmp = arr[x][y];
			int dir = 0;
			while (dir <= 3) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if (check(nx, ny)) {
					int t = arr[nx][ny];
					arr[nx][ny] = tmp;
					tmp = t;
					x = nx;
					y = ny;
				} else {
					dir++;
				}
			}
			col_st++;
			col_end--;
			row_st++;
			row_end--;
			x = col_st;
			y = row_st;
//			for (int i = 1; i <= N; i++) {
//				for (int j = 1; j <= M; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("-------------------------------------");
		}
	}
}
