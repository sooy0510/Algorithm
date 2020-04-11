package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 16916_부분문자열
 * 메모리 : 31512KB
 * 시간 : 320ms
 * 길이 : 1168B
 * 풀이
 * 1. 문자열 패턴 매칭
 * 2. 여러번 다시 풀어볼 것
 *
 */

public class Main_B_G3_16916_부분문자열 {

	static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()];
		
		int j = 0;
		for(int i=1; i<pattern.length(); i++) {
			
			while(j>0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			
			if(pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j; 
			}
		}
		
		return pi;
	}
	
	
	static void KMP(String parent, String pattern) {
		int[] table = getPi(pattern);
		
		int j = 0;
		for(int i=0; i<parent.length(); i++) {
			
			while(j>0 && parent.charAt(i) != pattern.charAt(j)) {
				j = table[j-1];
			}
			
			if(parent.charAt(i) == pattern.charAt(j)) {
				if(j==pattern.length()-1) {
					//찾으면 1리턴
					System.out.println(1);
					return;
					//패턴을 또 찾기 위해서
					//j = table[j];
				}else {
					j++;
				}
			}
			
			if(i == parent.length()-1) {
				System.out.println(0);
				return;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String parent = br.readLine();
		String pattern = br.readLine();
		
		//System.out.println(parent);
		//System.out.println(pattern);
		
		KMP(parent, pattern);
		
	}

}
