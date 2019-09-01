package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_11723_집합 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		ArrayList<Integer> arr = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		int in = -1;
		for(int i=0; i<N; i++) {
			switch(sc.next()) {
			case "add":
				in = sc.nextInt();
				if(!arr.contains(in))
					arr.add(in);
				break;
			case "remove":
				in = sc.nextInt();
				if(arr.contains(in)) {
					arr.remove(arr.indexOf(in));
				}
				break;
			case "check":
				in = sc.nextInt();
				if(arr.contains(in)) {
					arr.get(arr.indexOf(in));
					sb.append(1).append("\n");
				}
				else
					sb.append(0).append("\n");
				break;
			case "toggle":
				in = sc.nextInt();
				if(arr.contains(in)) {
					arr.remove(arr.indexOf(in));
				}
				else {
					arr.add(in);
				}
				break;
			case "all":
				arr.clear();
				for(int j=1; j<=20; j++) {
					arr.add(j);
				}
				break;
			case "empty":
				arr.clear();
				break;
			default:
				break;
			}
		}
		System.out.println(sb.toString());
	}
}
