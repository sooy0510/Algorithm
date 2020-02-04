package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author 이수연
 * 메모리 : 289544KB
 * 시간 : 1680ms
 * 코드길이 : 2354B
 * 풀이 : 완탐으로 가능식 모두 구현하여 계산식 모두 돌림 => 통과된게 신기
 */

public class Main_B_S1_14888_연산자끼워넣기 {
	
	static int N;					//수의 개수
	static char operators[];			//연산자
	static int opt_cnt[] = new int[4];	//연산자 개수
	static boolean o_selected[];		//operators 중복체크
	static String permuts[];
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int result;
	//static boolean isFirst = true;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N : 수의 개수, numbers[N] : 수열, operators[4], selected[N+1] : 중복체크
		N = Integer.parseInt(br.readLine());
		operators = new char[N-1];
		o_selected = new boolean[N-1];
		permuts = new String[2*N-1];
		
		int idx=0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			permuts[idx] = st.nextToken();
			idx += 2;
		}
		
		st = new StringTokenizer(br.readLine());
		
		idx = 0;
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
		
		// 순열, 재귀
		permutation(1);
		
		System.out.println(max);
		System.out.println(min);
	}

	private static void permutation(int o_idx) {//내가 처리하려고 하는 위치
		
		// 기저조건
		if(o_idx >= 2*N-2) {
			//계산하기
			if(operate()) {
				max = Math.max(max, result); 
				min = Math.min(min, result);
				return;
			}
		}
		

		
		// 연산자 재귀
		for(int i=0; i<operators.length; i++) {
			if(o_selected[i] == true) 
				continue;
			permuts[o_idx] = Character.toString(operators[i]);
			o_selected[i] = true;
			permutation(o_idx+2);
			o_selected[i] = false;
		}
	}

	private static boolean operate() {
		result=Integer.parseInt(permuts[0]);
		for(int i=1; i<permuts.length-1; i=i+2) {
			switch (permuts[i]) {
			case "+":
				result = result + Integer.parseInt(permuts[i+1]);
				break;
				
			case "-":
				result = result - Integer.parseInt(permuts[i+1]);
				break;
				
			case "*":
				result = result * Integer.parseInt(permuts[i+1]);
				break;
				
			case "/":
				result = result / Integer.parseInt(permuts[i+1]);
				break;

			default:
				break;
			}
		}
		return true;
	}

}