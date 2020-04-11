package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 1786_찾기
 * 메모리 : 84256
 * 시간 : 460ms
 * 길이 : 1163B
 * 풀이
 * 1. KMP
 * 2. sc.next와 sc.nextLine의 차이
 * - sc.next : 띄어쓰기 기준으로 읽고 개행문자 남겨둠
 * - sc.nextLine : 개행문자기준으로 한 라인을 읽음
 * - next를 사용하고 nextLine을 사용할 경우 sc.next뒤에 개행문자가 남아잇기때문에
 * 	nextLine에는 개행문자만 들어가서 정확한 답이 출력되지 않을수 있다. 따라서 sc.next후에는
 * 	sc.nextLine으로 개행문자를 한번 제거해줘야함
 *
 */

public class Main_B_G2_1786_찾기 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String parent = br.readLine();
		String pattern = br.readLine();
		
		//System.out.println(parent);
		//System.out.println(pattern);
		
		KDB(parent, pattern);
	}

	private static void KDB(String parent, String pattern) {
		int[] table = getPi(pattern);
		
		StringBuilder sb = new StringBuilder();
		int result =0;
		int j=0;
		for(int i=0; i<parent.length(); i++) {
			while(j>0 && parent.charAt(i) != pattern.charAt(j)) {
				j = table[j-1];
			}
			
			if(parent.charAt(i) == pattern.charAt(j)) {
				if(j == pattern.length()-1) {
					result++;
					sb.append(i-pattern.length()+2+" ");
					j = table[j];
				}else {
					j++;
				}
			}
			
		}
		
		System.out.println(result);
		System.out.println(sb.toString());
	}

	private static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()];
		
		int j = 0;
		for(int i=1; i<pattern.length(); i++) {
			
			while(j>0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			
			if (pattern.charAt(i) == pattern.charAt(j)) {
				// i번째의 최대길이는 ++j한 값
				pi[i] = ++j;
			}
		}
		
		return pi;
	}
}
