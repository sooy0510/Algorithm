package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D3_1244_최대상금 {

	static int[] numbers;
	static String N;
	static int S;	//교환횟수
	static int len;
	static int MAX = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();;
		int T = Integer.parseInt(br.readLine());

		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = st.nextToken();
			S = Integer.parseInt(st.nextToken());
			len = N.length();
			numbers = new int[len];
			MAX = 0;
			
			char[] temp = N.toCharArray();
			
			for(int i=0; i<len; i++) {
				numbers[i] = temp[i]-'0';
			}

			//System.out.println(Arrays.toString(numbers));
			
			permutation(0, 0);	//처리할 인덱스, 교환횟수
			sb.append("#"+test+" "+MAX+"\n");
		}
		System.out.println(sb);
		
		
	}

	private static void permutation(int index, int cnt) {
		
		if(index == len) {
			//System.out.println(Arrays.toString(numbers));
			if(S - cnt > 0) {
			}else {
				int sum = 0;
				int mul = 1;
				for(int i=len-1; i>=0; i--) {
					sum += numbers[i] * mul;
					mul *= 10;
				}
				MAX = Math.max(sum, MAX);
				return;
			}
			
			
//			if(cnt == S) {
//				//System.out.println(Arrays.toString(numbers));
//				int sum = 0;
//				int mul = 1;
//				for(int i=len-1; i>=0; i--) {
//					sum += numbers[i] * mul;
//					mul *= 10;
//				}
//				MAX = Math.max(sum, MAX);
//				return;
//			}
			
		}
		
		
		//자기포함 뒤에있는것들만 교환대상임
		for(int i=index; i<len; i++) {
			//index 위치와 i를 swap
			swap(index, i);
			permutation(index+1, cnt+1);
			swap(index, i);	//원상태로
		}
	}

	private static void swap(int a, int b) {
		//값이 아니라 인덱스를 서로 교환
		int temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;
	}

}
