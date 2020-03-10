package com.java.algo.baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 15655_N과M(6)
 * 메모리 : 14348KB
 * 시간 : 108ms
 * 길이 : 872B
 * 풀이
 * 1. 조합
 *
 */

public class Main_B_S3_15655_N과M_6 {

	private static int N;
	private static int M;
	private static int[] input;
	private static int[] numbers;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		//중복불가 -> 조합
		//오름차순
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		input = new int[N];
		numbers = new int[M];
		
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}

		Arrays.sort(input);
		
		combination(0,0);
		System.out.println(sb);
	}

	private static void combination(int index, int count) {
		
		if(count == M) {
			for (int i = 0; i < M; i++) {
				sb.append(numbers[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=index; i<N; i++) {
			numbers[count] = input[i];
			combination(i+1, count+1);
		}
	}

}
