package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_17143_낚시왕2 {
	static class Shark implements Comparable<Shark>{
		int r;
		int c;
		int s;
		int d;
		int z;
		Shark(int r, int c, int s, int d, int z){
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		@Override
		public int compareTo(Shark o) {
			if(Math.abs(man - this.c) == Math.abs(man - o.c))
				return this.r - o.r;
			return Math.abs(man - this.c) - Math.abs(man - o.c);
		}
	}
	static int R, C, M;
	static int man;
	static int result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ArrayList<Shark> shark = new ArrayList<>();
		int[][] map = new int[R+1][C+1];
		for(int i=1; i<=R; i++) {
			Arrays.fill(map[i], -1);
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if(d <= 2) {
				s %= (R-1)*2;
			}
			else {
				s %= (C-1)*2;
			}
			int z = Integer.parseInt(st.nextToken());
			shark.add(new Shark(r, c, s, d, z));
		}
		man = 0;
		ArrayList<Integer> toDel = new ArrayList<>();
		for(int i=1; i<=C; i++) {
			// 상어 없으면 나감
			if(shark.isEmpty())
				break;
			// 낚시꾼 이동
			man++;
			//임시배열 초기화
			toDel.clear();
			//맵 초기화
			for(int j=1; j<=R; j++)
				Arrays.fill(map[j], -1);
			// 낚시꾼과 같은 라인에 있는 상어를 맨 앞으로 가져옴
			Collections.sort(shark);
			// 낚시꾼과 같은 라인에 있다면 상어 잡고 크기 더해줌
			if(shark.get(0).c == man) {
				result += shark.get(0).z;
				shark.remove(0);
			}
			
			// 상어 없으면 나감
			if(shark.isEmpty())
				break;
			
			//상어 이동
			for(int j=shark.size()-1; j>=0; j--) {
				Shark shk = shark.get(j);
				int or = shk.r;
				int oc = shk.c;
				for(int k=0; k<shk.s; k++) {
					if(!check(shk.r+dr[shk.d], shk.c+dc[shk.d])) {
						switch(shk.d) {
						case 1:
							shk.d = 2;
							break;
						case 2:
							shk.d = 1;
							break;
						case 3:
							shk.d = 4;
							break;
						case 4:
							shk.d = 3;
							break;
						}
					}
					shk.r += dr[shk.d];
					shk.c += dc[shk.d];
				}
				if(map[shk.r][shk.c] == -1) {
					map[shk.r][shk.c] = j; 
				}
				else {
					if(shark.get(map[shk.r][shk.c]).z > shk.z) {
						toDel.add(j);
					}
					else {
						toDel.add(map[shk.r][shk.c]);
						map[shk.r][shk.c] = j; 
					}
				}
			}
			Collections.sort(toDel, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
			for(int s: toDel) {
				shark.remove(s);
			}
		}
		System.out.println(result);
	}
	
	static boolean check(int r, int c) {
		return r>=1 && c>=1 && r<=R && c<=C;
	}
	
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, 1, -1};
}
