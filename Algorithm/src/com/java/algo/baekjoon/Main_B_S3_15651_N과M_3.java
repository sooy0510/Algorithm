package com.java.algo.baekjoon;

import java.util.Scanner;

/**
 * 
 * 15651_N과 M(3)
 * (1)boolean 배열
 * 메모리 : 373652KB
 * 시간 : 704ms
 * 길이 : 676B
 * 
 * (2)비트마스킹
 * 메모리 : 299440KB
 * 시간 : 640ms
 * 길이 : 741B
 *
 */

//비트마스킹
/*public class Main_B_S3_15651_N과M_3 {

	private static int N;
	private static int M;
	private static int[] numbers;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		//중복순열
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		numbers = new int[M];
		
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
				permutation(index+1, selected);	//방문체크 안함
			}
		}
	}

}*/

//boolean배열
public class Main_B_S3_15651_N과M_3 {

	private static int N;
	private static int M;
	private static int[] numbers;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		//중복순열
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
			numbers[index] = i;
			permutation(index+1);
		}
	}

}
