package com.java.algo.baekjoon;

import java.util.Scanner;

/**
 * 
 * 15650_N과 M(2)
 * 메모리 : 14408KB
 * 시간 : 108ms
 * 길이 : 737B
 * 풀이
 * 1. 조합
 *
 */

public class Main_B_S3_15650_N과M_2 {

	private static int N;
	private static int M;
	private static int[] numbers;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		//중복없이 => 조합
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		numbers = new int[M+1];
		
		combination(1, 1);	//index, count
		System.out.println(sb);
	}

	private static void combination(int index, int count) {

		if(count == M+1) {
			for (int i = 1; i < M+1; i++) {
				sb.append(numbers[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=index; i<=N; i++) {	//지금 뽑은 수의 뒤만 봄
			numbers[count] = i;
			combination(i+1, count+1);
		}
	}

}
