package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935_문자열폭발 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String in = br.readLine();
		String token = br.readLine();
		Stack<Character> mainStr = new Stack<>();
		Stack<Character> tmp = new Stack<>();
		int tSize = token.length();
		int size = in.length();
		int idx = in.length()-1;
		for(int i=0; i<in.length(); i++) {
			mainStr.add(in.charAt(i));
		}
		while(!mainStr.isEmpty()) {
			int tIdx = 0;
			boolean flag = true;
			for(int i=tSize-1; i>=0; i--) {
				if(!mainStr.isEmpty() && mainStr.peek() == token.charAt(i)) {
					tIdx++;
					tmp.add(mainStr.pop());
				}
				else {
					flag = false;
					break;
				}
			}
			if(flag) {
				int ttIdx = tIdx;
				while(ttIdx > 0) {
					ttIdx--;
					tmp.pop();
				}
				ttIdx = tIdx;
				while(ttIdx > 0 && !tmp.isEmpty()) {
					ttIdx--;
					mainStr.add(tmp.pop());
				}
			}
			else {
				if(tIdx == 0)
					tmp.add(mainStr.pop());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!tmp.isEmpty()) {
			sb.append(tmp.pop());
		}
		if(sb.length() == 0)
			System.out.println("FRULA");
		else
			System.out.println(sb.toString());
	}
}