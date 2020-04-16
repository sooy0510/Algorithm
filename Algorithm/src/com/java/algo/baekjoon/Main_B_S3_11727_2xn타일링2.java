package com.java.algo.baekjoon;

import java.util.Scanner;

/**
 * 
 * 11727_2xn타일링2
 * 메모리 : 14272KB
 * 시간 : 104ms
 * 길이 : 398B
 * 풀이
 * 1. DP
 *
 */

public class Main_B_S3_11727_2xn타일링2 {

	static final int MOD = 10007;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] memo = new int[1001];
		
		for(int i=1; i<n+1; i++) {
			if(i == 1)memo[1] = 1;
			else if(i==2)memo[2] = 3;
			else memo[i] = (memo[i-2]*2 + memo[i-1])%MOD;
		}
		
		System.out.println(memo[n]);
	}

}
