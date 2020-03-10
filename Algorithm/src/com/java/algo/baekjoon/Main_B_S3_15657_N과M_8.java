package com.java.algo.baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 15657_N과M(8)
 * 메모리 : 26108KB
 * 시간 : 164ms
 * 길이 : 903B
 * 풀이
 * 1. 비내림차순
 *
 */


public class Main_B_S3_15657_N과M_8 {

	private static int N;
	private static int M;
	private static int[] input;
	static StringBuilder sb = new StringBuilder();
	private static int[] numbers;

	public static void main(String[] args) {
		//중복순열
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		input = new int[N];
		numbers = new int[M];
		
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		Arrays.sort(input);
		
		permutation(0);	
		System.out.println(sb);
	}

	private static void permutation(int index) {
		
		if(index == M) {
			for (int i = 0; i < M; i++) {
				sb.append(numbers[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(index >= 1) {
				if(numbers[index-1] > input[i])
					continue;
			}
			numbers[index] = input[i];
			permutation(index+1);
		}
	}

}

