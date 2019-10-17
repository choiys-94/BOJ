package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_3190_뱀_2 {
	static class Snake{
		int r;
		int c;
		Snake(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int N, K, L;
	static int[][] map;
	static int[][] rot;
	static boolean[][] visited;
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine()); // 맵 크기
		K = Integer.parseInt(br.readLine()); // 사과 개수
		map = new int[N+1][N+1];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 9;
		}
		L = Integer.parseInt(br.readLine()); // 변환 횟수
		rot = new int[L][2];
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			rot[i] = new int[2];
			int time = Integer.parseInt(st.nextToken());
			char ch = st.nextToken().charAt(0);
			rot[i][0] = time;
			// 1: 오른쪽, 2: 왼쪽
			if(ch == 'D')
				rot[i][1] = 1;
			else
				rot[i][1] = 2;
		}
		visited = new boolean[N+1][N+1];
		ArrayList<Snake> snake = new ArrayList<>();
		snake.add(new Snake(1, 1));
		// 1,1부터 시작
		visited[1][1] = true;
		int idx = 0;
		int dir = 0;
		int time = 0;
		while(!snake.isEmpty()) {
			time++;
			//머리 끄냄
			Snake q = snake.get(0);
			int nr = q.r + dr[dir];
			int nc = q.c + dc[dir];
			// 벽이거나 몸뚱아리면 끝
			if(!check(nr, nc) || visited[nr][nc])
				break;
			// 머리 옮겨주고
			snake.add(0, new Snake(nr, nc));
			visited[nr][nc] = true;
			// 사과면 꼬리 안없앰
			if(map[nr][nc] != 9) {
				int delr = snake.get(snake.size()-1).r;
				int delc = snake.get(snake.size()-1).c;
				visited[delr][delc] = false;
				snake.remove(snake.size()-1);
			}
			else {
				map[nr][nc] = 0;
			}
			// 회전 명령이 있으면
			if(rot.length > idx && rot[idx][0] == time) {
				// 오른쪽일 때
				if(rot[idx][1] == 1) {
					dir++;
					if(dir == 4)
						dir = 0;
				}
				// 왼쪽일 때
				else {
					dir--;
					if(dir == -1)
						dir = 3;
				}
				idx++;
			}
			
			ans = Math.max(ans, time);
		}
		System.out.println(ans+1);
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static boolean check(int r, int c) {
		return r>=1 && c>=1 && r<=N && c<=N;
	}
}
