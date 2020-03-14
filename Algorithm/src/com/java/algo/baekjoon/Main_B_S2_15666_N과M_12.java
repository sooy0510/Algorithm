package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 19840_N과M(12)
 * 메모리 : 19840KB
 * 시간 : 108ms
 * 길이 : 1266B
 * 풀이
 * 1. 중복순열, 비내림차순
 *
 */

public class Main_B_S2_15666_N과M_12 {

	private static int N;
	private static int M;
	private static int[] input;
	private static int[] numbers;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		//중복순열, 비내림차순
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		numbers = new int[M];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
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
				if(index >= 1) {
					if(numbers[index-1] > input[i])
						continue;
				}
				numbers[index] = input[i];
				permutation(index+1);
			}
		}
		
		numbers[index] = 0;
	}

}
