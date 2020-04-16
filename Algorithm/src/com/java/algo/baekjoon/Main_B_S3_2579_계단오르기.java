package com.java.algo.baekjoon;

import java.util.Scanner;

/**
 * 
 * 2579_계단오르기
 * 메모리 : 14760KB
 * 시간 : 126ms
 * 길이 : 598B
 * 풀이
 * 1. DP
 *
 */

public class Main_B_S3_2579_계단오르기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] stair = new int[N+1];
		int[] memo = new int[N+1];
		int nojump=0, jump=0;
		
		for(int i=1; i<N+1; i++) {
			stair[i] = sc.nextInt();
		}
		
		for(int i=1; i<N+1; i++) {
			if(i == 1)memo[1] = stair[1];
			else if(i==2)memo[2] = stair[1]+stair[2];
			else {
				jump = memo[i-2] + stair[i];
				nojump = memo[i-3] + stair[i-1] + stair[i];
				
				memo[i] = Math.max(jump, nojump);
			}
		}
		
		System.out.println(memo[N]);
		
	}

}
