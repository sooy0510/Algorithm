package com.java.algo.baekjoon;

import java.util.Scanner;

/**
 * 
 * 10974_모든순열
 * 메모리 : 56860KB
 * 시간 : 288ms
 * 길이 : 700B
 * 풀이
 * 1. 순열
 * 2. 비트마스킹으로도 해보기
 *
 */

public class Main_B_S3_10974_모든순열 {

	static int N;
	static int[] numbers;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		numbers = new int[N+1];
		visited = new boolean[N+1];
		
		perm(0);
		System.out.println(sb);
	}
	private static void perm(int index) {
		
		if(index == N) {
			for(int i=1; i<=N; i++) {
				if(visited[i])
					sb.append(numbers[i-1]+" ");
			}
			sb.append("\n");
		}
		
		for(int i=1; i<=N; i++) {
			if(visited[i])
				continue;
			
			visited[i] = true;
			numbers[index] = i;
			perm(index+1);
			visited[i] = false;
		}
	}

}
