package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 1233_사칙연산 유효성 검사
 * 메모리 : 19724KB
 * 실행시간 : 118ms
 * 풀이
 * 1. 후위연산과 스택이용
 *
 */

public class Solution_D4_1233_사칙연산유효성검사 {

	static int T = 10;
	static int N;
	static Stack<Character> stack;
	static char[] arr;
	static boolean isPossible;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		//중위순회
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		
		int T = 10;
		
		for(int test=1; test<=T; test++) {
			N = Integer.parseInt(br.readLine());
			stack = new Stack<>();
			arr = new char[N+1];
			isPossible = true;
			
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				arr[i] = st.nextToken().charAt(0);
			}
			
			
			check(1);
			
			if(isPossible) {
				sb.append("#"+test+" 1\n");
			}else {
				sb.append("#"+test+" 0\n");
			}
			
		}
		System.out.println(sb);
	}

	private static void check(int index) {
		if(index <= N) {
			//후위순회이므로
			check(index*2);
			check(index*2+1);
			
			
			if(Character.isDigit(arr[index])) { //숫자면
				stack.push(arr[index]);
			}else {	//연산자일떄
				if(stack.isEmpty() || stack.size() < 2) {
					isPossible = false;
					return;
				}else {
					stack.pop();
					stack.pop();
					stack.push('1');
				}
				
			}
			
		}
		
	}

}
