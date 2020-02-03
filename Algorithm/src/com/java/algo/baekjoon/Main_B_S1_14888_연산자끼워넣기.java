package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_S1_14888_연산자끼워넣기 {
	
	static int N;					//수의 개수
	static int numbers[];			//수열
	static char operators[];			//연산자
	static int opt_cnt[] = new int[4];	//연산자 개수
	static boolean n_selected[];		//numbers 중복체크
	static boolean o_selected[];		//operators 중복체크
	//static int r_numbers[];
	//static char r_operators[];
	static char permuts[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N : 수의 개수, numbers[N] : 수열, operators[4], selected[N+1] : 중복체크
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		operators = new char[N-1];
		n_selected = new boolean[N];
		o_selected = new boolean[N-1];
		permuts = new char[2*N-1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		int idx = 0;
		for(int i=0; i<4; i++) {
			int c_cnt = Integer.parseInt(st.nextToken());
			for(int j=0; j<c_cnt; j++) {
				switch (i) {
				case 0:
					operators[idx] = '+';
					idx++;
					break;
					
				case 1:
					operators[idx] = '-';
					idx++;
					break;
					
				case 2:
					operators[idx] = '*';
					idx++;
					break;
					
				case 3:
					operators[idx] = '/';
					idx++;
					break;

				default:
					break;
				}
			}
			
		}
		
		
		//System.out.println(Arrays.toString(numbers));
		//System.out.println(Arrays.toString(operators));
		
		// 순열, 재귀
		permutation(0,1);
	}

	private static void permutation(int n_idx, int o_idx) {//내가 처리하려고 하는 위치
		
		//System.out.println(Arrays.toString(permuts));
		// 기저조건
		if(n_idx >= 2*N-1 && o_idx >= 2*N-2) {
			System.out.println(Arrays.toString(permuts));
			//계산하기
			if(operate()) {
				
			}
			
			return;
		}
		
		
		// 수열 재귀
//		for(int i=0; i<numbers.length; i++) {
//			if(n_selected[i] == true) 
//				continue;
//			
//			//System.out.println("numbers");
//			permuts[n_idx] = Character.forDigit(numbers[i], 10);
//			n_selected[i] = true;
//			permutation(n_idx+2, o_idx);
//			n_selected[i] = false;
//		}
		
		// 연산자 재귀
		for(int i=0; i<operators.length; i++) {
			if(o_selected[i] == true) 
				continue;
			
			//System.out.println("operators");
			permuts[o_idx] = operators[i];
			o_selected[i] = true;
			permutation(n_idx, o_idx+2);
			o_selected[i] = false;
		}
	}

	private static boolean operate() {
		int result=permuts[0];
		for(int i=1; i<permuts.length-1; i=i+2) {
			switch (permuts[i]) {
			case '+':
				result = result + permuts[i+1];
				break;
				
			case '-':
				result = result - permuts[i+1];
				break;
				
			case '*':
				result = result * permuts[i+1];
				break;
				
			case '/':
				result = (int)(result / permuts[i+1]);
				break;

			default:
				break;
			}
		}
		return false;
	}

}
