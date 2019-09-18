package boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class BOJ_1159_농구경기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		HashMap<String, Integer> arr = new HashMap<>();
		int N = sc.nextInt();
		for(int i=0; i<N; i++) {
			String in = sc.next();
			if(in.length() < 2)
				continue;
			
			if(arr.containsKey(in.substring(0,1))) {
				arr.put(in.substring(0,1), arr.get(in.substring(0,1))+1);
			}
			else {
				arr.put(in.substring(0,1), 1);
			}
		}
		ArrayList<String> result = new ArrayList<>();
		for(String s: arr.keySet()) {
			if(arr.get(s) >= 5)
				result.add(s);
		}
		
		Collections.sort(result);
		
		if(result.size() == 0)
			System.out.println("PREDAJA");
		else {
			for(String s: result)
				System.out.print(s);
		}
	}
}
