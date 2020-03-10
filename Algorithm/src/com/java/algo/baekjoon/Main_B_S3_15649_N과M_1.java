package com.java.algo.baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 15649_N과 M(1)
 * (1)boolean배열
 * 메모리 : 56080KB
 * 시간 : 284ms
 * 길이 : 808B
 *
 * (2)비트마스킹
 * 메모리 : 58992KB
 * 시간 : 280ms
 * 길이 : 753B
 * 
 */

//boolean배열
/*public class Main_B_S3_15649_N과M_1 {
	private static int N;
	private static int M;
	private static boolean[] selected;
	private static int[] numbers;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		numbers = new int[M+1];
		selected = new boolean[N+1];
		
		permutation(1);
		System.out.println(sb);
	}

	private static void permutation(int index) {
		
		if(index == M+1) {
			//System.out.println(Arrays.toString(numbers));
			
			for (int i = 1; i < M+1; i++) {
				sb.append(numbers[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(selected[i])continue;
			
			numbers[index] = i;
			selected[i] = true;
			permutation(index+1);
			selected[i] = false;
		}
	}
}
*/

//비트마스킹
public class Main_B_S3_15649_N과M_1 {
	private static int N;
	private static int M;
	private static int[] numbers;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		numbers = new int[M+1];
		
		permutation(0,0);
		System.out.println(sb);
	}

	private static void permutation(int index, int selected) {
		
		if(index == M) {
			
			for (int i = 0; i < M; i++) {
				sb.append(numbers[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if((selected & 1<<i) == 0) {
				numbers[index] = i;
				permutation(index+1, selected | 1<<i);
			}
		}
	}
}
