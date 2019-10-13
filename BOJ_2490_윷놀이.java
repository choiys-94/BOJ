package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2490_윷놀이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int[] flag = new int[2];
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(flag, 0);
			for(int j=0; j<4; j++) {
				if(Integer.parseInt(st.nextToken()) == 0)
					flag[0]++;
			}
			switch(flag[0]) {
			case 0:
				sb.append("E");
				break;
			case 1:
				sb.append("A");
				break;
			case 2:
				sb.append("B");
				break;
			case 3:
				sb.append("C");
				break;
			case 4:
				sb.append("D");
				break;
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
