package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_5987_달리기_2 {

	static int T,N,M;
	static int[] needs;
	static long[] memo;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int test=1;test<=T;test++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int f,s;
			needs = new int[N];
			memo = new long[(1<<N)];
			
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				f = Integer.parseInt(st.nextToken())-1;
				s = Integer.parseInt(st.nextToken())-1;
				needs[f] |= 1<<s;	//자기보다 뒤에 있는 사람들 표시
			}
			
			long r = solve(0);
			
			sb.append("#"+test+" "+r+"\n");
		}
		System.out.println(sb);
	}
	static long solve(int flag) {
		if(flag == (1<<N)-1) {
			return 1;
		}
		
		if(memo[flag] > 0) {
			return memo[flag];
		}
		
		//순열
		for(int i=0;i<N;i++) {
			//순열
			if((flag&1<<i)==0) {	//아직 선택된것이 없다면(순열)
				//
				if((flag & needs[i]) == needs[i]) {
									//순열
					memo[flag] +=solve(flag | 1<<i);
				}
			}
		}
		return memo[flag];
	}

}
