package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_5397_키로거 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<Character> mainStr = new Stack<>();
		Stack<Character> tmp = new Stack<>();
		for(int tc = 1; tc <= T; tc++) {
			String in = br.readLine();
			int idx = 0;
			mainStr.clear();
			tmp.clear();
			for(int i=0; i<in.length(); i++) {
				char ch = in.charAt(i);
				switch(ch) {
				case '-':
					if(idx > 0) {
						mainStr.pop();
						idx--;
					}
					break;
				case '<':
					if(!mainStr.isEmpty()) {
						tmp.add(mainStr.pop());
						idx--;
					}
					break;
				case '>':
					if(!tmp.isEmpty()) {
						mainStr.add(tmp.pop());
						idx++;
					}
					break;
				default:
					mainStr.add(ch);
					idx++;
					break;
				}
			}
			while(!tmp.isEmpty())
				mainStr.add(tmp.pop());
			
			StringBuilder res = new StringBuilder();
			while(!mainStr.isEmpty()) {
				res.append(mainStr.pop());
			}
			sb.append(res.reverse().toString()).append("\n");
		}
		System.out.println(sb.toString());
	}
}
