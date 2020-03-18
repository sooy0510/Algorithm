package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 5604_구간합(2차)
 * 메모리 : 22620KB
 * 시간 : 115ms
 * 길이 : 1296B
 * 풀이
 * 1. for문
 *
 */

public class Solution_D4_5604_구간합_2 {

	private static long A,B;
	static long result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			result = 0;
			long[] ans = new long[10];	//0~9까지의 숫자갯수를 구할 배열
			long point = 1;
			while(A<=B) {
				while(B%10 != 9 && A<=B) {
					cal(B,ans,point);
					B--;
				}
				if(B<A) break;
				while(A % 10 != 0 && A<= B) {
					cal(A, ans, point);
					A++;
				}
				A/=10;
				B/=10;
				for(int i=0; i<10; i++) {
					ans[i] += (B-A+1)*point;
				}
				point *= 10;
			}
			long sum = 0;
			for(int i=0; i<10; i++) {
				sum+=(ans[i]*i);
			}
			sb.append("#"+test+" "+sum+"\n");
		}
		System.out.println(sb);
	}

	private static void cal(long x, long[] ans, long point) {
		while(x>0) {
			String s = String.valueOf(x);
			int xx = s.charAt(s.length()-1)-'0';
			ans[xx] += point;
			x /= 10;
		}
	}

}
