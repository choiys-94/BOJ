package boj;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_16236_아기상어_2 {
	static class Shark implements Comparable<Shark>{
		int r;
		int c;
		int size;
		int time;
		int eat;
		Shark(int r, int c, int size, int time, int eat){
			this.r = r;
			this.c = c;
			this.size = size;
			this.time = time;
			this.eat = eat;
		}
		@Override
		public int compareTo(Shark o) {
			if(this.time == o.time) {
				if(this.r == o.r)
					return this.c - o.c;
				return this.r - o.r;
			}
			return this.time - o.time;
		}
	}
	static int N;
	static int[][] map;
	static int str, stc;
	static boolean[][] visited;
	static int ans = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 9) {
					str = i;
					stc = j;
					map[i][j] = 0;
				}
			}
		}
		
		Queue<Shark> shark = new LinkedList<>();
		shark.add(new Shark(str, stc, 2, 0, 0));
		while(!shark.isEmpty()) {
			Shark s = shark.poll();
			Queue<Shark> queue = new LinkedList<>();
			queue.add(s);
			visited = new boolean[N][N];
			visited[s.r][s.c] = true;
			PriorityQueue<Shark> toEat = new PriorityQueue<>();
			while(!queue.isEmpty()) {
				Shark q = queue.poll();
				for(int i=0; i<4; i++) {
					int nr = q.r + dr[i];
					int nc = q.c + dc[i];
					if(!check(nr, nc) || visited[nr][nc])
						continue;
					// 물고기 사이즈가 나보다 작으면 먹을 리스트에 추가
					if(map[nr][nc] != 0 && (map[nr][nc] < q.size)) {
						toEat.add(new Shark(nr, nc, map[nr][nc], q.time+1, 0));
						continue;
					}
					// 먹을게 없으면 이동
					else if(map[nr][nc] <= q.size) {
						queue.add(new Shark(nr, nc, q.size, q.time+1, q.eat));
						visited[nr][nc] = true;
					}
				}
			}
			// 먹을게 없으면 끝
			if(toEat.isEmpty())
				break;
			// 먹고 맵에서 지움. 커지면 커지고.
			Shark e = toEat.poll();
			s.eat++;
			if(s.eat == s.size) {
				s.size++;
				s.eat = 0;
			}
			map[e.r][e.c] = 0;
			// 다음 큐에 추가
			shark.add(new Shark(e.r, e.c, s.size, e.time, s.eat));
			ans = Math.max(ans, e.time);
		}
		
		System.out.println(ans);
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}
}
