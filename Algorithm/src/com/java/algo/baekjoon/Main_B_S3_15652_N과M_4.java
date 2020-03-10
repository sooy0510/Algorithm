package com.java.algo.baekjoon;

import java.util.Scanner;

/**
 * 
 * 15652_N과 M(4)
 * 메모리 : 22840KB
 * 시간 : 144ms
 * 길이 : 821B
 * 풀이
 * 1. 중복순열
 * 2. 비내림차순은 처음 들어봐서 신기했다
 * 	=> 이전에 뽑은 값이 현재 뽑을 값보다 더 크면 pass했다
 *
 */

public class Main_B_S3_15652_N과M_4 {

	private static int N;
	private static int M;
	private static int[] numbers;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		//중복순열, 비내림차순
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		numbers = new int[M+1];
		
		permutation(1);
		System.out.println(sb);
	}

	private static void permutation(int index) {
		
		if(index == M+1) {
			for (int i = 1; i < M+1; i++) {
				sb.append(numbers[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			//이전에 뽑은 값이 지금 뽑을 값보다 더 크면 pass
			if(index >= 2) {
				if(numbers[index-1] > i) {
					continue;
				}
			}
			numbers[index] = i;
			permutation(index+1);
		}
		
	}

}
