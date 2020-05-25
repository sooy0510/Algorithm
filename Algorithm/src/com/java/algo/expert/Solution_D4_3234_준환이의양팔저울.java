package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_3234_준환이의양팔저울 {

	private static int N;
	private static int total;
	private static int[] sinkers;
	private static int[] pick;
	private static boolean[] selected;

	public static void main(String[] args) throws Exception{
		// 왼쪽 >= 오른쪽
		// 1. 순열 
		// 2. 비교
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int test=1; test<=T; test++) {
			//입력
			N = Integer.parseInt(br.readLine());
			selected = new boolean[N];
			sinkers = new int[N];
			pick = new int[N];
			
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0; i<N; i++) {
				sinkers[i] = Integer.parseInt(st.nextToken());
			}
			
			//System.out.println(Arrays.toString(sinkers));
			//왼쪽 저울 구하기(부분합)
			perm(0,0);
		}
	}

	private static void perm(int index, int selected) {

		if(index == N) {
			System.out.println(Arrays.toString(pick));
		}
		
		for(int i=0; i<N; i++) {
			if((selected & 1<<i) == 0) {
				pick[index] = sinkers[i];
				perm(index+1, selected | 1<<i);
			}
		}
	}

}
