package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * ssafy - Q1204: 최빈수 구하기
 * */


class Q1204 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		int t = 0;
		StringTokenizer st;
		int[] arr = new int[101];
		
		int max=0;
		
		
		for (int test_case = 1; test_case <= T; test_case++) {
			Arrays.fill(arr,0);  // 배열 초기화
			t = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			
			while(st.hasMoreTokens()) {				
				int num = Integer.parseInt(st.nextToken());
				arr[num]++;
			}
			
			max = getMax(arr);  //최대빈도수
			
			for(int i=arr.length-1; i>=0; i--) {
				if(arr[i] == max) {
					bw.write("#"+t+" "+i+"\n");
					break;
				}
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	private static int getMax(int[] arr) {
		int max = arr[0];
		
		for(int i=0; i<arr.length-1; i++) {
			if(arr[i+1]> max) {
				max = arr[i+1];
			}
		}
		return max;
	}
}