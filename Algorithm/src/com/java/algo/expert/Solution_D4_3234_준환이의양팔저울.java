package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 3234_준환이의 양팔저울
 * 메모리 : 20384KB
 * 시간 : 948ms
 * 길이 : 1326B
 * 풀이
 * 1. 순열(swap)방법으로 풀었음
 * 2. index를 기준으로 왼쪽저울에 추를 계속 놓고
 * , 오른쪽에 새로운 추를 추가할때 왼쪽보다 같거나 작으면 오른쪽 저울에 추를 추가하면서 순열을 완성해간다
 */

public class Solution_D4_3234_준환이의양팔저울 {

	private static int N;
	private static int result;
	private static int[] sinkers;

	public static void main(String[] args) throws Exception{
		// 왼쪽 >= 오른쪽
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int test=1; test<=T; test++) {
			//입력
			N = Integer.parseInt(br.readLine());
			sinkers = new int[N];
			
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0; i<N; i++) {
				sinkers[i] = Integer.parseInt(st.nextToken());
			}
			
			result = 0;
			go(0,0,0);
			sb.append("#"+test+" "+result+"\n");
		}
		System.out.println(sb.toString());
	}

	private static void go(int left, int right, int index) {

		if(index == N) {
			result++;
			return;
		}
		
		for(int i=index; i<N; i++) {	//index를 기준으로 뒤만 바꿔주기
			swap(index, i);
			go(left+sinkers[index], right, index+1);
			
			if(left >= right+sinkers[index]) {
				go(left, right+sinkers[index], index+1);
			}
			swap(index, i);
		}
	}

	private static void swap(int index, int i) {
		int temp = sinkers[index];
		sinkers[index] = sinkers[i];
		sinkers[i] = temp;
 	}

}
