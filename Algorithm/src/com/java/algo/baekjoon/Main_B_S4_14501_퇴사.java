package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 14501_퇴사
 * 메모리 : 13000KB
 * 시간 : 100ms
 * 길이 : 923B
 * 풀이
 * 1. 조합
 * 2. 상담 추가될때마다 MAX값 갱신
 *
 */

public class Main_B_S4_14501_퇴사 {

	private static int N;
	private static int[] t;
	private static int[] p;
	private static int MAX = 0;

	public static void main(String[] args) throws Exception {
		//조합
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		t = new int[N];
		p = new int[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}

		comb(0,0);
		System.out.println(MAX);
	}

	private static void comb(int index, int total) {
	
		if(index > N)
			return;
		
		if(MAX < total)
			MAX = total;
		
		for(int i=index; i<N; i++) {
			comb(i+t[i], total+p[i]);
		}
	}

}
