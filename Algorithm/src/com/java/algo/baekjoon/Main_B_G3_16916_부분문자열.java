package com.java.algo.baekjoon;

import java.util.Scanner;

public class Main_B_G3_16916_부분문자열 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String parent = sc.next();
		String pattern = sc.next();
		
		//System.out.println(parent);
		//System.out.println(pattern);
		
		KMP(parent, pattern);
		
	}

	private static void KMP(String parent, String pattern) {
		int[] table = getPi(pattern);
		
		int j = 0;
		for(int i=0; i<parent.length(); i++) {
			
			while(j>0 && parent.charAt(i) != parent.charAt(j)) {
				j = table[j-1];
			}
			
			if(parent.charAt(i) == parent.charAt(j)) {
				if(j==parent.length()-1) {
					System.out.println((i-pattern.length()+1) + "째 인덱스에서 찾음" );
					//패턴을 또 찾기 위해서
					j = table[j];
				}else {
					j++;
				}
			}
		}
	}

	private static int[] getPi(String pattern) {
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

}
