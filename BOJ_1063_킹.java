package boj;

import java.util.Scanner;

public class BOJ_1063_킹 {
	static int N;
	static int kingr, kingc, stoner, stonec;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String k = sc.next();
		kingc = k.charAt(0)-'A'+1;
		kingr = k.charAt(1)-'0';
		String s = sc.next();
		stonec = s.charAt(0)-'A'+1;
		stoner = s.charAt(1)-'0';
		
		N = sc.nextInt();
		for(int i=0; i<N; i++) {
			int d = 0;
			String in = sc.next();
			if(in.equals("R"))
				d = 0;
			else if(in.equals("L"))
				d = 1;
			else if(in.equals("B"))
				d = 2;
			else if(in.equals("T"))
				d = 3;
			else if(in.equals("RT"))
				d = 4;
			else if(in.equals("LT"))
				d = 5;
			else if(in.equals("RB"))
				d = 6;
			else if(in.equals("LB"))
				d = 7;
			
			int nkr = kingr + dr[d];
			int nkc = kingc + dc[d];
			if(check(nkr, nkc)) {
				if(nkr == stoner && nkc == stonec) {
					int nsr = stoner + dr[d];
					int nsc = stonec + dc[d];
					if(!check(nsr, nsc)) {
						continue; 
					}
					stoner = nsr;
					stonec = nsc;
					kingr = nkr;
					kingc = nkc;
				}
				else {
					kingr = nkr;
					kingc = nkc;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append((char)(kingc+'A'-1)).append(kingr).append("\n");
		sb.append((char)(stonec+'A'-1)).append(stoner).append("\n");
		System.out.println(sb.toString());
	}
	
	static boolean check(int r, int c) {
		return r>=1 && c>=1 && r<=8 && c<=8;
	}
	
	static int dr[] = {0, 0, -1, 1, 1, 1, -1, -1};
	static int dc[] = {1, -1, 0, 0, 1, -1, 1, -1};
}
