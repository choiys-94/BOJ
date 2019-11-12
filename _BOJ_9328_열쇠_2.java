package boj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _BOJ_9328_열쇠_2 {
	static class Pos{
		int r;
		int c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int N, M;
	static char[][] map;
	static boolean[] key;
	static boolean[][] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new char[N][M];
			for(int i=0; i<N; i++) {
				map[i] = sc.next().toCharArray();
				for(int j=0; j<M; j++) {
					// 가장자리이면서 키일 경우 키 소유 배열에 추가하고 길로 바꿈
					if(i == 0 || j == 0 || i == N-1 || j == M-1) {
						if(Character.isLowerCase(map[i][j])) {
							key[map[i][j]-'a'] = true;
							map[i][j] = '.';
						}
					}
				}
			}
			
			key = new boolean[27];
			
			String in = sc.next();
			// 처음에 가진 열쇠 입력
			if(!in.equals("0")) {
				for(int i=0; i<in.length(); i++) {
					char ch = in.charAt(i);
					key[ch-'a'] = true;
				}
			}
			
			Queue<Pos> queue = new LinkedList<>();
			Queue<Pos> enter = new LinkedList<>();
			HashMap<Integer, ArrayList<Pos>> doors = new HashMap<>();
			// 다시 탐색하면서 가장자리이면서 길이거나, 열쇠를 가진 문짝일 경우 큐에 추가
			// 가장자리가 아니라 그냥 문짝일 경우에는 평지로만 바꿔줌
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(i == 0 || j == 0 || i == N-1 || j == M-1) {
						if(map[i][j] == '.') {
							queue.add(new Pos(i, j));
							enter.add(new Pos(i, j));
						}
						else if(Character.isUpperCase(map[i][j]) && key[map[i][j]-'A']) {
							queue.add(new Pos(i, j));
							enter.add(new Pos(i, j));
							map[i][j] = '.';
						}
						else if(Character.isLowerCase(map[i][j])) {
							key[map[i][j]-'a'] = true;
							map[i][j] = '.';
							queue.add(new Pos(i, j));
							enter.add(new Pos(i, j));
						}
					}
					else if(Character.isUpperCase(map[i][j]) && key[map[i][j]-'A']) {
						map[i][j] = '.';
					}
					else if(Character.isUpperCase(map[i][j])) {
						if(!doors.containsKey(map[i][j]-'A'))
							doors.put(map[i][j]-'A', new ArrayList<Pos>());
						doors.get(map[i][j]-'A').add(new Pos(i, j));
					}
				}
			}
			
			visited = new boolean[N][M];
			
			while(!queue.isEmpty()) {
				Pos q = queue.poll();
				for(int d=0; d<4; d++) {
					int nr = q.r + dr[d];
					int nc = q.c + dc[d];
					if(!check(nr, nc))
						continue;
					if(visited[nr][nc])
						continue;
					if(map[nr][nc] == '*')
						continue;
					if(Character.isLowerCase(map[nr][nc])) {
						key[map[nr][nc]-'a'] = true;
						if(doors.containsKey(map[nr][nc]-'a')) {
							for(Pos door: doors.get(map[nr][nc]-'a')) {
								map[door.r][door.c] = '.';
								visited[door.r][door.c] = true; 
								if(door.r == 0 || door.c == 0 || door.r == N-1 || door.c == M-1)
									queue.add(new Pos(door.r, door.c));
							}
							doors.remove(map[nr][nc]-'a');
						}
						map[nr][nc] = '.';
						visited = new boolean[N][M];
						continue;
					}
					if(Character.isUpperCase(map[nr][nc]) && key[map[nr][nc]-'A']) {
						map[nr][nc] = '.';
					}
					else if(Character.isUpperCase(map[nr][nc]))
						continue;
					visited[nr][nc] = true;
					queue.add(new Pos(nr, nc));
				}
			}
			
			visited = new boolean[N][M];
			int ans = 0;
			queue.clear();
			queue.addAll(enter);
			
			while(!queue.isEmpty()) {
				Pos q = queue.poll();
				for(int d=0; d<4; d++) {
					int nr = q.r + dr[d];
					int nc = q.c + dc[d];
					if(!check(nr, nc))
						continue;
					if(visited[nr][nc])
						continue;
					if(map[nr][nc] == '*')
						continue;
					if(Character.isLowerCase(map[nr][nc])) {
						key[map[nr][nc]-'a'] = true;
						if(doors.containsKey(map[nr][nc]-'a')) {
							for(Pos door: doors.get(map[nr][nc]-'a')) {
								map[door.r][door.c] = '.';
								queue.add(new Pos(door.r, door.c));
								visited[door.r][door.c] = true; 
							}
							doors.remove(map[nr][nc]-'a');
						}
						map[nr][nc] = '.';
						visited = new boolean[N][M];
					}
					if(Character.isUpperCase(map[nr][nc]) && key[map[nr][nc]-'A']) {
						map[nr][nc] = '.';
					}
					else if(Character.isUpperCase(map[nr][nc]))
						continue;
					if(map[nr][nc] == '$') {
						map[nr][nc] = '.';
						ans++;
					}
					visited[nr][nc] = true;
					queue.add(new Pos(nr, nc));
				}
			}
			System.out.println(ans);
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<M;
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
}
