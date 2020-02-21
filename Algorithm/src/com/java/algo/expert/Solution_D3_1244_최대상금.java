package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution_D3_1244_최대상금 {

	static int[] numbers;
	static String N;
	static int S;	//교환횟수
	static int len;
	static int MAX = 0;
	static HashSet<String> s = new HashSet<String>();;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
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
			
			permutation(S);	//잔여 교환횟수
			sb.append("#"+test+" "+MAX+"\n");
		}
		System.out.println(sb);
		
		
	}

	private static void permutation(int S) {
		
		int val = 0;
		for(int i=0; i<len; i++) {
			val = val*10 + numbers[i];
		}
		
		if(s.contains(""+val+S)) {
			return;
		}
		
		s.add(""+val+S);
		if (S == 0) {
			if (MAX < val) MAX = val;
			return;
		}
		
		
		//모든 경우의 수를 다 따져봐야함
		for(int i=0; i<len-1; i++) {
			for(int j=i+1; j<len; j++) {
				swap(i,j);
				permutation(S-1);
				swap(i, j);
			}
		}
	}

	private static void swap(int a, int b) {
		//값이 아니라 인덱스를 서로 교환
		int temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;
	}

}