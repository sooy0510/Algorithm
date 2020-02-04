package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author 이수연
 * 메모리 : 15044KB
 * 시간 : 84ms
 * 코드길이 : 1965B
 * 풀이 : 재귀함수인자에 계산값을 같이 넘겨주었다.
 *
 */

public class Main_B_S1_14888_연산자끼워넣기_2 {
	
	//N
	static int N;
	//max, min
	static int MAX = Integer.MIN_VALUE;
	static int MIN = Integer.MAX_VALUE;
	// 수열 배열
	static int[] numbers;
	// 연산자 배열
	static int[] operators = new int[4];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//N저장
		N = Integer.parseInt(br.readLine());
		//임시 배열
		String[] tempArray;
		
		//수열 저장
		tempArray = br.readLine().split(" ");
		numbers = new int[N];
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(tempArray[i]);
		}
		// 연산자 저장
		tempArray = br.readLine().split(" ");
		for(int i=0; i<4; i++) {
			operators[i] = Integer.parseInt(tempArray[i]);
		}
		
		calculate(1, numbers[0]); //operators에서 처리할 위치, 계산값
		
		System.out.println(MAX);
		System.out.println(MIN);
	}
	
	// 현재 operators의 index+1(계산하기 편하게 +1해줌), 계산값
	public static void calculate(int index, int result) {	
		
		if(index == N) {
			MAX = Math.max(MAX, result);
			MIN = Math.min(MIN, result);
			return;
		}
		
		
		//4개의 operation을 모두 돈다
		for(int i=0; i<4; i++) {
			//해당 operator이 없으면 다음 operator 찾기
			if(operators[i] == 0)
				continue;
			
			//해당 operator가 있다면 해당 operator값 -1이후 계산
			operators[i]--;
			
			switch (i) {
			case 0:	//+
				calculate(index+1, result + numbers[index]);
				break;
				
			case 1:	//-
				calculate(index+1, result - numbers[index]);
				break;
				
			case 2:	//*
				calculate(index+1, result * numbers[index]);
				break;

			default: // /
				calculate(index+1, result / numbers[index]);
				break;
			}
			
			//빠져나오면 원래 개수 초기화
			operators[i]++;
		}
		
	}
}