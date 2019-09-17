package boj;

import java.util.Scanner;

public class BOJ_1924_2007년 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//			월  화  수  목 금  토 일
		String[] day = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
		
		// 3 14 : 31 + 28 + 13 = 59 + 13 = 72 % 7 = 2
		int inMonth = sc.nextInt() - 1;
		int inDay = sc.nextInt() - 1;
		int result = 0;
		for(int i=1; i<=inMonth; i++) {
			switch(i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				result += 31;
				break;
			case 2:
				result += 28;
				break;
			default:
				result += 30;
				break;
			}
		}
		result += inDay;
		result %= 7;
		System.out.println(day[result]);
	}
}
