package com.java.algo.baekjoon;

import java.util.Scanner;

/**
 * 
 * 1463_1로만들기
 * 유형 : DP
 * 1. Top-Down
 * 메모리 : 57168KB
 * 시간 : 3076ms
 * 길이 : 777B
 * 
 * 2. Bottom-Up
 * 메모리 : 18440KB
 * 시간 : 128ms
 * 길이 : 762B
 *
 */

public class Main_B_S3_1463_1로만들기 {

	static int[] memo;
	static int MIN;
	static int N;
	
	public static void main(String[] args) {
		//dp
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	//1<=N<=1,000,000
		memo = new int[N+1];

		//System.out.println(dp(N));
		
		
		//Bottom-Up
		for(int i=0; i<N+1; i++) {
			if(i == 0)memo[0] = 0;
			else if(i == 1)memo[1] = 0;
			else {
				memo[i] = memo[i-1]+1;
				if(i%2 == 0)memo[i] = Math.min(memo[i], memo[i/2]+1);
				if(i%3 == 0)memo[i] = Math.min(memo[i], memo[i/3]+1);
			}
		}
		System.out.println(memo[N]);
	}


	// Top-Down(재귀)
	private static int dp(int n) {
		if(n == 1)
			return 0;
		if(memo[n] > 0) 	//이미 저장된 값 return
			return memo[n];
		
		// -1연산(3이나 2로 나누어지지 않을 경우 최소계산수)
		memo[n] = dp(n-1) + 1;
		
		if(n%3 == 0) {
			memo[n] = Math.min(memo[n], dp(n/3) + 1);
		}
		
		if(n%2 == 0) {
			memo[n] = Math.min(memo[n], dp(n/2) + 1);
		}
		
		return memo[n];
	}

}
