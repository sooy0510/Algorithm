package com.java.algo.baekjoon;

import java.util.Scanner;
/**
 * 
 * 2839_설탕배달
 * 메모리 : 14280KB
 * 시간 : 104ms
 * 길이 : 707B
 * 풀이 
 * 1. dfs로 푸니까 시간초과남
 * 2. 5로 바로 나눠질때까 가장 최소
 *
 */


public class Main_B_B1_2839_설탕배달 {

	static int M;	//만들어야 하는 소금무게
	static int count;	
	static int MIN;//최소 봉지수

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		MIN = Integer.MAX_VALUE;
		M = sc.nextInt();	
		
		if(M%5 == 0) {
			MIN = M/5;
			System.out.println(MIN);
			return;
		}
		
		if(M%3 == 0) {
			//System.out.println(MIN);
			//return;
			MIN = Math.min(MIN, M/3);
		}
		
		int cnt=0;
		while(M>5) {
			cnt++;
			M -= 5;
			
			if(M%3 == 0) {
				int res = cnt + M/3;
				MIN = Math.min(res, MIN);
			}
		}
		
		System.out.println(MIN==Integer.MAX_VALUE?-1:MIN);
		//System.out.println(MIN);
	}
}
