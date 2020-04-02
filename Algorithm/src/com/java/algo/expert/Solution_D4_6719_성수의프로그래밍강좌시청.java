package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 6719_성수의프로그래밍강좌시청
 * 메모리 : 23884KB
 * 시간 : 149ms
 * 길이 : 1025B
 * 풀이 
 * 1. Greedy (뒤로 갈수록 숫자가 커져야 실력의 수치가 커짐)
 */

public class Solution_D4_6719_성수의프로그래밍강좌시청 {

	private static int N,K;
	private static int[] input;
	private static double result;

	public static void main(String[] args) throws Exception{
		//Greedy
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			input = new int[N];
			result = 0;
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(input);
			
			for(int i=N-K; i<N; i++) {
				result = (result+input[i])/2;
			}
			sb.append("#"+test+" "+String.format("%.6f",result)+"\n");
		}
		System.out.println(sb);
	}

}
