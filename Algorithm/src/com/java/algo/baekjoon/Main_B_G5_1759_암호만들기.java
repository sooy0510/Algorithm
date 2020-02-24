package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 1759_암호만들기
 * 메모리 : 13792KB
 * 시간 : 84ms
 * 코드길이 : 1709B
 * 풀이
 * 1차 
 * - 순열 => 시간초과
 * 
 * 2차
 * 1. 조합
 * 2. 조합이 만들어지기전(index == L)에 리턴해서 모든 조합이 제대로 만들어지지 않았다
 *
 */

public class Main_B_G5_1759_암호만들기 {

	static int L;	//암호 길이
	static int C;
	static char[] arr;
	static boolean[] selected;
	static char[] result;
	static char[] temp;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[C];
		result = new char[L];
		temp = new char[L];
		selected = new boolean[C];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(arr);
		
		makepw(0,0);
		System.out.println(sb);
	}

	private static void makepw(int index, int cnt) {
		
		if(cnt == L) {
			
			int idx = 0;
			for(int s=0; s<selected.length; s++) {
				if(selected[s]) {
					result[idx++] = arr[s];
				}
			}
			
			int mo=0;
			int ja=0;
			
			//암호요건을 만족하는지 확인(최소 한개의 모음과 최소 두개의 자음)
			for (int i = 0; i < L; i++) {
				if (result[i] == 'a' || result[i] == 'e' || result[i] == 'i' || result[i] == 'o' || result[i] == 'u') 
					mo++;
			}
			ja = L - mo;
			
			if(mo < 1 || ja <2)
				return;
			
			
			for (char c : result) {
				sb.append(c);
			}
			sb.append("\n");
			return;
		}
		
		if(index == C)
			return;
		
		//조합
		selected[index] = true; 
		makepw(index+1, cnt+1);//선택하고
		selected[index] = false;
		makepw(index+1, cnt);	//선택안하고
	}

}
