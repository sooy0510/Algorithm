package com.java.algo.baekjoon;

import java.util.Scanner;

/**
 * 
 * 9095_1,2,3 더하기 : DP
 * 1. Bottom-Up
 * 메모리 : 14256KB
 * 시간 : 108ms
 * 길이 : 564B
 * 
 * 2. Top-Down
 * 메모리 : 14268KB
 * 시간 : 104ms
 * 길이 : 613B
 *
 */

public class Main_B_S3_9095_123더하기 {

	private static int n;
	static int[] memo;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		
		//1<=n<=10
		memo = new int[11];
		
		// 1. Bottom-Up 
//		for(int i=1; i<11; i++) {
//			if(i==1)memo[1] = 1;
//			else if(i==2)memo[2] = 2;
//			else if(i==3)memo[3] = 4;
//			else memo[i] = memo[i-3]+memo[i-2]+memo[i-1];
//		}
//		
//		for(int test=1; test<=T; test++) {
//			n = sc.nextInt();
//			sb.append(memo[n]+"\n");
//		}
//		System.out.println(sb.toString());
		
		for(int test=1; test<=T; test++) {
			n = sc.nextInt();
			sb.append(dp(n)+"\n");
		}
		System.out.println(sb.toString());
	}

	// 2. Top-Down
	private static int dp(int n) {
		if(n == 1) return 1;
		if(n == 2) return 2;
		if(n == 3) return 4;
		
		if(memo[n] > 0) return memo[n];
		
		memo[n] = dp(n-3)+dp(n-2)+dp(n-1);
		
		return memo[n];
	}

}
