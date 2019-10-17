package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _BOJ_5373_큐빙 {
	static int N;
	static int[][][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			map = new int[6][6][6];
			// 0: 위쪽, 1: 아래쪽, 2: 앞쪽, 3: 뒤쪽, 4: 왼쪽, 5: 오른쪽 
			for(int k=0; k<6; k++) {
				for(int i=0; i<6; i++) {
					for(int j=0; j<6; j++) {
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
		}
	}
	/*
1. 앞면
	스왑 : 윗면 아래쪽 -> 오른면 왼쪽 -> 아랫면 위쪽 -> 왼면 오른쪽
	
2. 윗면
	스왑 : 뒷면 위쪽 -> 오른면 위쪽 -> 앞면 위쪽 -> 왼면 위쪽

3. 오른면
	스왑 : 앞면 오른쪽 -> 윗면 오른쪽 -> 뒷면 왼쪽 -> 아랫면 오른쪽

4. 왼면
	스왑 : 윗면 왼쪽 -> 앞면 왼쪽 -> 뒷면 왼쪽 -> 뒷면 오른쪽

5. 뒷면
	스왑 : 윗면 위쪽 -> 왼면 왼쪽 -> 아랫면 아래쪽 -> 오른면 오른쪽

6. 아랫면
	스왑 : 앞면 아래쪽 -> 오른면 아래쪽 -> 뒷면 아래쪽 -> 왼면 아래쪽
	 */
	public static void play(int side, int dir) {
		
	}
	
	public static void rotate(int side, int dir) {
		// 시계방향
		if(dir == 1) {
			int ta = map[side][0][0];
			int tb = map[side][1][0];
			int tc = map[side][2][0];
			
			int tmp = map[side][0][3];
			map[side][0][3] = map[side][0][0];
			map[side][1][3] = map[side][0][1];
			map[side][2][3] = map[side][0][2];
			
			
		}
	}
	
}
