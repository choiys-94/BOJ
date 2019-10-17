package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _BOJ_2579_계단오르기 {
	static class Stair{
		int score;
		int step;
		Stair(int score, int step){
			this.score = score;
			this.step = step;
		}
	}
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Stair[] stair = new Stair[N+1];
		int[] arr = new int[N+1];
		stair[0] = new Stair(0, 0);
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			stair[i] = new Stair(0, 0);
			if(stair[i-1].step+1 < 3) {
				stair[i].score = stair[i-1].score + arr[i];
				stair[i].step = stair[i-1].step+1;
			}
			if(i-2 >= 0 && stair[i-2].score + arr[i] >= stair[i].score) {
				stair[i].score = stair[i-2].score + cur;
				stair[i].step = 1;
			}
			else if(stair[i].score == 0){
				stair[i].score = stair[i-2].score + cur;
				stair[i].step = 1;
			}
			System.out.println(stair[i].score);
		}
		
		System.out.println(stair[N].score);
	}
}
