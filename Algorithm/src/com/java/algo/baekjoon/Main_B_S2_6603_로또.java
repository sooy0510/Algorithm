package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 6603_로또
 * 메모리 : 14400KB
 * 시간 : 88ms
 * 길이 : 1250B
 * 풀이
 * 1. 조합
 * => 지금 뽑은 수의 뒤에 있는 수들만 뽑는 방식으로 사전순으로 출력할수 있었다
 *
 */

public class Main_B_S2_6603_로또 {

	private static int[] numbers;
	private static int[] pick;
	static int size;
	static boolean[] selected;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		//조합
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		pick = new int[6];
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken());
			if(size == 0) {
				break;
			}
			numbers = new int[size];
			Arrays.fill(pick, 0);
			
			for(int i=0; i<size; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			
			//combination
			combination(0, 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void combination(int index, int c) {//index : 조합고려대상 시작 인덱스, c : 직전까지 조합한 원소의 수
		
		if(c == 6) {
			for (int i : pick) {
				sb.append(i+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=index; i<size; i++) {
			pick[c] = numbers[i];
			combination(i+1, c+1);	//지금 뽑은 수의 뒤부터 봄
		}
	}

}
