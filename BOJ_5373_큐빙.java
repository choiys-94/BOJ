package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_5373_큐빙 {
	static int N;
	static int[][][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			map = new int[6][3][3];

			for(int k=0; k<6; k++) {
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						map[k][i][j] = k+1;
					}
				}
			}
			N = Integer.parseInt(br.readLine().trim());
			st = new StringTokenizer(br.readLine().trim());
			for(int i=0; i<N; i++) {
				String in = st.nextToken();
				// dir 1은 시계, 2는 반시계
				int side = -1, dir = -1;
				switch(in.charAt(0)) {
				case 'U':
					side = 0;
					break;
				case 'D':
					side = 1;
					break;
				case 'F':
					side = 2;
					break;
				case 'B':
					side = 3;
					break;
				case 'L':
					side = 4;
					break;
				case 'R':
					side = 5;
					break;
				}
				if(in.charAt(1) == '+')
					dir = 1;
				else
					dir = 2;
				
				play(side, dir);
			}
			HashMap<Integer, Character> hm = new HashMap<>();
			hm.put(1, 'w');
			hm.put(2, 'y');
			hm.put(3, 'r');
			hm.put(4, 'o');
			hm.put(5, 'g');
			hm.put(6, 'b');
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					System.out.print(hm.get(map[0][i][j]));
				}
				System.out.println();
			}
		}
	}
	// 0: 위쪽, 1: 아래쪽, 2: 앞쪽, 3: 뒤쪽, 4: 왼쪽, 5: 오른쪽 
	public static void play(int side, int dir) {
		switch(side) {
		//0. 윗면 스왑 : 뒷면 위쪽 -> 오른면 위쪽 -> 앞면 위쪽 -> 왼면 위쪽
		case 0:
			if(dir == 1) {
				swap(map[5][0], map[3][0]);
				swap(map[3][0], map[4][0]);
				swap(map[4][0], map[2][0]);
			}
			else {
				swap(map[3][0], map[5][0]);
				swap(map[5][0], map[2][0]);
				swap(map[2][0], map[4][0]);				
			}
			break;
		//1. 아랫면 스왑 : 앞면 아래쪽 -> 오른면 아래쪽 -> 뒷면 아래쪽 -> 왼면 아래쪽
		case 1:
			if(dir == 1) {
				swap(map[5][2], map[2][2]);
				swap(map[2][2], map[4][2]);
				swap(map[4][2], map[3][2]);
			}
			else {
				swap(map[2][2], map[5][2]);
				swap(map[5][2], map[3][2]);
				swap(map[3][2], map[4][2]);
			}
			break;
		//2. 앞면 스왑 : 윗면 아래쪽 -> 오른면 왼쪽 -> 아랫면 위쪽 -> 왼면 오른쪽
		case 2:
			rotate(5, 2);
			rotate(4, 1);
			rotate(1, 1);
			rotate(1, 1);
			if(dir == 1) {
				swap(map[5][2], map[0][2]);
				swap(map[0][2], map[4][2]);
				swap(map[4][2], map[1][2]);
			}
			else {
				swap(map[0][2], map[5][2]);
				swap(map[5][2], map[1][2]);
				swap(map[1][2], map[4][2]);				
			}
			rotate(1, 2);
			rotate(1, 2);
			rotate(5, 1);
			rotate(4, 2);
			break;
		//3. 뒷면 스왑 : 윗면 위쪽 -> 왼면 왼쪽 -> 아랫면 아래쪽 -> 오른면 오른쪽
		case 3:
			rotate(4, 2);
			rotate(5, 1);
			rotate(0, 1);
			rotate(0, 1);
			if(dir == 1) {
				swap(map[4][2], map[0][2]);
				swap(map[0][2], map[5][2]);
				swap(map[5][2], map[1][2]);
			}
			else {
				swap(map[0][2], map[4][2]);
				swap(map[4][2], map[1][2]);
				swap(map[1][2], map[5][2]);
			}
			rotate(0, 2);
			rotate(0, 2);
			rotate(4, 1);
			rotate(5, 2);
			break;
		//4. 왼면 스왑 : 윗면 왼쪽 -> 앞면 왼쪽 -> 아랫 왼쪽 -> 뒷면 오른쪽
		case 4:
			rotate(0, 2);
			rotate(2, 2);
			rotate(1, 2);
			rotate(3, 1);
			if(dir == 1) {
				swap(map[0][2], map[3][2]);
				swap(map[3][2], map[1][2]);
				swap(map[1][2], map[2][2]);
			}
			else {
				swap(map[0][2], map[2][2]);
				swap(map[2][2], map[1][2]);
				swap(map[1][2], map[3][2]);
			}
			rotate(0, 1);
			rotate(2, 1);
			rotate(1, 1);
			rotate(3, 2);
			break;
		//5. 오른면 스왑 : 앞면 오른쪽 -> 윗면 오른쪽 -> 뒷면 왼쪽 -> 아랫면 오른쪽
		case 5:
			rotate(2, 1);
			rotate(0, 1);
			rotate(3, 2);
			rotate(1, 1);
			if(dir == 1) {
				swap(map[0][2], map[2][2]);
				swap(map[2][2], map[1][2]);
				swap(map[1][2], map[3][2]);				
			}
			else {
				swap(map[2][2], map[0][2]);
				swap(map[0][2], map[3][2]);
				swap(map[3][2], map[1][2]);
			}
			rotate(2, 2);
			rotate(0, 2);
			rotate(3, 1);
			rotate(1, 0);
			break;
		}
		rotate(side, dir);
//		for(int k=0; k<6; k++) {
//			for(int i=0; i<3; i++) {
//				for(int j=0; j<3; j++) {
//					System.out.print(map[k][i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println("-------------------");
//		}
	}
	
	// 0: 우 1: 하 2:좌 3:상
	public static void swap(int[] s1, int[] s2) {
		int[] tmp = new int[3];
		System.arraycopy(s1, 0, tmp, 0, 3);
		System.arraycopy(s2, 0, s1, 0, 3);
		System.arraycopy(tmp, 0, s2, 0, 3);
	}
	
	public static void rotate(int side, int dir) {
		// 시계방향
		int[][] tmp = new int[3][3];
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(dir == 1)
					tmp[j][2-i] = map[side][i][j];
				else
					tmp[2-j][i] = map[side][i][j];
			}
		}
		
		for(int i=0; i<3; i++) {
			System.arraycopy(tmp[i], 0, map[side][i], 0, tmp[i].length);
		}
	}
	
}
