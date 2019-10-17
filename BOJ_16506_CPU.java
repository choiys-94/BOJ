package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_16506_CPU {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		HashMap<String, String> op = new HashMap<>();
		op.put("ADD", "000000");
		op.put("ADDC", "000010");
		op.put("SUB", "000100");
		op.put("SUBC", "000110");
		op.put("MOV", "001000");
		op.put("MOVC", "001010");
		op.put("AND", "001100");
		op.put("ANDC", "001110");
		op.put("OR", "010000");
		op.put("ORC", "010010");
		op.put("NOT", "010100");
		op.put("MULT", "011000");
		op.put("MULTC", "011010");
		op.put("LSFTL", "011100");
		op.put("LSFTLC", "011110");
		op.put("LSFTR", "100000");
		op.put("LSFTRC", "100010");
		op.put("ASFTR", "100100");
		op.put("ASFTRC", "100110");
		op.put("RL", "101000");
		op.put("RLC", "101010");
		op.put("RR", "101100");
		op.put("RRC", "101110");
		HashMap<String, String> reg = new HashMap<>();
		reg.put("0", "000");
		reg.put("1", "001");
		reg.put("2", "010");
		reg.put("3", "011");
		reg.put("4", "100");
		reg.put("5", "101");
		reg.put("6", "110");
		reg.put("7", "111");
		reg.put("8", "1000");
		reg.put("9", "1001");
		reg.put("10", "1010");
		reg.put("11", "1011");
		reg.put("12", "1100");
		reg.put("13", "1101");
		reg.put("14", "1110");
		reg.put("15", "1111");
		HashMap<String, String> con = new HashMap<>();
		con.put("0", "0000");
		con.put("1", "0001");
		con.put("2", "0010");
		con.put("3", "0011");
		con.put("4", "0100");
		con.put("5", "0101");
		con.put("6", "0110");
		con.put("7", "0111");
		con.put("8", "1000");
		con.put("9", "1001");
		con.put("10", "1010");
		con.put("11", "1011");
		con.put("12", "1100");
		con.put("13", "1101");
		con.put("14", "1110");
		con.put("15", "1111");
		
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc = 0; tc < N; tc++) {
			st = new StringTokenizer(br.readLine());
			String o = st.nextToken();
			sb.append(op.get(o));
			for(int i=0; i<2; i++) {
				sb.append(reg.get(st.nextToken()));
			}
			if(o.charAt(o.length()-1) == 'C')
				sb.append(con.get(st.nextToken()));
			else {
				sb.append(reg.get(st.nextToken()));
				sb.append("0");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
