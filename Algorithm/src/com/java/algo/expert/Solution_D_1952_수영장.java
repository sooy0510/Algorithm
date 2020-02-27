package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 1952_수영장
 * 메모리 : 23580KB
 * 시간 : 114ms
 * 길이 : 1743B
 * 풀이
 * 1. 재귀, 완탐
 * 2. 1년권이 최대값이니까 초기 MIN값으로 잡았다.
 *
 */

public class Solution_D_1952_수영장 {
	
	static int[] fees;
	static int[] plans;
	static int MIN;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine().trim());
		fees = new int[4];
		plans = new int[12];
		
		for(int test=1; test<=T; test++) {
			Arrays.fill(fees, 0);
			Arrays.fill(plans, 0);
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				fees[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<12; i++) {
				plans[i] = Integer.parseInt(st.nextToken());
			}
			
			//가장 클때는 1년권 끊을때
			MIN = fees[3];
			
			calculate(0,0);	//처리할 인덱스, 요금
			sb.append("#"+test+" "+MIN+"\n");
		}
		System.out.println(sb);
		
	}

	private static void calculate(int index, int sum) {
		
		if(sum > MIN)
			return;
		
		if(index == 12) {
			MIN = Math.min(MIN, sum);
			return;
		}
		
		if(plans[index] == 0) {
			calculate(index+1, sum);
		}else {
			for(int f=0; f<4; f++) {
				int cnt=0;
				switch (f) {
				case 0:
					//1일권
					cnt = fees[0] * plans[index];
					calculate(index+1, sum+cnt);
					break;
					
				case 1:
					//1달권
					cnt = fees[1];
					calculate(index+1, sum+cnt);
					break;
					
				case 2:
					//3달권
					cnt = fees[2];
					if(index+1 >= 12 || index+2 >= 12 || index+3 >= 12) {
						calculate(12, sum+cnt);
					}else {
						calculate(index+3, sum+cnt);
					}
					break;
					
				}
			}
		}
		
	}

}
