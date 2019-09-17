package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_16637_괄호추가하기 {
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();	// 식의 길이
		String tmp = sc.next();
		ArrayList<Character> in = new ArrayList<>();	//식 입력 받기
		for(int i=0; i<N; i++) {
			in.add(tmp.charAt(i));
		}
		if(N == 1) {
			System.out.println(in.get(0));
			System.exit(0);
		}
		int[] pos = new int[N/2];	// 연산자 위치
		for(int i=0; i<N/2; i++) {
			pos[i] = i*2+1;		// 연산자 위치 저장
		}
		
		solve(in, pos, new ArrayList<Integer>(), new ArrayList<Integer>(), 0, 'a');
		
		System.out.println(MAX);
	}
	
	static int MAX = Integer.MIN_VALUE;
	
	static void solve(ArrayList<Character> in, int[] pos, ArrayList<Integer> ops, ArrayList<Integer> paren, int idx, char n) {
		if(idx == N/2 || idx == N/2+1) {
			ArrayList<Character> tmp = new ArrayList<>(in);
			
//			for(int i=0; i<ops.size(); i++) {
//				System.out.print(ops.get(i)+" ");
//			}
//			System.out.println();
			
			for(int i=ops.size()-1; i>=0; i--) {
				int t = calc(tmp.get(ops.get(i)-1)-'0', tmp.get(ops.get(i)+1)-'0', tmp.get(ops.get(i)));
				for(int j=0; j<3; j++) {
					tmp.remove(ops.get(i)-1);
				}
				tmp.add(ops.get(i)-1, 'a');
				paren.add(0, t);
			}
			
			int result = Integer.MIN_VALUE;
			int cnt = 0;
			while(tmp.size() != 1) {
				int a, b;
				char c;
				if(tmp.get(0) == 'a') {
					a = paren.get(cnt);
					cnt++;
				}
				else if(tmp.get(0) == 'b') {
					a = result;
				}
				else {
					a = tmp.get(0)-'0';
				}
				if(tmp.get(2) == 'a') {
					b = paren.get(cnt);
					cnt++;
				}
				else {
					b = tmp.get(2)-'0';
				}
				c = tmp.get(1);
				result = calc(a, b, c);
				
				for(int i=0; i<3; i++) {
					tmp.remove(0);
				}
				tmp.add(0, 'b');
			}
//			System.out.println(result);
			MAX = Math.max(MAX, result);
			return;
		}
		
		ops.add(pos[idx]);

		solve(in, pos, new ArrayList<Integer>(ops), new ArrayList<Integer>(paren), idx+2, (char)(n+1));
		ops.remove(ops.size()-1);
		solve(in, pos, new ArrayList<Integer>(ops), new ArrayList<Integer>(paren), idx+1, (char)(n+1));
	}
	
	static int calc(int a, int b, char c) {
		switch(c) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		}
		return 0;
	}
}
