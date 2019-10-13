package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_17472_다리만들기2 {
	static int R, C;
	static int[][] map;
	static boolean[][] visited;
	/**
	 * Pos
	 * - 좌표 클래스
	 * r : 세로
	 * c : 가로
	 */
	static class Pos{
		int r;
		int c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	/**
	 * Isl
	 * - 섬 클래스
	 * idx : 섬 인덱스(번호)
	 * pos : 좌표가 담긴 리스트
	 */
	static class Isl{
		int idx;
		ArrayList<Pos> pos = new ArrayList<>();
		Isl(int r, int c){
			this.pos.add(new Pos(r, c));
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt(); // 1 ≤ N, M ≤ 10
		C = sc.nextInt(); // 1 ≤ N, M ≤ 10
		// 2 ≤ 섬의 개수 ≤ 6
		map = new int[R][C];
		visited = new boolean[R][C];
		
		isl = new ArrayList<>();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				// 섬이면서 방문하지 않았다면
				if(map[i][j] != 0 && visited[i][j] == false) {
					// 해당 자리가 섬의 기준점
					isl.add(new Isl(i, j));
					// 섬의 인덱스 할당(0은 바다이니 1부터 꽂음)
					// 맵에 섬 인덱스 표시
					// 방문체크 하고, 섬 찾는 dfs 함수(findIsl) 기
					isl.get(islCnt).idx = islCnt+1;
					map[i][j] = islCnt+1;
					visited[i][j] = true;
					findIsl(i, j);
					// 이번 섬은 끝났으니 다음 섬 인덱스를 잡아줌
					islCnt++;
				}
			}
		}

		adj = new int[islCnt+1][islCnt+1];
		parents = new int[islCnt+1];
		makeSet();

		// 섬 개수만큼 반복 돌면서 인접행렬 생성
		for(int i=0; i<islCnt; i++) {
			adjSet(isl.get(i));
		}
		
		int cnt = 0;
		int min = Integer.MAX_VALUE;
		int res = 0;
		
		// N-1개의 간선을 찾을때까지 반복
		while(cnt < islCnt-1) {
			int r=-1, c=-1;
			// 섬 개수만큼 반복
			for(int i=1; i<=islCnt; i++) {
				for(int j=i+1; j<=islCnt; j++) {
					// 인접행렬에서 최소값 찾기
					if(adj[i][j] != 0 && min > adj[i][j]) {
						min = adj[i][j];
						r = i;
						c = j;
					}
				}
			}
			// 최소값을 찾았다면
			if(min != Integer.MAX_VALUE && r != -1 && c != -1) {
				// 두 섬의 부모들을 찾아서
				int a = findSet(r);
				int b = findSet(c);
				// 인접행렬의 값을 0으로 변경(방문 체크 대신 함)
				adj[r][c] = 0;
				// 이미 이어져있으면 패스
				if(a == b) {
					min = Integer.MAX_VALUE;
					continue;
				}
				else {
					// 이어져있지 않으면 두 섬 이어줌
					union(a, b);
					res += min;
					cnt++;
					min = Integer.MAX_VALUE;
				}
			}
			else
				break;
		}
		
		if(cnt != islCnt-1)
			System.out.println(-1);
		else
			System.out.println(res);
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static ArrayList<Isl> isl;
	static int islCnt;
	static int[] parents;
	static int[][] adj;
	
	/**
	 * adjSet
	 * - 인접 행렬 만들기
	 * @param isl 섬 리스트(1개)
	 */
	static void adjSet(Isl isl) {
		// 섬의 좌표 개수만큼 반복
		for(int i=0; i<isl.pos.size(); i++) {
			// 사방 탐색
			for(int d=0; d<dr.length; d++) {
				int nr = isl.pos.get(i).r;
				int nc = isl.pos.get(i).c;
				int cnt = -1; // 섬과 섬 사이의 거리(다리 길이)
				while(true) {
					nr += dr[d];
					nc += dc[d];
					cnt++;
					// 맵 안에 있고
					// 탐색하는 부분이 같은 섬이면 나가리
					if(check(nr, nc) && map[nr][nc] == isl.idx)
						break;
					// 탐색하는 부분이 다른 섬인데 거리가 2보다 작으면 나가리
					else if(check(nr, nc) && map[nr][nc] != isl.idx && map[nr][nc] != 0 && cnt < 2)
						break;
					// 탐색하는 부분이 다른 섬이고 거리가 2 이상일 경우
					else if(check(nr, nc) && map[nr][nc] != isl.idx && map[nr][nc] != 0 && cnt >= 2) {
						// 인접행렬이 0이거나 현재 거리보다 큰 값을 가지고 있을 때 현재 거리 꽂음
						if(adj[isl.idx][map[nr][nc]] == 0 || adj[isl.idx][map[nr][nc]] > cnt) {
							adj[isl.idx][map[nr][nc]] = cnt;
							adj[map[nr][nc]][isl.idx] = cnt;
						}
						break;
					}
					// 맵 밖이면 나가리
					else if(!check(nr, nc)) {
						break;
					}
				}
			}
		}
	}
	
	/**
	 * findIsl
	 * - 섬 찾기(dfs)
	 * @param r 세로 기준점
	 * @param c 가로 기준점
	 */
	static void findIsl(int r, int c) {
		// 사방 탐색
		for(int i=0; i<dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 경계를 벗어나지 않고 && 방문하지 않았으며 && 바다가 아닐 경우
			if(check(nr, nc) && visited[nr][nc] == false && map[nr][nc] != 0) {
				// 방문 체크, 맵에 섬 번호 표시, 맵 리스트에 꽂아넣고, 재귀
				visited[nr][nc] = true;
				map[nr][nc] = islCnt+1;
				isl.get(islCnt).pos.add(new Pos(nr, nc));
				findIsl(nr, nc);
			}
		}
	}
	
	/**
	 * makeSet
	 * - 1 ~ 섬의 개수만큼 초기화 
	 */
	static void makeSet() {
		for(int i=1; i<=islCnt; i++) {
			parents[i] = i;
		}
	}
	
	/**
	 * findSet
	 * - 엄빠 찾기
	 * @param x 부모를 찾고자 하는 섬의 번호
	 * @return 최상위 부모
	 */
	static int findSet(int x) {
		if(x == parents[x])
			return x;
		parents[x] = findSet(parents[x]);
		return parents[x];
	}
	
	/**
	 * union
	 * - x와 y의 최상위 부모들을 묶어줌
	 * @param x 섬의 번호 1
	 * @param y 섬의 번호 2
	 */
	static void union(int x, int y) {
		int px = findSet(parents[x]);
		int py = findSet(parents[y]);
		if(px != py) {
			parents[py] = parents[px];
		}
	}
	
	/**
	 * check
	 * - 경계 체크
	 * @param r 세로
	 * @param c 가로
	 * @return
	 */
	static boolean check(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
}
