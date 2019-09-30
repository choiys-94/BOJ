package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_2810_컵홀더 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		ArrayList<Character> arr = new ArrayList<>();
		String in = sc.next();
		for(int i=0; i<in.length(); i++) {
			if(in.charAt(i) == 'S') {
				if(arr.isEmpty() || arr.get(arr.size()-1) != '*')
					arr.add('*');
				arr.add('S');
				arr.add('*');
			}
			else if(in.charAt(i) == 'L') {
				if(arr.isEmpty() || arr.get(arr.size()-1) != '*')
					arr.add('*');
				arr.add('L');
				arr.add('L');
				arr.add('*');
				i++;
			}
		}
		
		int cnt = 0;
		for(int i=0; i<arr.size(); i++) {
			if(arr.get(i) == 'S') {
				if(arr.get(i-1) == '*') {
					arr.set(i-1, '.');
					cnt++;
				}
				else if(arr.get(i+1) == '*') {
					arr.set(i+1, '.');
					cnt++;
				}
			}
			else if(arr.get(i) == 'L') {
				if(arr.get(i-1) == '*') {
					arr.set(i-1, '.');
					cnt++;
				}
				else if(arr.get(i+1) == '*') {
					arr.set(i+1, '.');
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
