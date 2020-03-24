package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 17406_배열돌리기4
 * 메모리 : 31104KB
 * 시간 : 224ms
 * 길이 : 3064B
 * 풀이 
 * 1. np
 *
 */

public class Main_B_G5_17406_배열돌리기4_np {

	private static int N,M,K;
	private static int[][] arr;
	private static int[][] oper;
	private static int MIN;
	private static int[] numbers;
 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		oper = new int[K][3];
		numbers = new int[K];
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
			numbers[i] = i;
		}
		
		do {
			startRotate(arr,numbers);
		} while (np());
		
		System.out.println(MIN);
	}

	private static boolean np() {
		
		int i = numbers.length-1;
		
		//1. i-1찾기
		while(i>0 && numbers[i-1] >= numbers[i])--i;
		if(i==0)return false;
		
		//2. i-1위치값과 교환할 j위치 찾기
		int j = numbers.length-1;
		while(numbers[i-1] >= numbers[j])--j;
		
		//3. i-1위차값과 j위치값 교환
		int temp = numbers[i-1];
		numbers[i-1] = numbers[j];
		numbers[j] = temp;
		
		//4. i부터 끝까지 오름차순으로 교환
		j = numbers.length-1;
		
		while(i<j) {
			temp = numbers[i];
			numbers[i] = numbers[j];
			numbers[j] = temp;
			++i; --j;
		}
		return true;
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
