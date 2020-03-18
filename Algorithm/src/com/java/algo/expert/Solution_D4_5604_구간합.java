package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 시간초과
 *
 */

public class Solution_D4_5604_구간합 {

	private static long a,b;
	//private static int digit_a, digit_b;
	static long result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			a = Long.parseLong(st.nextToken());
			b = Long.parseLong(st.nextToken());
			result = 0;
			
			//자릿수 구하기
			//자리수1~9까지는 
			//digit_a = (Long.toString(a).length()/10) + (Long.toString(a).length()%10);
			//digit_b = (Long.toString(b).length()/10) + (Long.toString(b).length()%10);
		
			Long now = a;
			int digit=0;
			String str="";
			
			while(true) {
				if(now == b+1)break;
				digit = (Long.toString(now).length()/10) + (Long.toString(now).length()%10);
				str = Long.toString(now);
				
				result += getSum(str, digit);
				now++;
			}
			
			sb.append("#").append(test).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static long getSum(String str, int digit) {
		int sum = 0;
		for(int i=0; i<digit; i++) {
			sum += str.charAt(i)-'0';
		}
		return sum;
	}

}
