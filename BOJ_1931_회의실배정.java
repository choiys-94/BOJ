package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.omg.CORBA.Principal;

public class BOJ_1931_회의실배정 {
	static class Meeting implements Comparable<Meeting>{
		int startTime;
		int endTime;
		Meeting(int st, int et){
			startTime = st;
			endTime = et;
		}
		@Override
		public int compareTo(Meeting o) {
			// TODO Auto-generated method stub
			if(endTime == o.endTime) {
				return startTime - o.startTime;
			}
			return endTime - o.endTime;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // <= 100000
		
		PriorityQueue<Meeting> queue = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			queue.add(new Meeting(sc.nextInt(), sc.nextInt()));
		}
		int cnt = 0;
		int lastFinishedTime = 0;
		while(!queue.isEmpty()) {
			Meeting mt = queue.poll();
			if(mt.startTime >= lastFinishedTime) {
				cnt++;
				lastFinishedTime = mt.endTime;
			}
		}
		
//		ArrayList<Meeting> list = new ArrayList<>(N);
//		for(int i=0; i<N; i++) {
//			list.add(new Meeting(sc.nextInt(), sc.nextInt()));
//		}
//		Collections.sort(list);
//		//배정된 회의의 갯수
//		int cnt = 0;
//		//마지막 회의가 끝난 시간
//		int lastFinishedTime = 0;
//		//회의를 하나씩 검사하면서
//		for(Meeting meeting: list) {
//			//해당 회의의 시작시간이 마지막에 끝난 회의보다 더 늦으면 추가 가능한 회의
//			if(meeting.startTime >= lastFinishedTime) {
//				//회의 하나 세고
//				cnt++;
//				//해당 회의의 끝나는 시간을 마지막 종료시간으로 업데이트
//				lastFinishedTime = meeting.endTime;
//			}
//		}
		System.out.println(cnt);
	}
}
