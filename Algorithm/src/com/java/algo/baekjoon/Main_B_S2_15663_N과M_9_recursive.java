package com.java.algo.baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 15663_N과M(9)
 * 메모리 : 22128KB
 * 시간 : 168ms
 * 길이 : 1002B
 * 풀이
 * 1. 재귀
 *
 */


public class Main_B_S2_15663_N과M_9_recursive {

	private static int N;
	private static int M;
	static StringBuilder sb = new StringBuilder();
	private static int[] numbers;
	private static int[] input;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		input = new int[N];
		numbers = new int[M];
		
		for(int i=0; i<N; i++) {
			input[i] = sc.nextInt();
		}
		
		Arrays.sort(input);
		
		permutation(0,0);
		System.out.println(sb);
	}

	private static void permutation(int index, int selected) {
		
		if(index == M) {
			for (int n : numbers)
				sb.append(n).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			if((selected & 1<<i)==0) {
				if(numbers[index] != input[i]) {
					numbers[index] = input[i];
					permutation(index+1, selected | 1<<i);
				}
			}
		}
		//numbers 마지막index를 0으로 초기화
		numbers[index] =0;
	}

}

