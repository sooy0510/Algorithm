package com.java.algo.baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 15664_N과M(10)
 * 메모리 : 14428KB
 * 시간 : 108ms
 * 길이 : 1174B
 * 풀이
 * 1. memo
 *
 */


public class Main_B_S2_15664_N과M_10 {

	private static int N;
	private static int M;
	private static int[] input;
	static StringBuilder sb = new StringBuilder();
	private static int[] numbers;
	private static ArrayList<String> memo;

	public static void main(String[] args) {
		//중복순열
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		input = new int[N];
		numbers = new int[M];
		memo = new ArrayList<String>();
		
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		Arrays.sort(input);
		
		permutation(0,0);	
		
		for (String string : memo) {
			System.out.println(string);
		}
	}

	private static void permutation(int index, int selected) {
		
		if(index == M) {
			String str="";
			for (int i = 0; i < M; i++) {
				str += numbers[i]+" ";
			}
			if(memo.contains(str))
				return;
			
			memo.add(str);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(index >= 1) {
				if(numbers[index-1] > input[i])
					continue;
			}
			if((selected & 1<<i) == 0) {
				numbers[index] = input[i];
				permutation(index+1, selected | 1<<i);
			}
		}
	}

}

