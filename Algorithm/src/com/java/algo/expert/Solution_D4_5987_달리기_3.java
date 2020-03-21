package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_5987_달리기_3 {

	private static int TC;
	private static int N;
	private static int M;
	private static long[] relation;
	private static int x;
	private static int y;
	private static long[] numbers;  

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			relation = new long[N];
			numbers = new long[1<<N];
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken())-1;
				y = Integer.parseInt(st.nextToken())-1;
				relation[y] |= 1<<x;	//자기보다 먼저 들어온 사람들 표시해주기
			}
			
			System.out.println("#"+tc+" "+solve(0));
		}
	}
	static long solve(int index) {
		//슨서대로 채우면 1로 리턴
		if(index==(1<<N) -1) {
			return 1;
		}
		if(numbers[index]>0) { 	//벌써 그자리에 값이 있으면 다시 구할 필요가 없다. memoi
			return numbers[index];
		}
		for(int i=0;i<N;i++) {
			if((index&1<<i)>0) continue;
			if((index&relation[i])!=relation[i]) continue;
			numbers[index]+=solve(index|1<<i);	
		}
		return numbers[index];
	}

}
