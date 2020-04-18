package com.java.algo.baekjoon;

import java.util.Scanner;

/**
 * 
 * 1003_피보나치함수
 * 메모리 : 14380KB
 * 시간 : 116ms
 * 길이 : 780B
 * 풀이
 * 1. DP
 *
 */

public class Main_B_S3_1003_피보나치함수 {

	static int[][] memo;
	static int n;	//0<=n<=40
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		
		// N일때 0의 갯수 = N-1에서의 0의 갯수 +  N-2에서 0의 갯수
		// N일때 1의 갯수 = N-1에서의 1의 갯수 +  N-2에서 1의 갯수
		memo = new int[41][2];
		memo[0][0] = 1;
		memo[1][1] = 1;
		
		for(int i=2; i<41; i++) {
			memo[i][0] = memo[i-1][0] + memo[i-2][0];
			memo[i][1] = memo[i-1][1] + memo[i-2][1];
		}
		
		for(int test=1; test<=T; test++) {
			n = sc.nextInt();
			sb.append(memo[n][0]+" "+memo[n][1]+"\n");
		}
		System.out.println(sb.toString());
	}

}
