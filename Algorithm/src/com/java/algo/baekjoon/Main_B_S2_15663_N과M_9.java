package com.java.algo.baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 15663_N과M(9)
 * 메모리 : 34668KB
 * 시간 : 2112ms
 * 길이 : 1198B
 * 풀이
 * 1. 넥퍼
 *
 */


public class Main_B_S2_15663_N과M_9 {

	private static int N;
	private static int M;
	static StringBuilder sb = new StringBuilder();
	private static int[] numbers;
	private static ArrayList<String> memo;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		numbers = new int[N];
		memo = new ArrayList<String>();
		
		for(int i=0; i<N; i++) {
			numbers[i] = sc.nextInt();
		}
		
		Arrays.sort(numbers);
		
		do {
			String str = "";
			for(int i=0; i<M; i++) {
				str += numbers[i]+" ";
			}
			if(!memo.contains(str))
				memo.add(str);
			
		}while(nextPermutation());
		
		for (String string : memo) {
			System.out.println(string);
		}
	}

	private static boolean nextPermutation() {
		int i = N-1;
		
		while(i>0 && numbers[i-1]>=numbers[i])--i;
		if(i==0)
			return false;
		
		int j = N-1;
		while(numbers[i-1] >= numbers[j])--j;
		
		int temp = numbers[i-1];
		numbers[i-1] = numbers[j];
		numbers[j] = temp;
		
		j = N-1;
		while(i<j) {
			temp = numbers[i];
			numbers[i] = numbers[j];
			numbers[j] = temp;
			i++; j--;
		}
		
		return true;
	}

}

