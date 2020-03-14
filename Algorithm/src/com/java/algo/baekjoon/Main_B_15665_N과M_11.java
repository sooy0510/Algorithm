package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 15665_N과M(11)
 * 메모리 : 47020KB
 * 시간 : 288ms
 * 길이 : 1210B
 * 풀이
 * 1. 중복순열
 * 2. 수열은 중복x
 *
 */


public class Main_B_15665_N과M_11 {

	private static int N;
	private static int M;
	private static int[] input;
	static StringBuilder sb = new StringBuilder();
	private static int[] numbers;

	public static void main(String[] args) throws Exception {
		//중복순열
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		numbers = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input);
		
		permutation(0);	
		System.out.println(sb);
	}

	private static void permutation(int index) {
		
		if(index == M) {
			for (int i : numbers) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(numbers[index] != input[i]) {
				numbers[index] = input[i];
				permutation(index+1);
			}
		}
	}

}

