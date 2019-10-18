package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2680_QR {
	static final int N = 19;
	static final String alpha = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:";
	static String binary;
	static String str;
	static int num;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		String in = new String();
		
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			in = br.readLine().trim();
			binary = new String();
			str = new String();
			for(int i=0; i<N; i++) {
				// 먼저, 2자리씩 잘라서 16진수를 2진수로 만들어주자
				String tmp = Integer.toBinaryString(Integer.parseInt(in.substring(2*i, 2*i+2), 16));
				// 바꾼 2진수 left padding
				if(tmp.length() != 8) {
					int size = tmp.length();
					for(int j=0; j<8-size; j++) {
						tmp = "0" + tmp;
					}
				}
				// 2진수 문자열 완성!
				binary += tmp;
			}
			num = 0;
			out: while(binary.length() > 0) {
				if(binary.length() < 4) {
					break;
				}
				String mode = binary.substring(0,4);
				binary = binary.substring(4);
				switch(mode) {
				case "0001":
					numeric();
					break;
				case "0010":
					alphanumeric();
					break;
				case "0100":
					octet();
					break;
				case "1000":
					kanji();
					break;
				case "0000":
					break out;
				default:
					break out;
				}
			}
			sb.append(num).append(" ").append(str).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void kanji() {
		int countBit = Integer.parseInt(binary.substring(0, 8), 2);
		binary = binary.substring(8);
		
		num += countBit;
		
		// 올 13비트
		for(int i=0; i<countBit; i++) {
			int token = Integer.parseInt(binary.substring(0, 13), 2);
			if(token >= 0x20 && token <= 0x7e) {
				if((char)token == '\\' || (char)token == '#'){
					str += '\\';
				}
				str += (char)token;
			}
			else {
				String hex = Integer.toHexString(token).toUpperCase();
				if(hex.length() < 4) {
					int size = hex.length();
					for(int j=0; j<4-size; j++)
						hex = "0" + hex;
				}
				str += "#" + hex;
			}
			binary = binary.substring(13);
		}
	}
	
	static void octet() {
		int countBit = Integer.parseInt(binary.substring(0, 8), 2);
		binary = binary.substring(8);
		
		num += countBit;
		
		// 올 8비트
		for(int i=0; i<countBit; i++) {
			int token = Integer.parseInt(binary.substring(0, 8), 2);
			if(token >= 0x20 && token <= 0x7e) {
				if((char)token == '\\' || (char)token == '#'){
					str += '\\';
				}
				str += (char)token;
			}
			else {
				String hex = Integer.toHexString(token).toUpperCase();
				if(hex.length() < 2)
					hex = "0" + hex;
				
				str += "\\" + hex;
			}
			binary = binary.substring(8);
		}
	}
	
	static void alphanumeric() {
		int countBit = Integer.parseInt(binary.substring(0, 9), 2);
		binary = binary.substring(9);
		
		num += countBit;
		
		// 2자리씩 끊어지니 2로 나눈 몫으로 먼저 체크
		for(int i=0; i<countBit/2; i++) {
			int token = Integer.parseInt(binary.substring(0, 11), 2);
			int a = token/45;
			int b = token%45;
			str += (char)alpha.charAt(a);
			str += (char)alpha.charAt(b);
			binary = binary.substring(11);
		}
		if(countBit%2 == 1) {
			int token = Integer.parseInt(binary.substring(0,6), 2);
			str += (char)alpha.charAt(token);
			binary = binary.substring(6);
		}
	}
	
	static void numeric() {
		int countBit = Integer.parseInt(binary.substring(0, 10), 2);
//		System.out.println(binary.substring(0, 10));
		binary = binary.substring(10);
		
		num += countBit;
		
		// 3자리씩 끊어지니 3으로 나눈 몫으로 먼저 체크
		for(int i=0; i<countBit/3; i++) {
			int token = Integer.parseInt(binary.substring(0, 10), 2);
//			System.out.println(binary.substring(0, 10));
			if(token/100 == 0)
				str += "0";
			str += token;
			binary = binary.substring(10);
		}
		// 뒤에 2자리일 경우
		if(countBit%3 == 2) {
			int token = Integer.parseInt(binary.substring(0,7), 2);
			if(token/10 == 0)
				str += "0";
			str += token;
			binary = binary.substring(7);
		}
		// 뒤에 1자리일 경우
		else if(countBit%3 == 1) {
			int token = Integer.parseInt(binary.substring(0,4), 2);
			str += token;
			binary = binary.substring(4);
		}
	}
}
