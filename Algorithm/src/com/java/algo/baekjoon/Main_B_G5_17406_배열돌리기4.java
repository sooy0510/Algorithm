package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 17406_배열돌리기4
 * 메모리 : 31144KB
 * 시간 : 248ms
 * 길이 : 2696B
 * 풀이 
 * 1. 순열
 * 2. 문제 잘못 이해
 * 	=> 회전을 완료한후의 최솟값을 비교해야하는데, 각 회전시의 최솟값끼리 비교했다 
 *
 */

public class Main_B_G5_17406_배열돌리기4 {

	private static int N,M,K;
	private static int[][] arr;
	private static int[][] oper;
	private static int MIN;
 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		oper = new int[K][3];
		MIN = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<3; j++) {
				oper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//회전순서순열 구하기
		perm(0, new int[K] ,0);
		
		System.out.println(MIN);
	}

	private static void perm(int index, int[] numbers, int selected) {
		
		if(index == K) {
			//배열돌리기 시작
			startRotate(arr,numbers);
		}
		
		for(int i=0; i<K; i++) {
			if((selected & 1<<i) == 0) {
				numbers[index] = i;
				perm(index+1, numbers, selected | 1<<i);
			}
		}
	}

	private static void startRotate(int[][] arr, int[] numbers) {
		int[][] cloned = cloneArr(arr);
		
		for(int k=0; k<K; k++) {
			int n = numbers[k];
			//배열돌리기
			rotation(cloned,oper[n][0],oper[n][1],oper[n][2]);

		}//배열돌리기 끝
		
		//최소값 구하기
		int result = getMin(cloned);
		MIN = Math.min(MIN, result);
		
	}

	private static int getMin(int[][] cloned) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++) {
				sum += cloned[i][j];
			}
			min = Math.min(sum, min);
		}
		return min;
	}

	private static int[][] cloneArr(int[][] arr) {
		int[][] cloned = new int[N][M];
		for(int i=0; i<N; i++) {
			cloned[i] = arr[i].clone();
		}
		
		return cloned;
	}

	private static void rotation(int[][] cloned, int r, int c, int s) {
		int si = r-s-1; int sj = c-s-1; 
		int ei = r+s-1; int ej = c+s-1; 
		
		
		while(si != ei) {
			int d = cloned[si][sj];

			for(int i=si; i<ei; i++) {
				cloned[i][sj] = cloned[i+1][sj];
			}
			
			for(int j=sj; j<ej; j++) {
				cloned[ei][j] = cloned[ei][j+1];
			}
			
			for(int i=ei; i>si; i--) {
				cloned[i][ej] = cloned[i-1][ej];
			}
			
			for(int j=ej; j>sj; j--) {
				cloned[si][j] = cloned[si][j-1];
			}
			
			cloned[si][sj+1] = d;

			si++;sj++;
			ei--;ej--;
		}
	}

}
