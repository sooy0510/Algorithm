package com.java.algo.baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 15654_N과M(5)
 * 
 * (1) visited배열 복사
 * 메모리 : 87196KB
 * 시간 : 452ms
 * 길이 : 907B
 *
 * (2) 비트마스킹
 * 메모리 : 76248KB
 * 시간 : 376ms
 * 길이 : 877B
 */

/*
public class Main_B_S3_15654_N과M_5 {
	
	private static int N;
	private static int M;
	private static int[] input;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		//nPm
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		Arrays.sort(input);
		permutation(0, new int[M], new boolean[N]);
		System.out.println(sb);
	}

	private static void permutation(int index, int[] temp, boolean[] visited) {
		
		if(index == M) {
			for(int i=0; i<M; i++) {
				sb.append(temp[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			boolean[] cloned = visited.clone();
			if(cloned[i])continue;
			
			cloned[i] = true;
			temp[index] = input[i];
			permutation(index+1, temp, cloned);
		}
	}
}
*/

public class Main_B_S3_15654_N과M_5 {
	
	private static int N;
	private static int M;
	private static int[] input;
	static StringBuilder sb = new StringBuilder();
	private static int[] numbers;

	public static void main(String[] args) {
		//nPm
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		input = new int[N];
		numbers = new int[M];
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		Arrays.sort(input);
		permutation(0,0);
		System.out.println(sb);
	}

	private static void permutation(int index, int selected) {
		
		if(index == M) {
			for(int i=0; i<M; i++) {
				sb.append(numbers[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			if((selected & 1<<i) == 0) {
				numbers[index] = input[i];
				permutation(index+1, selected | 1<<i);
			}
		}
	}
}
