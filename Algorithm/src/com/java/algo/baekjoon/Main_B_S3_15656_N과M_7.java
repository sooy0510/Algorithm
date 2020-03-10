package com.java.algo.baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 15656_N과M(7)
 * 메모리 : 471632KB
 * 시간 : 1060ms
 * 길이 : 898B
 *
 */


public class Main_B_S3_15656_N과M_7 {

	private static int N;
	private static int M;
	private static int[] input;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		//중복순열
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		input = new int[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		Arrays.sort(input);
		
		permutation(0, new int[M]);	
		System.out.println(sb);
	}

	private static void permutation(int index, int[] temp) {
		
		if(index == M) {
			for (int i = 0; i < M; i++) {
				sb.append(temp[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			temp[index] = input[i];
			permutation(index+1, temp);
		}
	}

}

