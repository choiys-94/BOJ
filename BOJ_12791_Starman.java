package boj;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_12791_Starman {
	static class Album{
		int year;
		String name;
		Album(int y, String n){
			year = y;
			name = n;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner("25\r\n" +
				"1967 DavidBowie\r\n" + 
				"1969 SpaceOddity\r\n" + 
				"1970 TheManWhoSoldTheWorld\r\n" + 
				"1971 HunkyDory\r\n" + 
				"1972 TheRiseAndFallOfZiggyStardustAndTheSpidersFromMars\r\n" + 
				"1973 AladdinSane\r\n" + 
				"1973 PinUps\r\n" + 
				"1974 DiamondDogs\r\n" + 
				"1975 YoungAmericans\r\n" + 
				"1976 StationToStation\r\n" + 
				"1977 Low\r\n" + 
				"1977 Heroes\r\n" + 
				"1979 Lodger\r\n" + 
				"1980 ScaryMonstersAndSuperCreeps\r\n" + 
				"1983 LetsDance\r\n" + 
				"1984 Tonight\r\n" + 
				"1987 NeverLetMeDown\r\n" + 
				"1993 BlackTieWhiteNoise\r\n" + 
				"1995 1.Outside\r\n" + 
				"1997 Earthling\r\n" + 
				"1999 Hours\r\n" + 
				"2002 Heathen\r\n" + 
				"2003 Reality\r\n" + 
				"2013 TheNextDay\r\n" + 
				"2016 BlackStar");
		
		int N = sc.nextInt();
		ArrayList<Album> album = new ArrayList<>();
		for(int i=0; i<N; i++) {
			album.add(new Album(sc.nextInt(), sc.next()));
		}
		
		sc = new Scanner(System.in);
		
		N = sc.nextInt();
		ArrayList<Album> result = new ArrayList<>();
		for(int tc=0; tc<N; tc++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			result.clear();
			for(Album data: album) {
				if(data.year >= start && data.year <= end) {
					result.add(data);
				}
			}
			
			System.out.println(result.size());
			for(Album res: result) {
				System.out.println(res.year + " " + res.name);
			}
		}
	}
}
